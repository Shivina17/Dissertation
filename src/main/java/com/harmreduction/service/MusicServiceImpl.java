package com.harmreduction.service;

import com.harmreduction.dao.MusicDao;
import com.harmreduction.dao.MusicDaoImpl;
import com.harmreduction.model.Music;

import java.io.IOException;
import java.util.List;

public class MusicServiceImpl implements MusicService {
	private final MusicDao musicDao = new MusicDaoImpl();;
	
	public boolean isMusicExist(Music music) {
		return musicDao.doesMusicExist(music);
	}
	
	public void createMusic(Music music) throws IOException {
		musicDao.createMusic(music);
	}
	
	public Music findMusicById(String id) throws IOException {
		return musicDao.findMusicById(id);
	}

	public void updateMusic(Music music) throws IOException {
		musicDao.updateMusic(music);
	}

	public boolean deleteMusicById(String id) {
		return musicDao.deleteMusicById(id);
	}

	@Override
	public List<Music> findMusicBySearch(String quote) throws IOException {
		return musicDao.findMusicBySearch(quote);
	}


}
