package com.harmreduction.dao;

import com.harmreduction.model.Music;

import java.io.IOException;
import java.util.List;

public interface MusicDao {
	public boolean doesMusicExist(Music music);
	public void createMusic(Music music) throws IOException;
	public void updateMusic(Music music) throws IOException;
	public boolean deleteMusicById(String id);
	public Music findMusicById(String id) throws IOException;

	public List<Music> findMusicBySearch(String quote) throws IOException;
}
