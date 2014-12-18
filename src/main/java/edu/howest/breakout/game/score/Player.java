package edu.howest.breakout.game.score;

/**
 * Created by thomas15v on 18/12/14.
 */
public class Player {
    private int score;
    private String playerName;

    public Player(String playerName){
        this.playerName = playerName;
        this.score = 0;
    }

    @Override
    public String toString() {
        return playerName + " : " + score;
    }

    public void addScore(int value) {
        score+=value;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }
}
