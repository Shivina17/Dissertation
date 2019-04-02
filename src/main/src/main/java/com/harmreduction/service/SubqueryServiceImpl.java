package com.harmreduction.service;

import com.harmreduction.dao.SubqueryDao;
import com.harmreduction.dao.SubqueryDaoImpl;
import com.harmreduction.model.Subqueries;

import java.io.IOException;
import java.util.List;


public class SubqueryServiceImpl implements SubqueryService {

    SubqueryDao subqueryDao = new SubqueryDaoImpl();
    @Override
    public void createQuery(Subqueries subquery) throws IOException {
        subqueryDao.createQuery(subquery);
    }

    @Override
    public void deleteQuery(String queryName) throws IOException {
        subqueryDao.deleteQuery(queryName);
    }

    @Override
    public void editQuery(String queryName, String newQuery, String currentuser, String datetime) throws IOException {
        subqueryDao.editQuery(queryName, newQuery, currentuser, datetime);
    }


    @Override
    public List<Subqueries> getAllSubqueries() throws IOException {
        return subqueryDao.getAllSubqueries();
    }

    @Override
    public List<Subqueries> getByName(String queryName) throws IOException {
        return subqueryDao.getByName(queryName);
    }
}
