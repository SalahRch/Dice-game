package com.example.tp1_ex3.servlet;

import com.example.tp1_ex3.helper.GameContextManagement;
import com.example.tp1_ex3.model.Message;
import com.example.tp1_ex3.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("create") != null){
            getServletContext().getRequestDispatcher("/WEB-INF/views/Register.jsp").forward(request,response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //Retrieve infos
        String name = request.getParameter("name");
        String fullname = request.getParameter("fullName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User(name,fullname,username,password,0,0);
        //retrieve ctx to store user in userList :
        List<Message> messageList = new ArrayList<>();

        GameContextManagement ctx = GameContextManagement.getInstance(getServletContext());
        //check if username already exists
        Optional<User> userOptional = ctx.isFound(username);
        Message message;
        if(userOptional.isEmpty()){
            message = new Message("User registered !");
            messageList.add(message);
            request.setAttribute("messages", messageList);
            ctx.addUser(user);
        }
        else {
            message = new Message("Username already exists bozo");
            messageList.add(message);
            request.setAttribute("messages", messageList);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/views/Login.jsp").forward(request,response);
    }

}
