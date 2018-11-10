package com.harmreduction;

import com.harmreduction.model.Music;
import com.harmreduction.service.MusicService;
import com.harmreduction.service.MusicServiceImpl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class MusicApp {
	private static MusicService musicService = null;

	public static void main(String[] args) throws Exception {

		musicService = new MusicServiceImpl();

		//insertSomeMusic();

		printMusicBySearch("love");
	}

	private static void insertSomeMusic() {
		// Save music 1
		Music music1 = new Music();
		music1.setYear(new Date());
		music1.setDocumentId("1");
		music1.setLyrics("Every night in my dreams\n" +
				"I see you, I feel you\n" +
				"That is how I know you go on");
		music1.setName("Hollywood Track 1");
		createMusic(music1);

		// Save music 2
		Music music2 = new Music();
		music2.setYear(new Date());
		music2.setDocumentId("2");
		music2.setLyrics("Love was when I loved you\n" +
				"One true time I hold to\n" +
				"In my life we'll always go on");
		music2.setName("Hollywood Track 2");
		createMusic(music2);

		// Save music 3
		Music music3 = new Music();
		music3.setYear(new Date());
		music3.setDocumentId("3");
		music3.setLyrics("Love can touch us one time\n" +
				"And last for a lifetime\n" +
				"And never let go till we're gone");
		music3.setName("Hollywood Track 3");
		createMusic(music3);

		// Save music 3
		Music music4 = new Music();
		music4.setYear(new Date());
		music4.setDocumentId("4");
		music4.setLyrics("Near, far, wherever you are\n" +
				"I believe that the heart does go on\n" +
				"Once more you open the door\n" +
				"And you're here in my heart\n" +
				"And my heart will go on and on");
		music4.setName("Hollywood Track 4");
		createMusic(music4);
	}

	private static void createMusic(Music music) {
		try {
			musicService.createMusic(music);

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
		List<Music> music = musicService.findMusicBySearch(quote);

		if (music != null) {
			//print music
		} else {
			System.out.println("No music found");
		}
	}
}
