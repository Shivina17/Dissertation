package com.harmreduction.service;


import com.harmreduction.model.Subqueries;

import java.io.IOException;
import java.util.List;

public interface SubqueryService {

    public void createQuery(Subqueries subquery) throws IOException;
    public void deleteQuery(String queryName) throws IOException;
    public void editQuery(String queryName, String newQuery, String currentuser, String datetime) throws IOException;

    public List<Subqueries> getAllSubqueries() throws IOException;
    public List<Subqueries> getByName(String queryName) throws IOException;
}
