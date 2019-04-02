package com.harmreduction.dao;



import com.harmreduction.model.Forum;

import java.io.IOException;
import java.util.List;

public interface ForumDao {

    public void createPost(Forum post) throws IOException;
    public boolean doesPostExist(Forum post) throws IOException;

    public Forum findById(String id) throws IOException;
    public List<Forum> findBySearch(String quote) throws IOException;
    List<Forum> findByJsonQuery(String quote) throws IOException;
    public List<Forum> findByTopicId(String quote) throws IOException;
    public List<Forum> findByUsername(String quote) throws IOException;
}
