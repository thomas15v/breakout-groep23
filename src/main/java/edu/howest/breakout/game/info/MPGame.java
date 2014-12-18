package edu.howest.breakout.game.info;

import edu.howest.breakout.game.score.Player;

/**
 * Created by thomas15v on 18/12/14.
 */
public class MPGame {

    private Player player1;
    private Player player2;

    public MPGame(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
    }

    public int getTotalScore(){
        return player1.getScore() + player2.getScore();
    }

    @Override
    public String toString() {
        return String.format("Score: %d  %s  %s", getTotalScore(), player1, player2);
    }
}

