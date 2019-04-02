package com.harmreduction;

import com.harmreduction.model.Music;
import com.harmreduction.service.MusicService;
import com.harmreduction.service.MusicServiceImpl;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Date;
import java.util.List;



public class MusicApp {
	private static MusicService musicService = null;

	public static void main(String[] args) throws Exception {
		musicService = new MusicServiceImpl();
		//readJson();

		//insertSomeMusic();

		printMusicBySearch("near, far");

	}

	public static String readJson() throws IOException, ParseException {
		//JsonReader jsonReader = new JsonReader(new FileReader("/Users/shivinasethia/Documents/Dissertation/quikcstart-es-java-master/src/main/java/com/harmreduction/data.json"));
//		JSONParser parser = new JSONParser();
//
//		Object obj = parser.parse(new FileReader("/Users/shivinasethia/Documents/Dissertation/quikcstart-es-java-master/src/main/src/main/java/com/harmreduction/data.json"));
//		JSONObject jsonObject = (JSONObject) obj;
//		JSONObject posts = (JSONObject) jsonObject.get("posts");
//		JSONArray rows = (JSONArray) posts.get("row");
//		for(Object row: rows){
//			//System.out.println(row.toString());
//			insertPost(row.toString());
//		}

		//insertPost("");
		return "hi";

	}

	public static void insertPost(String post){
//		String date = new Date().toString();
//		String music4 = post;
//		System.out.println(post);

		String music4 = "\n" +
				"{\"-FavoriteCount\":\"2\"," +
				"\"documentId\":\"5\"" +
				"\"-AnswerCount\":\"3\"," +
				"\"-PostTypeId\":\"1\"," +
				"\"-LastActivityDate\":\"2016-01-13T13:36:41.160\"," +
				"\"-Tags\":\"<discussion>\"," +
				"\"-Id\":\"1\"," +
				"\"-OwnerUserId\":\"30\"," +
				"\"-CreationDate\":\"2016-01-12T19:24:29.457\"," +
				"\"-CommentCount\":\"1\",\"-Score\":\"20\",\"" +
				"-Title\":\"What can \\\"newbies\\\" do to help the site at this stage?\"," +
				"\"-ViewCount\":\"113\"," +
				"\"-Body\":\"<p>I have been wanting to learn about 3D printing a long" +
				" time so I really want this site to succeed but I have no previous experience with the subject. <\\/p>" +
				"<p>I was wondering how can I help the site at this early stage. " +
				"I thought about asking about how to get started with 3D printing but SE " +
				"explicitly discourages \\\"easy\\\" questions in the private beta.<\\/p>" +
				"<p>What can newbies like me do for the site at this stage besides voting questions and answers?<\\/p>\"}";

		createMusicSource(music4);
	}

	private static void insertSomeMusic() {
		// Save music 1
		Music music1 = new Music();
		music1.setYear(new Date());
		music1.setDocumentId("1");
		music1.setLyrics("Near, far, wherever you are\n" +
				"I believe that the heart does go on\n" +
				"Once more you open the door\n" +
				"And you're here in my heart\n" +
				"And my heart will go on and on");
		music1.setName("My heart will go on");
		createMusic(music1);

		// Save music 2
		Music music2 = new Music();
		music2.setYear(new Date());
		music2.setDocumentId("2");
		music2.setLyrics("Tú, tú eres el imán y yo soy el metal \n" +
				"Me voy acercando y voy armando el plan \n" +
				"Solo con pensarlo se acelera el pulso (Oh yeah)");
		music2.setName("Despacito");
		createMusic(music2);

		// Save music 3
		Music music3 = new Music();
		music3.setYear(new Date());
		music3.setDocumentId("3");
		music3.setLyrics("Not knowing what it was\n" +
				"I will not give you up this time\n" +
				"But darling, just kiss me slow, your heart is all I own\n" +
				"And in your eyes you're holding mine");
		music3.setName("Perfect");
		createMusic(music3);

		// Save music 3
		Music music4 = new Music();
		music4.setYear(new Date());
		music4.setDocumentId("4");
		music4.setLyrics("Havana, ooh na-na (ay)\n" +
				"Half of my heart is in Havana, ooh-na-na (ay, ay)\n" +
				"He took me back to East Atlanta, na-na-na\n" +
				"Oh, but my heart is in Havana (ay)\n" +
				"There's somethin' 'bout his manners (uh huh)\n" +
				"Havana, ooh na-na (uh)");
		music4.setName("Havana");
		createMusic(music4);



	}

	private static void createMusic(Music music) {
		try {
			musicService.createMusic(music);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void createMusicSource(String music) {
		try {
			musicService.createMusicSource(music);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private static void printMusic(String id) throws IOException {
		Music music = musicService.findMusicById(id);

		if (music != null) {
			System.out.println(music.toString());
		} else {
			System.out.println("No  music with the ID[" + id + "] found");
		}
	}

	private static void printMusicBySearch(String quote) throws Exception{
//		List<Music> music = musicService.findMusicBySearch(quote);
		List<Music> music = musicService.findMusicBySearchJSON(quote);

		if (music != null) {
			//print music
		} else {
			System.out.println("No music found");
		}
	}
}
