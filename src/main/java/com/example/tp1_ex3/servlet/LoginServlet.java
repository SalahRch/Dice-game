package com.example.tp1_ex3.servlet;

import com.example.tp1_ex3.helper.GameContextManagement;
import com.example.tp1_ex3.model.Game;
import com.example.tp1_ex3.model.Message;
import com.example.tp1_ex3.model.User;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdk.jfr.Frequency;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/WEB-INF/views/Login.jsp").forward(request, response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String success = "/WEB-INF/views/Game.jsp" ;
        String error = "/WEB-INF/views/Login.jsp";

        String username= request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        System.out.println(password);

        List<Message> messages = new ArrayList<>();


        //retrieve context that contains users:
        GameContextManagement ctx = GameContextManagement.getInstance(getServletContext());
        Optional<User> user = ctx.isFound(username.trim());
        if(user.isPresent()){
            if(user.get().getPassword().equals(password)) {

                Game game = new Game(user.get());

                //We store both the game and the user in the session for futur usage !
                request.getSession().setAttribute("user",user.get());
                request.getSession().setAttribute("game",game);

                getServletContext().getRequestDispatcher(success).forward(request, response);
            }
            else{
                Message msg = new Message("Password incorrect ! ");
                messages.add(msg);
                request.setAttribute("messages", messages);
                getServletContext().getRequestDispatcher(error).forward(request,response);
            }
        }else{
            Message msg = new Message("User not found");
            messages.add(msg);
            request.setAttribute("messages",messages);
            getServletContext().getRequestDispatcher(error).forward(request, response);
        }
    }
}
