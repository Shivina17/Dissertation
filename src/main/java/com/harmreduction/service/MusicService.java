package com.harmreduction.service;

import com.harmreduction.model.Music;

import java.io.IOException;
import java.util.List;

public interface MusicService {
	public boolean isMusicExist(Music music);
	public void createMusic(Music music) throws IOException;
	public void updateMusic(Music music) throws IOException;
	public Music findMusicById(String id) throws IOException;
	public boolean deleteMusicById(String id);
	public List<Music> findMusicBySearch(String quote) throws IOException;
}
