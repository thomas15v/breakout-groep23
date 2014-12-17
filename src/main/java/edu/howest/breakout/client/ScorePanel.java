package edu.howest.breakout.client;

import edu.howest.breakout.game.Game;

import javax.swing.*;

/**
 * Created by thomas15v on 17/12/14.
 */
public class ScorePanel extends JPanel {

    private Game game;
    private JLabel levelLabel;
    private JLabel scoreLabel;
    private JPanel rootPannel;
    private JLabel LifesLabel;

    public ScorePanel(Game game){
        this();
        setGame(game);
    }

    public ScorePanel(){
        add(rootPannel);
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
