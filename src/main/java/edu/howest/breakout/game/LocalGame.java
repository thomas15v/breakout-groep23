package edu.howest.breakout.game;

import edu.howest.breakout.client.FpsCalculator;
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
public class LocalGame extends Game implements Runnable {

    private long clock = 100/6;
    private Timer timer;
    FpsCalculator fpsCalculator = new FpsCalculator();

    public LocalGame(GameProperties properties) {
        super(properties);
        for (int i = 0; i < 3000; i++)
            entities.add(new EntityBall(10,10));
        entities.add(new EntityBlock(10,10, Color.black, 20,20));
    }

    public void run() {
        while (gameState == GameState.Running) {
            try {
                tick();
                Thread.sleep(fpsCalculator.getDelay());
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
        fpsCalculator.tick();
        notifyObservers();
    }

    @Deprecated
    public void actionPerformed(ActionEvent e) {
        try {
            tick();
            timer.setDelay(fpsCalculator.getDelay());
        }catch (Exception err){
            err.printStackTrace();
            this.setGameState(GameState.Errored);
        }
    }

    @Override
    public void setGameState(GameState gameState) {
        if (gameState != GameState.Running) {
            System.out.println("Game Thread stopped!");
        }
        super.setGameState(gameState);

    }

    @Override
    public FpsCalculator getFpsCalculator() {
        return fpsCalculator;
    }
}
