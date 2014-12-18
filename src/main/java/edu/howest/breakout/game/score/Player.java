package edu.howest.breakout.game.score;

/**
 * Created by thomas15v on 18/12/14.
 */
public class Player {
    private int score;
    private String name;

    public Player(String name){
        this.name = name;
        this.score = 0;
    }

    @Override
    public String toString() {
        return name + " : " + score;
    }

    public void addScore(int value) {
        score+=value;
    }
}
