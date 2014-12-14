package edu.howest.breakout.game;

import edu.howest.breakout.client.FpsCalculator;
import edu.howest.breakout.game.entity.Entity;
import edu.howest.breakout.game.entity.EntityBall;
import edu.howest.breakout.game.entity.EntityBlock;
import edu.howest.breakout.game.entity.EntityPanel;
import edu.howest.breakout.game.info.GameProperties;
import edu.howest.breakout.game.info.GameState;

import java.awt.*;
import java.util.Iterator;

/**
 * Created by thomas on 04/11/2014.
 */
public class LocalGame extends Game implements Runnable {


    FpsCalculator fpsCalculator = new FpsCalculator();

    public LocalGame(GameProperties properties) {
        super(properties);
        getEntities().add(new EntityBall(250, 400));

        for (int x = 0; x < 8; x++)
            for (int y = 0; y < 4; y++)
                getEntities().add(new EntityBlock(x * 52 + 10, y * 52 + 10, Color.black, 50, 50));

        getEntities().add(new EntityPanel(50, 440, Color.RED, 150, 15));

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
        Iterator<Entity> it = getEntities().iterator();
        while (it.hasNext()){
            Entity e = it.next();
            e.tick(this);
            if (e.isDestroyed()) {
                System.out.println("remove " + e);
                getEntities().remove(e);
            }
        }
        setChanged();
        fpsCalculator.tick();
        notifyObservers();
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
