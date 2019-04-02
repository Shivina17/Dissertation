package com.harmreduction.dao;

import com.harmreduction.client.ESJestClient;
import com.harmreduction.model.Music;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.*;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class MusicDaoImpl implements MusicDao {
	public void createMusic(Music music) throws IOException {
		Index index = new Index.Builder(music).index("music").type("songs").build();
		JestClient client = ESJestClient.getClient();
		client.execute(index);
	}


	public void createMusicSource(String music) throws IOException {
		try {
			Index index = new Index.Builder(music).index("music").type("songs").build();
			JestClient client = ESJestClient.getClient();
			client.execute(index);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	public Music findMusicById(String id) throws IOException {
		Get get = new Get.Builder("music", id).type("songs").build();
		JestClient client = ESJestClient.getClient();
		JestResult result = client.execute(get);

		return result.getSourceAsObject(Music.class);
	}

	@Override
	public List<Music> findMusicBySearch(String quote) throws IOException {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchPhraseQuery("lyrics", "love"));

		Search search = new Search.Builder(searchSourceBuilder.toString())
				// multiple index or types can be added.
				.addIndex("music")
				.addType("songs")
				.build();
		JestClient client = ESJestClient.getClient();
		SearchResult result = client.execute(search);
		List<SearchResult.Hit<Music, Void>> hits = result.getHits(Music.class);

		List<Music> music = new ArrayList<Music>();
		for (int i=0; i<hits.size(); i++){
			System.out.println("\n"+hits.get(i).source.getName()+"\n");
			System.out.println(hits.get(i).source.getLyrics());
			music.add(hits.get(i).source);
		}
		return music;
	}

	@Override
	public List<Music> findMusicBySearchJSON(String quote) throws IOException {
		try {
			String query =
					"{\n" +
					"    \"query\": {\n" +
					"        \"match_phrase\" : {\n" +
					"                    \"lyrics\" : \""+quote+"\"\n" +
					"        }\n" +
					"    }\n" +
					"}";
			//matches exactly

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
					.addIndex("music")
					.addType("songs")
					.build();

			JestClient client = ESJestClient.getClient();
			SearchResult result = client.execute(search);
			List<SearchResult.Hit<Music, Void>> hits = result.getHits(Music.class);

			List<Music> music = new ArrayList<>();
			for (int i = 0; i < hits.size(); i++) {
				System.out.println("\n" + hits.get(i).source.getName() + "\n");
				System.out.println(hits.get(i).source.getLyrics());
				music.add(hits.get(i).source);
			}
			return music;
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			return null;
		}

	}

	public boolean doesMusicExist(Music music) {
		System.out.println("exists?");
		try {
			if (findMusicById(music.getDocumentId()) != null) {
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public void updateMusic(Music music) throws IOException {

		Index index = new Index.Builder(music).index("music").type("songs").id(music.getDocumentId()).build();
		JestClient client = ESJestClient.getClient();
		client.execute(index);
	}

	public boolean deleteMusicById(String id) throws UnknownHostException {
		JestClient client = ESJestClient.getClient();
		try {
			DocumentResult documentResult = client.execute(new Delete.Builder(id)
			        .index("music")
			        .type("songs")
			        .build());
			return documentResult.isSucceeded();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

}
