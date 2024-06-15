


package com.example.tp1_ex3.servlet;

import com.example.tp1_ex3.helper.GameContextManagement;
import com.example.tp1_ex3.model.Game;
import com.example.tp1_ex3.model.Message;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author Salah rch
 */

@WebServlet("/Game")
public class GameServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Game game = (Game) request.getSession().getAttribute("game");
        game.reinit();
        getServletContext().getRequestDispatcher("/WEB-INF/views/Game.jsp").forward(request,response);
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Retrieve number of dice and generate value back
        int diceNumber = Integer.parseInt(request.getParameter("diceNumber"));
        System.out.println("dice number : " + diceNumber);
        List<Message> messageList = new ArrayList<>();

        GameContextManagement ctx = GameContextManagement.getInstance(getServletContext());

        boolean sixvalued = false;

        //retrieve the game from the session
        Game game = (Game) request.getSession().getAttribute("game");
        //check if game is not over yet !
        if(!game.getIsOver()) {
            //check if the dice is already rolled or not
            if (!game.isRolled(diceNumber)) {
                //roll the dice corresponding to the choice
                game.rollDice(diceNumber);
                //check if any of the first and second dice rolled a 6 value -> Game over !
                sixvalued = game.sixValued();
                //check if the dices values are in ascending order if not -> Game over !
                game.AreDicesInAscendingOrder();
            } else {
                //if the dice is arleady rolled -> score  -1 !
                Message message = new Message("Dice already rolled -> Game over !  ");
                messageList.add(message);
                //set the user score to -1 !
                game.getUser().setScore(-1);
                //set the game over to true !
                game.setOver(true);
                request.setAttribute("messages", messageList);
            }
            if(sixvalued){
                Message message = new Message("6 rolled -> Game over !");
                messageList.add(message);
                request.setAttribute("messages", messageList);
            }
        }
        //if all the dices are rolled and the game is won -> Dices values are in ascending order
        if(game.Allrolled() && game.getIsWon()){
            //set the score to the sum of value of dices
            game.getUser().setScore(game.sumScore());
            //check if the score of the user is bigger than best score
            if(game.getUser().getBestscore() < game.getUser().getScore()){
                game.getUser().setBestscore(game.getUser().getScore());
            }
            //update the user infos in our context
            ctx.updateUser(game.getUser().getUsername(), game.getUser().getBestscore());
            //set the game over to true
            game.setOver(true);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/views/Game.jsp").forward(request,response);
    }
}


