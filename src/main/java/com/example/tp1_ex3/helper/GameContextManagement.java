package com.example.tp1_ex3.helper;

import com.example.tp1_ex3.model.User;
import jakarta.servlet.ServletContext;

import java.util.List;
import java.util.Optional;

public class GameContextManagement {
    /**
     * Singleton pattern
     */
    private ServletContext context;

    public static GameContextManagement instance;

    private GameContextManagement(ServletContext context) {
        this.context = context;
    }

    synchronized public static final GameContextManagement getInstance(ServletContext context){
        if (instance == null) {
            instance = new GameContextManagement(context);
        }
        return instance;
    }
    public List<User> retrieveUsers(){
        return (List<User>) context.getAttribute("users");
    }

    /**
     * Using Stream API to filter the list of users !
     *
     * @param username of the user to find
     * @return Optional<User>that we can check later if it's present using isPresent then retrieve
     * using the get method !
     */
    public Optional<User> isFound(String username){
        List<User> userList = retrieveUsers();
        return userList.stream().filter(user -> user.getUsername().equals(username)).findFirst();
    }

    /**
     * Using standard method to iterate trought the list and update the
     * user corresponding to the username !
     * @param username -> to fetch
     * @param bestScore -> to assign
     */

    public void updateUser(String username, int bestScore){
        List<User> userList= retrieveUsers();
        for(User user : userList){
            if(user.getUsername().equals(username)){
                user.setBestscore(bestScore);
            }
        }
    }


    public void addUser(User user){
        List<User> userList= retrieveUsers();
        userList.add(user);
        System.out.println(user);
    }





}
