package com.harmreduction.dao;

import com.harmreduction.model.Subqueries;

import java.io.IOException;
import java.util.List;

public interface SubqueryDao {

    public void createQuery(Subqueries subquery) throws IOException;
    public void deleteQuery(String queryName) throws IOException;
    public void editQuery(String queryName, String newQuery, String currentuser, String datetime) throws IOException;
    public List<Subqueries> getAllSubqueries() throws IOException;
    public List<Subqueries> getByName(String queryName) throws IOException;
    public List<String> getIdByName(String queryName) throws IOException;
}
