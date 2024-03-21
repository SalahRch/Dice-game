package com.example.tp1_ex3.model;

import java.util.Comparator;
import java.util.Random;

public class Dice  {

    private int order;
    private int value;
    private boolean rolled = false;



    public Dice(int order){
        this.order = order;
        this.value =0;
    }



    public int roll(){
        Random random = new Random();
        this.value=  random.nextInt(6)+1;
        this.rolled = true;
        return this.value;
    }
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isRolled() {
        return rolled;
    }

    public void setRolled(boolean rolled) {
        this.rolled = rolled;
    }


}
