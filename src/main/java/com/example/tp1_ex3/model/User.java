package com.example.tp1_ex3.model;

public class User {

    private String name;
    private String fullname;
    private String username;
    private String password;
    private int bestscore ;
    private int score;

    public User(String name, String fullname, String username,String password,int score, int bestscore) {
        this.name = name;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.score = score;
        this.bestscore = bestscore;
    }





    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }



    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", fullname='" + fullname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", bestscore=" + bestscore +
                ", score=" + score +
                '}';
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getBestscore() {
        return bestscore;
    }

    public void setBestscore(int bestscore) {
        this.bestscore = bestscore;
    }
}
