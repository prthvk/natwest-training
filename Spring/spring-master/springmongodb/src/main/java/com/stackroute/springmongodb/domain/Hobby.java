package com.stackroute.springmongodb.domain;

public class Hobby {

    private String hobbyname;
    private String since;

    public Hobby() {
    }

    public String getHobbyname() {
        return hobbyname;
    }

    public void setHobbyname(String hobbyname) {
        this.hobbyname = hobbyname;
    }

    public String getSince() {
        return since;
    }

    public void setSince(String since) {
        this.since = since;
    }
}
