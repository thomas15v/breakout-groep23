package edu.howest.breakout.game;

import edu.howest.breakout.game.entity.Entity;
import edu.howest.breakout.game.entity.EntityBall;
import edu.howest.breakout.game.entity.EntityBlock;
import edu.howest.breakout.game.info.GameProperties;
import edu.howest.breakout.game.info.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by thomas on 04/11/2014.
 */
public class LocalGame extends Game  implements ActionListener {

    private long clock = 100/6;
    private Timer timer;

    public LocalGame(GameProperties properties) {
        super(properties);
        entities.add(new EntityBall(10,10));
        entities.add(new EntityBlock(10,10, Color.black, 20,20));
    }

    @Deprecated
    public void run1() {
        while (gameState == GameState.Running) {
            try {
                tick();
                Thread.sleep(clock);
            } catch (Exception e) {
                e.printStackTrace();
                gameState = GameState.Errored;
            }
        }
    }

    private void tick(){
        for (Entity e : entities)
            e.tick(this);
        setChanged();
        notifyObservers();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            tick();
        }catch (Exception err){
            err.printStackTrace();
            this.setGameState(GameState.Errored);
        }
    }

    @Override
    public void setGameState(GameState gameState) {
        if (gameState != GameState.Running)
            timer.stop();
        super.setGameState(gameState);
    }

    public void run(){
        int clock = 95/6;
        timer = new Timer(0, this);
        timer.start();
    }
}
