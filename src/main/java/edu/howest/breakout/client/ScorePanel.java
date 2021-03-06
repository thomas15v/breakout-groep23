package edu.howest.breakout.client;

import edu.howest.breakout.game.Game;
import edu.howest.breakout.game.info.Level;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by thomas15v on 17/12/14.
 */
public class ScorePanel extends JPanel implements Observer {

    private Game game;
    private JLabel levelLabel;
    private JLabel scoreLabel;
    private JPanel rootPannel;
    private JLabel lifesLabel;

    public ScorePanel(Game game){
        this();
        setGame(game);

    }

    public ScorePanel(){
        add(rootPannel);
    }

    public void setGame(Game game) {
        this.game = game;
        game.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        setScoreLabel(game.getScoreManager().toString());
        setLifesLabel(game.getLives());
        setLevelLabel(game.getLevel());
    }

    public void setScoreLabel(String value) {
        this.scoreLabel.setText(value);
    }
    public void setLifesLabel(int value) {
        this.lifesLabel.setText(String.format("Lives: %d", value));
    }
    public void setLevelLabel(Level level){
        levelLabel.setText(level.toString());
    }
}
