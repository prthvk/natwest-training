package com.stackroute.springdi.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Student {

    private int id;
    private String name;
    private String email;

   /* @Autowired
    @Qualifier("hobby1")
     private Hobby hobby;*/

    public Student(int id, String name, String email, List<Hobby> hobbyList) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.hobbyList = hobbyList;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", hobbyList=" + hobbyList +
                '}';
    }

    public List<Hobby> getHobbyList() {
        return hobbyList;
    }

    public void setHobbyList(List<Hobby> hobbyList) {
        this.hobbyList = hobbyList;
    }

    @Autowired
    private List<Hobby> hobbyList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

  /*  public Hobby getHobby() {
        return hobby;
    }

    public void setHobby(Hobby hobby) {
        this.hobby = hobby;
    }*/

    public Student() {
    }

   /* public Student(int id, String name, String email, Hobby hobby) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", hobby=" + hobby +
                '}';
    }*/
}
