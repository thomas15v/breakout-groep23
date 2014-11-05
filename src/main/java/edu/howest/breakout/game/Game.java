package edu.howest.breakout.game;

import edu.howest.breakout.game.entity.Entity;
import edu.howest.breakout.game.info.GameProperties;
import edu.howest.breakout.game.info.GameState;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/***
 * Future class in case we would implement multiplayer
 */
public abstract class Game extends Observable implements Runnable {
    protected GameProperties gameProperties;
    protected List<Entity> entities;
    protected GameState gameState;
    protected Dimension dimension = new Dimension(500,500);

    public Game(GameProperties properties){
        this.entities = new ArrayList<Entity>();
        this.gameProperties = properties;
        gameState = GameState.Running;
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
