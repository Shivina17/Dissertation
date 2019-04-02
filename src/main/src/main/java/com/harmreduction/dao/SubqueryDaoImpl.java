package com.harmreduction.dao;

import com.harmreduction.client.ESJestClient;
import com.harmreduction.model.Subqueries;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Delete;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SubqueryDaoImpl implements SubqueryDao {
    String indexName = "subqueries";
    String typeName = "subquerydocs";

    @Override
    public void createQuery(Subqueries subquery) throws IOException {
        Index index = new Index.Builder(subquery).index(indexName).type(typeName).build();
        JestClient client = ESJestClient.getClient();
        client.execute(index);
    }

    @Override
    public void deleteQuery(String queryName) throws IOException {
        List<String> ids = getIdByName(queryName);
        JestClient client = ESJestClient.getClient();
        for(String id : ids){
            System.out.println("DELETING "+id);

            client.execute(new Delete.Builder(id)
                    .index(indexName)
                    .type(typeName)
                    .build());
        }
    }

    @Override
    public void editQuery(String queryName, String newQuery, String currentuser, String datetime) throws IOException {
        System.out.println("HI");

        deleteQuery(queryName);

        Subqueries newQ = new Subqueries();
        newQ.setDate(datetime);
        newQ.setAuthor(currentuser);
        newQ.setQueryName(queryName);
        newQ.setQuery(newQuery);
        createQuery(newQ);
    }

//    @Override
//    public void editQuery(String queryName, String newQuery) throws IOException {
//        //if queryname works get that to get ID?
//        String payload = new Gson().toJson(newQuery);
//        payload = new Gson().toJson(payload);
//        try{
//            String query = "{\n" +
//                    "  \"query\": {\n" +
//                    "\n" +
//                    "              \"term\": {\n" +
//                    "                \"queryName\": \""+queryName+"\"\n" +
//                    "              }\n" +
//                    "            },\n" +
//                    "  \"script\" : \"ctx._source.query = "+payload.substring(1, payload.length()-1)+";\"\n" +
//                    "}";
//            System.out.println(query);
//
//
////            String script = "{\n" +
////                    "  \"script\" : \"ctx._source.query = "";\"\n" +
////                    "}";
//
//            //String script = "script\" : \"ctx._source.author = \"sks9\";\"\n";
//
//
////            query = "{\n" +
////                    "  \"query\": {\n" +
////                    "\n" +
////                    "              \"term\": {\n" +
////                    "                \"queryName\": \"me\"\n" +
////                    "              }\n" +
////                    "            },\n" +
////                    "  \"script\" : \"ctx._source.author = \"sks9\"; \"" +
////                    "}";
//           // JestClient client = ESJestClient.getClient();
////            client.execute(new Update.Builder(query).index(indexName).type(typeName).id("xITsv2kB8yqs1ktxUYVX").build());
//
//
////            String script = "{\n" +
////                    "    \"script\" : \"ctx._source.author = sks9\",\n" +
////                    "    \"term\" : {\n" +
////                    "                \"queryName\": \"me\"\n" +
////                    "              }\n" +
////                    "}";
//
//            final BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.termQuery("queryName", "me"));
//            final String script = "ctx._source.author = sks9;";
//
//            final String payload2 = jsonBuilder()
//                    .startObject()
//                    .field("query", queryBuilder)
//                    .startObject("script")
//                    .field("inline", script)
//                    .endObject()
//                    .endObject().string();
//
////            UpdateByQuery updateByQuery = new UpdateByQuery.Builder(payload)
////                    .addIndex(INDEX)
////                    .addType(TYPE)
////                    .build();
////
////            UpdateByQueryResult result = client.execute(updateByQuery);
//
////            JestClient client = ESJestClient.getClient();
////            JestResult result = client.execute(
////                    new Index.Builder(query)
////                            .index(indexName)
////                            .type(typeName)
////                            .build()
////            );
////            System.out.println(result);
////
////            Search search = new Search.Builder(query)
////                    // multiple index or types can be added.
////                    .addIndex(indexName)
////                    .addType(typeName)
////                    .build();
//
////            Index index = new Index.Builder(music).index("music").type("songs").id(music.getDocumentId()).build();
////            JestClient client = ESJestClient.getClient();
////            client.execute(index);
//
////            UpdateRequest updateRequest = new UpdateRequest("ttl", "doc", "1")
////                    .script(new Script("ctx._source.gender = \"male\""));
////            JestClient client = ESJestClient.getClient();
//////
//////            client.execute(updateRequest).get();
////            SearchResult result = client.execute(search);
////            List<SearchResult.Hit<Subqueries, Void>> hits = result.getHits(Subqueries.class);
//        }
//        catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//
//    }

    @Override
    public List<Subqueries> getAllSubqueries() throws IOException {
        try{
            String query = "{\"size\": 50,\"query\":{\"match_all\":{}}}";
            System.out.println(query);

            Search search = new Search.Builder(query)
                    // multiple index or types can be added.
                    .addIndex(indexName)
                    .addType(typeName)
                    .build();

            JestClient client = ESJestClient.getClient();
            SearchResult result = client.execute(search);
            List<SearchResult.Hit<Subqueries, Void>> hits = result.getHits(Subqueries.class);

            List<Subqueries> subqueries = new ArrayList<>();
            for (int i = 0; i < hits.size(); i++) {
                subqueries.add(hits.get(i).source);
            }
            return subqueries;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }


    }

    @Override
    public List<Subqueries> getByName(String queryName) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchPhraseQuery("queryName", queryName));
        String query = searchSourceBuilder.toString();

//        String query = "{\"query\":{\"term\":{ \"queryName\" : \""+queryName+"\" }}}";
//        System.out.println(query);
//


        Search search = new Search.Builder(query)
                // multiple index or types can be added.
                .addIndex(indexName)
                .addType(typeName)
                .build();

        //System.out.println(searchSourceBuilder.toString());

        JestClient client = ESJestClient.getClient();
        SearchResult result = client.execute(search);
        List<SearchResult.Hit<Subqueries, Void>> hits = result.getHits(Subqueries.class);

        List<Subqueries> subqueries = new ArrayList<Subqueries>();


        for (int i=0; i<hits.size(); i++){

            subqueries.add(hits.get(i).source);
        }
        return subqueries;
    }

    @Override
    public List<String> getIdByName(String queryName) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchPhraseQuery("queryName", queryName));
        String query = searchSourceBuilder.toString();

//        String query = "{\"query\":{\"term\":{ \"queryName\" : \""+queryName+"\" }}}";
//        System.out.println(query);
//


        Search search = new Search.Builder(query)
                // multiple index or types can be added.
                .addIndex(indexName)
                .addType(typeName)
                .build();

        //System.out.println(searchSourceBuilder.toString());

        JestClient client = ESJestClient.getClient();
        SearchResult result = client.execute(search);
        List<SearchResult.Hit<Subqueries, Void>> hits = result.getHits(Subqueries.class);

        List<String> subqueries = new ArrayList<String>();
        List<SearchResult.Hit<Map,Void>> hits2 = client.execute(search).getHits(Map.class);


        for (int i=0; i<hits.size(); i++){
            SearchResult.Hit hit = hits2.get(0);
            Map source = (Map)hit.source;
            String id = (String)source.get(JestResult.ES_METADATA_ID);
            System.out.println(id);
            subqueries.add(id);
        }
        return subqueries;
    }

}
