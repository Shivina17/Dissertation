package com.harmreduction.service;

import com.harmreduction.model.Music;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

public interface MusicService {
	public boolean isMusicExist(Music music);
	public void createMusic(Music music) throws IOException;
	public void createMusicSource(String music) throws IOException;

	public void updateMusic(Music music) throws IOException;
	public Music findMusicById(String id) throws IOException;
	public boolean deleteMusicById(String id) throws UnknownHostException;

	public List<Music> findMusicBySearch(String quote) throws IOException;
	public List<Music> findMusicBySearchJSON(String quote) throws IOException;
}
