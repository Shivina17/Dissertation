package com.harmreduction.service;

import com.harmreduction.dao.ForumDao;
import com.harmreduction.dao.ForumDaoImpl;
import com.harmreduction.model.Forum;

import java.io.IOException;
import java.util.List;

public class ForumServiceImpl implements ForumService{

    private final ForumDao dao = new ForumDaoImpl();

    @Override
    public void createPost(Forum post) throws IOException {
        dao.createPost(post);
    }

    @Override
    public boolean doesPostExist(Forum post) throws IOException {
        return dao.doesPostExist(post);
    }

    @Override
    public Forum findById(String id) throws IOException {
        return dao.findById(id);
    }

    @Override
    public List<Forum> findBySearch(String quote) throws IOException {
        return dao.findBySearch(quote);
    }

    @Override
    public List<Forum> findByJsonQuery(String quote) throws IOException {
        return dao.findByJsonQuery(quote);
    }

    @Override
    public List<Forum> findByTopicId(String quote) throws IOException {
        return dao.findByTopicId(quote);
    }

    @Override
    public List<Forum> findByUsername(String quote) throws IOException {
        return dao.findByUsername(quote);
    }
}
