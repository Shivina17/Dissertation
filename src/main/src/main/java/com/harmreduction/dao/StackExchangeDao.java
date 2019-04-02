package com.harmreduction.dao;

import com.harmreduction.model.StackExchange;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

public interface StackExchangeDao {
    public boolean doesExist(StackExchange post);

    public void create(StackExchange post) throws IOException;
    public void createFromSourceString(String post) throws IOException;

    public void update(StackExchange post) throws IOException;
    public boolean delete(String id) throws UnknownHostException;

    public StackExchange findById(String id) throws IOException;
    public List<StackExchange> findBySearch(String quote) throws IOException;
    List<StackExchange> findByJsonQuery(String quote) throws IOException;
}
