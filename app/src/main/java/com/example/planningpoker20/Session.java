package com.example.planningpoker20;

import java.util.ArrayList;

public class Session {

   private String id;
   private String sessionName;
   private ArrayList< Question > questions;
   private ArrayList< User > users;

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", sessionName='" + sessionName + '\'' +
                ", questions=" + questions +
                ", users=" + users +
                '}';
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
