package com.harmreduction.model;


import io.searchbox.annotations.JestId;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class StackExchange {

    private String name;
    private Date year;
    private String post;

    @JestId
    private String documentId;

    public String getDocumentId() {
        return documentId;
    }
    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getYear() {
        return year;
    }
    public void setYear(Date year) {
        this.year = year;
    }
    public String getPost() {
        return post;
    }
    public void setPost(String post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
