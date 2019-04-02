package com.harmreduction;

import com.harmreduction.model.Forum;
import com.harmreduction.service.ForumService;
import com.harmreduction.service.ForumServiceImpl;

import java.io.IOException;
import java.util.List;

public class ForumApp {
    private static ForumService service = null;

    public static void main(String[] args) throws Exception {
        service = new ForumServiceImpl();
       // insert();

//        printBySearch("help");
        printByQuery("{\n" +
                "  \"query\": {\n" +
                "    \"bool\": {\n" +
                "      \"must\": [\n" +
                "        {\n" +
                "          \"match_phrase\": {\n" +
                "            \"text\": \"help\"\n" +
                "          }\n" +
                "        }\n" +
                "      ],\n" +
                "      \"filter\": [\n" +
                "        {\n" +
                "          \"term\": {\n" +
                "            \"userName\": \"SpicyHab\"\n" +
                "          }\n" +
                "        }\n" +
                "      ],\n" +
                "      \"must_not\": [],\n" +
                "      \"should\": []\n" +
                "    }\n" +
                "  }\n" +
                "}");

       // printByTopicId("94747");
       // printByUsername("SpicyHab");
    }



    private static void create(Forum post) {
        try {
            service.createPost(post);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void printBySearch(String quote) throws Exception{
//        List<StackExchange> posts = service.findBySearch(quote);
        List<Forum> posts = service.findBySearch(quote);

        if (posts != null) {
            //print music
            for (Forum post: posts){
                System.out.println(post.getText()+"\n");
            }
        } else {
            System.out.println("No music found");
        }
    }

    private static void printByQuery(String query) throws Exception{
//        List<StackExchange> posts = service.findBySearch(quote);
        List<Forum> posts = service.findByJsonQuery(query);

        if (posts != null) {
            //print music
            for (Forum post: posts){
                System.out.println("USERNAME "+post.getUserName()+"\n");
                System.out.println(post.getText()+"\n");
            }
        } else {
            System.out.println("No music found");
        }
    }


    private static void printByTopicId(String id) throws IOException{
        List<Forum> posts = service.findByTopicId(id);

        System.out.println(posts);
        if (posts != null) {
            //print music
            for (Forum post: posts){
                System.out.println(post.getText()+"\n");
            }
        } else {
            System.out.println("No music found");
        }
    }

    private static void printByUsername(String username) throws IOException{
        List<Forum> posts = service.findByUsername(username);

//        System.out.println(posts);
        if (posts != null) {
            //print music
            for (Forum post: posts){
                System.out.println(post.getText()+"\n");
            }
        } else {
            System.out.println("No music found");
        }
    }





}


/*
curl -H 'Content-Type: application/json' -XPOST 'http://localhost:9200/dm_forum/doc' -d '{
    "userName" : "Thugzmike",
          "userRegDate" : "2018-12-26",
          "postMessage" : "\n\t\t\t\t\t\t<p>HI HOW CAN I SEE HOW MANY TRANSACTION SINCE I AM ON DREAM MARKET ?? IS THERE A WAY TO SEE THE AMOUNT OF $ I DID SPEND TO ?? CUZ IT HELP CREDIBILITY IF WE CAN CUZ UNFORTUNATELY I GOT SCAM TWICE AND I HAVE TO OPEN A DISPUTE BUT WITH MORE OF 60 SUCSESSFUL TRANSACTION BEFORE MY TERIBLE&nbsp; WEEK I HOPE OTHER DEALER WILL UNDERSTAND THAT I AM SERIOUS AND NO PROBLEM</p>\n\t\t\t\t\t",
          "userStatus" : "Member",
          "userSignature" : "WASSUP",
          "postId" : "p459544",
          "forumId" : 6,
          "forumName" : "Off Topic",
          "topicId" : 94747,
          "topicName" : "How to check your own feedback?",
          "postNumber" : 17,
          "userPostCount" : 43,
          "topicPageNumber" : 1,
          "userId" : 143689,
          "postTime" : "2019-01-12T19:14:51",
          "languages" : [ ],
          "type" : "post",
          "text" : "HI HOW CAN I SEE HOW MANY TRANSACTION SINCE I AM ON DREAM MARKET ?? IS THERE A WAY TO SEE THE AMOUNT OF $ I DID SPEND TO ?? CUZ IT HELP CREDIBILITY IF WE CAN CUZ UNFORTUNATELY I GOT SCAM TWICE AND I HAVE TO OPEN A DISPUTE BUT WITH MORE OF 60 SUCSESSFUL TRANSACTION BEFORE MY TERIBLE&nbsp; WEEK I HOPE OTHER DEALER WILL UNDERSTAND THAT I AM SERIOUS AND NO PROBLEM"

}'



curl XPUT 'http://localhost:9200/dm_forum/_settings
{
  "index": {
    "blocks": {
      "read_only_allow_delete": "false"
    }
  }
}

curl -XPUT "localhost:9200/dm_forum/settings" -H 'Content-Type: application/json' -d'
{
    "index": {
    "blocks": {
      "read_only_allow_delete": "false"
    }
  }
}
'


curl -H 'Content-Type: application/x-ndjson' -XPOST 'localhost:9200/dm_forum/doc/_bulk?pretty' --data-binary @accounts.json

curl -H 'Content-Type: application/json' -XPOST 'localhost:9200/bank/dm_forum/_bulk?pretty' --data-binary @accounts.json

curl PUT .kibana/_settings -d'
{
"index": {
"blocks": {
"read_only_allow_delete": "false"
}
}
}'




curl -XPUT http://localhost:9200/twitter  -H "Content-Type: application/json" -d '
{
 "mappings" : {
  "_default_" : {
   "properties" : {
    "speaker" : {"type": "string", "index" : "not_analyzed" },
    "play_name" : {"type": "string", "index" : "not_analyzed" },
    "line_id" : { "type" : "integer" },
    "speech_number" : { "type" : "integer" }
   }
  }
 }
}
';



curl -XGET gitlab.server:9200/ -H "Content-Type: application/json" -d "
{\"query\": {\"simple_query_string\" : {\"fields\" : [\"content\"], \"query\" : \"foo bar -baz\"}}
}"


https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/transport-client.html
https://stackoverflow.com/questions/36591116/how-to-connect-to-remote-elasticsearch-server-which-is-up-and-started-using-elas


 */
