package com.harmreduction.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.harmreduction.util.DateTimeTypeConverter;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;

import java.util.Date;

public class ESJestClient {
	private static final String SERVER_URI = "http://localhost:9200";
	private static JestClient musicClient = null;
	public static JestClient getMusicClient() {
		
		if (musicClient == null) {
			//It's required for mapping of Date field from ES result to Model class
			Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateTimeTypeConverter()).create();
			JestClientFactory factory = new JestClientFactory();
			factory.setHttpClientConfig(
					new HttpClientConfig.Builder(SERVER_URI).gson(gson).multiThreaded(true).build());

			musicClient = factory.getObject();
		}
		
		return musicClient;
	}

}
