package edu.howest.breakout.game;

import edu.howest.breakout.game.entity.Entity;
import edu.howest.breakout.game.entity.EntityBall;
import edu.howest.breakout.game.entity.EntityBlock;
import edu.howest.breakout.game.info.GameProperties;
import edu.howest.breakout.game.info.GameState;

import java.awt.*;

/**
 * Created by thomas on 04/11/2014.
 */
public class LocalGame extends Game {

    public LocalGame(GameProperties properties) {
        super(properties);
        entities.add(new EntityBall(10,10));
        entities.add(new EntityBlock(10,10, Color.black, 20,20));
    }

    @Override
    public void run() {
        while (gameState == GameState.Running) {
            try {
                tick();
                Thread.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
                gameState = GameState.Errored;
            }
        }
        notifyObservers();
    }

    private void tick(){
        for (Entity e : entities)
            e.tick(this);
        setChanged();
        notifyObservers();
    }
}
