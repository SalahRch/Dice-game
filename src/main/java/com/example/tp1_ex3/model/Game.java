package com.example.tp1_ex3.model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private User user;
    private boolean isOver;
    private List<Dice> dices = new ArrayList<>();
    private boolean isWon = false;


    public boolean getIsWon() {
        return isWon;
    }

    public void setWon(boolean won) {
        isWon = won;
    }

    public Game(User user) {
        this.user= user;
        addDices();
        this.isOver = false;
    }

    public List<Dice> getDices() {
        return dices;
    }

    public void addDices(){
        Dice dice1 = new Dice(1);
        Dice dice2 = new Dice(2);
        Dice dice3 = new Dice(3);
        dices.add(dice1);
        dices.add(dice2);
        dices.add(dice3);
    }
    public void reinit(){
        this.isOver = false;
        this.setWon(false);
        this.user.setScore(0);
        for(Dice dice : dices){
            dice.setRolled(false);
            dice.setValue(0);
        }
    }


    public void play(int choice){
      if(!isOver){
          if(!isRolled(choice)){
              rollDice(choice);
          }
      }
    }
    public int rollDice(int choice){
        for(Dice dice : dices){
            if(dice.getOrder() == choice){
                if(!dice.isRolled()) {
                    return dice.roll();
                }
                else return 0;
            }
        }
        return 0;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean getIsOver() {
        return isOver;
    }

    public void setOver(boolean over) {
        isOver = over;
    }

    public void setDices(List<Dice> dices) {
        this.dices = dices;
    }

    public boolean isRolled(int choice){
        for(Dice dice : dices){
            if(dice.getOrder() == choice){
                return dice.isRolled();
            }
        }
        return false;
    }

    public int sumScore(){
        int sum = 0;
        for(Dice dice: dices){
            sum+= dice.getValue();
        }
        return sum;
    }

    public boolean Allrolled(){
        for(Dice dice : dices){
            if(!dice.isRolled()){
                return false;
            }
        }
        return true;
    }
    public boolean sixValued(){
        for(Dice dice: dices){
            if(dice.getOrder() == 1 || dice.getOrder() == 2){
                if(dice.getValue() == 6){
                    setWon(false);
                    setOver(true);
                    return true;
                }
            }
        }
        return false;
    }

    public void AreDicesInAscendingOrder(){
        //iterate trought the dices in order and check if value is lower than the next one
        int previousval = 0;
        for(Dice dice : dices){
            if(dice.isRolled()){
                System.out.println("dice value :"+ dice.getValue() + "dice order:"+ dice.getOrder());
                if(dice.getValue() > previousval){
                    previousval = dice.getValue();
                }else{
                    setWon(false);
                    setOver(true);
                    return;
                }
            }
        }
        if(Allrolled()) {
            setWon(true);
        }
    }










}
