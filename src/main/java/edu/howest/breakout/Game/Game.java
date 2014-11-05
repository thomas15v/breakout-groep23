package edu.howest.breakout.game;

import edu.howest.breakout.game.entity.Entity;
import edu.howest.breakout.game.entity.EntityBall;
import edu.howest.breakout.game.entity.EntityBlock;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/***
 * Future class in case we would implement multiplayer
 */
public abstract class Game extends Observable implements Runnable {
    private GameProperties gameProperties;
    private List<Entity> entities;
    private GameState gameState;
    private Dimension dimension = new Dimension(500,500);

    public Game(GameProperties properties){
        this.entities = new ArrayList<Entity>();
        this.gameProperties = properties;
        entities.add(new EntityBall(10,10));
        entities.add(new EntityBlock(10,10, Color.black, 20,20));
        gameState = GameState.Running;
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

    public void tick(){
        for (Entity e : entities)
            e.tick(this);
        setChanged();
        notifyObservers();
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
