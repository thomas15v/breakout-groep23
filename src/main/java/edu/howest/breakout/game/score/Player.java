package edu.howest.breakout.game.score;

/**
 * Created by thomas15v on 18/12/14.
 */
public class Player {
    private int id = -1;
    private int score;
    private String playerName;

    public Player(int id, String playerName, int score){
        this.id = id;
        this.playerName = playerName;
        this.score = score;
    }

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
