package com.harmreduction.dao;

import com.harmreduction.client.ESJestClient;
import com.harmreduction.model.StackExchange;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.*;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


public class StackExchangeDaoImpl implements StackExchangeDao {

    String indexName = "stackexchange";
    String typeName = "blogpost";

    public void create(StackExchange post) throws IOException {
        Index index = new Index.Builder(post).index(indexName).type(typeName).build();
         JestClient client = ESJestClient.getClient();
        client.execute(index);
    }


    public void createFromSourceString(String post) throws IOException {
        try {
            Index index = new Index.Builder(post).index(indexName).type(typeName).build();
            JestClient client = ESJestClient.getClient();
            client.execute(index);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public StackExchange findById(String id) throws IOException {
        Get get = new Get.Builder(indexName, id).type(typeName).build();
        JestClient client = ESJestClient.getClient();
        JestResult result = client.execute(get);

        return result.getSourceAsObject(StackExchange.class);
    }

    public boolean doesExist(StackExchange post) {
        System.out.println("exists?");
        try {
            if (findById(post.getDocumentId()) != null) {
                return true;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<StackExchange> findBySearch(String quote) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchPhraseQuery("post", quote));

        Search search = new Search.Builder(searchSourceBuilder.toString())
                // multiple index or types can be added.
                .addIndex(indexName)
                .addType(typeName)
                .build();
        JestClient client = ESJestClient.getClient();
        SearchResult result = client.execute(search);
        List<SearchResult.Hit<StackExchange, Void>> hits = result.getHits(StackExchange.class);

        List<StackExchange> posts = new ArrayList<StackExchange>();
        for (int i=0; i<hits.size(); i++){
            System.out.println("\n"+hits.get(i).source.getName()+"\n");
            System.out.println(hits.get(i).source.getPost());
            posts.add(hits.get(i).source);
        }
        return posts;
    }

    @Override
    public List<StackExchange> findByJsonQuery(String quote) throws IOException {
        try {
//            String query =
//                    "\n" +
//                            "    \"query\": {\n" +
//                            "        \"match_phrase\" : {\n" +
//                            "                    \"post\" : \""+quote+"\"\n" +
//                            "        }\n" +
//                            "    }\n" +
//                            "";
            //matches exactly

            String query = "{\"query\":{\"match_phrase\":{\"post\":\""+quote+"\"}}}";
//            String query = "{\"query\":{\"match_all\":{}}}";

            System.out.println(query);

//			String query = "{\n" +
//					"\t\t\t\t\"query\": {\n" +
//					"\t\t\t\t\"query_string\" : {\n" +
//					"\t\t\t\t\t\"default_field\" : \"lyrics\",\n" +
//					"\t\t\t\t\t\t\t\"query\" : \"("+quote+")\"\n" +
//					"\t\t\t\t}\n" +
//					"\t\t\t}\n" +
//					"\t\t\t}";



            Search search = new Search.Builder(query)
                    // multiple index or types can be added.
                    .addIndex(indexName)
                    .addType(typeName)
                    .build();

            JestClient client = ESJestClient.getClient();
            SearchResult result = client.execute(search);
            List<SearchResult.Hit<StackExchange, Void>> hits = result.getHits(StackExchange.class);

            List<StackExchange> posts = new ArrayList<>();
            for (int i = 0; i < hits.size(); i++) {
                System.out.println("\n" + hits.get(i).source.getName() + "\n");
                System.out.println(hits.get(i).source.getPost());
                posts.add(hits.get(i).source);
            }
            return posts;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    public void update(StackExchange post) throws IOException {

        Index index = new Index.Builder(post).index(indexName).type(typeName).id(post.getDocumentId()).build();
        JestClient client = ESJestClient.getClient();
        client.execute(index);
    }

    public boolean delete(String id) throws UnknownHostException {
        JestClient client = ESJestClient.getClient();
        try {
            DocumentResult documentResult = client.execute(new Delete.Builder(id)
                    .index(indexName)
                    .type(typeName)
                    .build());
            return documentResult.isSucceeded();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }
}
