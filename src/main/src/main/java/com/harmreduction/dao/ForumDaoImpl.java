package com.harmreduction.dao;

import com.harmreduction.client.ESJestClient;
import com.harmreduction.model.Forum;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Get;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ForumDaoImpl implements ForumDao{

    String indexName = "dm_forum";
    String typeName = "doc";

    @Override
    public void createPost(Forum post) throws IOException {
        Index index = new Index.Builder(post).index(indexName).type(typeName).build();
        JestClient client = ESJestClient.getClient();
        client.execute(index);
    }

    @Override
    public boolean doesPostExist(Forum post) throws IOException {
        System.out.println("exists?");
        try {
            if (findById(post.getPostId()) != null) {
                return true;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Forum findById(String id) throws IOException {
        Get get = new Get.Builder(indexName, id).type(typeName).build();
        JestClient client = ESJestClient.getClient();
        JestResult result = client.execute(get);

        return result.getSourceAsObject(Forum.class);
    }

    @Override
    public List<Forum> findBySearch(String quote) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchPhraseQuery("text", quote));

        Search search = new Search.Builder(searchSourceBuilder.toString())
                // multiple index or types can be added.
                .addIndex(indexName)
                .addType(typeName)
                .build();
        JestClient client = ESJestClient.getClient();
        SearchResult result = client.execute(search);
        List<SearchResult.Hit<Forum, Void>> hits = result.getHits(Forum.class);

        List<Forum> posts = new ArrayList<Forum>();
        for (int i=0; i<hits.size(); i++){
            posts.add(hits.get(i).source);
        }
        return posts;
    }

    @Override
    public List<Forum> findByJsonQuery(String quote) throws IOException {
        try {
//            System.out.println(quote);
//            System.out.println("\n");
//
//             quote = "{\n" +
//                    "  \"query\": {\n" +
//                    "    \"bool\": {\n" +
//                    "      \"must\": [\n" +
//                    "        {\n" +
//                    "          \"match_phrase\": {\n" +
//                    "            \"text\": \"help\"\n" +
//                    "          }\n" +
//                    "        }\n" +
//                    "      ],\n" +
//                    "      \"filter\": [\n" +
//                    "        {\n" +
//                    "          \"match\": {\n" +
//                    "            \"userName\": \"ISLEOFWEED3\"\n" +
//                    "          }\n" +
//                    "        }\n" +
//                    "      ],\n" +
//                    "      \"must_not\": [],\n" +
//                    "      \"should\": []\n" +
//                    "    }\n" +
//                    "  }\n" +
//                    "}";
//
//
//            System.out.println(quote);
//            String query = "{\"query\":{\"match_phrase\":{\"text\":\""+quote2+"\"}}}";
//            System.out.println(query);

//            System.out.println(quote);
//            System.out.println("\n");
//            System.out.printf(query);

            Search search = new Search.Builder(quote)
                    // multiple index or types can be added.
                    .addIndex(indexName)
                    .addType(typeName)
                    .build();

            JestClient client = ESJestClient.getClient();
            SearchResult result = client.execute(search);
            List<SearchResult.Hit<Forum, Void>> hits = result.getHits(Forum.class);

            List<Forum> posts = new ArrayList<>();
            for (int i = 0; i < hits.size(); i++) {
                posts.add(hits.get(i).source);
            }
            return posts;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Forum> findByTopicId(String id) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchPhraseQuery("topicId", id));

        Search search = new Search.Builder(searchSourceBuilder.toString())
                // multiple index or types can be added.
                .addIndex(indexName)
                .addType(typeName)
                .build();
        JestClient client = ESJestClient.getClient();
        SearchResult result = client.execute(search);
        List<SearchResult.Hit<Forum, Void>> hits = result.getHits(Forum.class);

        List<Forum> posts = new ArrayList<Forum>();
        for (int i=0; i<hits.size(); i++){
            posts.add(hits.get(i).source);
        }
        return posts;
    }

    @Override
    public List<Forum> findByUsername(String username) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchPhraseQuery("userName", username));

        Search search = new Search.Builder(searchSourceBuilder.toString())
                // multiple index or types can be added.
                .addIndex(indexName)
                .addType(typeName)
                .build();
        JestClient client = ESJestClient.getClient();
        SearchResult result = client.execute(search);
        List<SearchResult.Hit<Forum, Void>> hits = result.getHits(Forum.class);

        List<Forum> posts = new ArrayList<Forum>();
        System.out.println(hits.size());

        for (int i=0; i<hits.size(); i++){
            System.out.println(hits.get(i).source);
            posts.add(hits.get(i).source);
        }


//        List<SearchResult.Hit<Map,Void>> hits2 = client.execute(search).getHits(Map.class);
//        for (SearchResult.Hit hit: hits2){
//            System.out.println(hit.source);
//        }

        return posts;
    }
}


/*


curl -X GET "localhost:9201/dm_forum/_search" -H 'Content-Type: application/json' -d'
{
  "query": {
    "bool": {
      "must": {
        "match_phrase": {
            "text":"help"
        }
      },
      "filter": {
        "term": {
          "userName": "cookerblocks"
        }
      }
    }
  }
}
'

 */