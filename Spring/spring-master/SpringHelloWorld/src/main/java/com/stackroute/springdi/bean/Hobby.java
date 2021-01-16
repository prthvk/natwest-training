package com.stackroute.springdi.bean;

import org.springframework.stereotype.Component;


public class Hobby {

    private int id;
    private String hobbyName;

    public Hobby(int id, String hobbyName) {
        System.out.println("In param Conts. of Hobby");
        this.id = id;
        this.hobbyName = hobbyName;
    }

    public Hobby() {
        System.out.println("In default of Hobby");
    }

    public void init(){
        System.out.println(" In Init");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHobbyName() {
        return hobbyName;
    }

    public void setHobbyName(String hobbyName) {
        this.hobbyName = hobbyName;
    }

    @Override
    public String toString() {
        return "Hobby{" +
                "id=" + id +
                ", hobbyName='" + hobbyName + '\'' +
                '}';
    }
}
