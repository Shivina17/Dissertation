package com.harmreduction.service;

import com.harmreduction.dao.StackExchangeDao;
import com.harmreduction.dao.StackExchangeDaoImpl;
import com.harmreduction.model.StackExchange;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;


public class StackExchangeServiceImpl implements StackExchangeService {

    private final StackExchangeDao dao = new StackExchangeDaoImpl();;

    @Override
    public boolean doesExist(StackExchange post) {
        return dao.doesExist(post);
    }

    @Override
    public void create(StackExchange post) throws IOException {
        dao.create(post);
    }

    @Override
    public void createFromSourceString(String post) throws IOException {
        dao.createFromSourceString(post);
    }

    @Override
    public void update(StackExchange post) throws IOException {
        dao.update(post);
    }

    @Override
    public boolean delete(String id) throws UnknownHostException {
        return dao.delete(id);
    }

    @Override
    public StackExchange findById(String id) throws IOException {
        return dao.findById(id);
    }

    @Override
    public List<StackExchange> findBySearch(String quote) throws IOException {
        return dao.findBySearch(quote);
    }

    @Override
    public List<StackExchange> findByJsonQuery(String quote) throws IOException {
        return dao.findByJsonQuery(quote);
    }
}
