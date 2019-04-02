package com.harmreduction.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.harmreduction.util.DateTimeTypeConverter;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;

import java.net.UnknownHostException;
import java.util.Date;


/**
 * slogin -L9201:localhost:9200 -L5601:localhost:5601 e-research
 */
public class ESJestClient {
	private static final String SERVER_URI = "http://localhost:9201";	//138.251.22.79
	private static JestClient client = null;


	public static JestClient getClient() throws UnknownHostException {
		
		if (client == null) {
			//It's required for mapping of Date field from ES result to Model class
			Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateTimeTypeConverter()).create();
			JestClientFactory factory = new JestClientFactory();
			factory.setHttpClientConfig(
					new HttpClientConfig.Builder(SERVER_URI).gson(gson).multiThreaded(true).build());

			client = factory.getObject();
			System.out.println(client);
		}
		return client;



	}

}




/*

cd elasticsearch-6.4.2/bin
./elasticsearch
http://localhost:9200/_cat/indices
http://localhost:9200/music

 */


/*
"hits" : [
      {
        "_index" : "dm_forum",
        "_type" : "doc",
        "_id" : "Pj5-a2gBorwZXpPo-Cb-",
        "_score" : 1.0,
        "_source" : {
          "userName" : "dexterlabs",
          "userRegDate" : "2019-01-09",
          "postMessage" : "\n\t\t\t\t\t\t<p>yes just got it after hour and a half. Thank you anyways sorry for that.</p>\n\t\t\t\t\t",
          "userStatus" : "Member",
          "postId" : "p457208",
          "forumId" : 6,
          "forumName" : "Off Topic",
          "topicId" : 101278,
          "topicName" : "Hey somebody knows how to contact support?",
          "postNumber" : 3,
          "userPostCount" : 3,
          "topicPageNumber" : 1,
          "userId" : 147215,
          "postTime" : "2019-01-09T13:53:27",
          "languages" : [
            "en"
          ],
          "type" : "post",
          "text" : "yes just got it after hour and a half. Thank you anyways sorry for that."
        }
      }

 */


/*
GET /_search
{
    "query": {
        "constant_score" : {
            "filter" : {
                "terms" : { "user" : ["kimchy", "elasticsearch"]}
            }
        }
    }
}
 */