package com.example.tp1_ex3.model;

public class Message {
    private String contenu ;

    public Message(String message){
        this.contenu = message;
    }

    public String getContenu() {
        return contenu;
    }

    @Override
    public String toString() {
        return contenu;
    }
}
