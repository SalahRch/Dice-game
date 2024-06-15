package com.example.tp1_ex3.listener;

import com.example.tp1_ex3.model.User;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@WebListener
public class GameInitializer implements ServletContextListener {

    public GameInitializer(){


    }

    /**
     *  intializes the context of the application with a synchronzedList of users !
     *
     * @param sce the ServletContextEvent containing the ServletContext that is being initialized
     *
     */

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext context = sce.getServletContext();
        System.out.println("Preconfig initalized ! ");
        List<User> userList = Collections.synchronizedList(new ArrayList<>());
        context.setAttribute("users", userList);

    }
}
