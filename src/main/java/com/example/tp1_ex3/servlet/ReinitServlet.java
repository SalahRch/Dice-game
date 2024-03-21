package com.example.tp1_ex3.servlet;


import com.example.tp1_ex3.model.Game;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/reinit")
public class ReinitServlet extends HttpServlet {


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Game game = (Game)request.getSession().getAttribute("game");
        game.reinit();
        getServletContext().getRequestDispatcher("/WEB-INF/views/Game.jsp").forward(request,response);
    }

}
