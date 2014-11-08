package edu.howest.breakout.game;

import edu.howest.breakout.client.FpsCalculator;
import edu.howest.breakout.game.entity.Entity;
import edu.howest.breakout.game.info.GameProperties;
import edu.howest.breakout.game.info.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.CopyOnWriteArrayList;

/***
 * Future class in case we would implement multiplayer
 */
public abstract class Game extends Observable {
    protected GameProperties gameProperties;
    private List<Entity> entities;
    private List<Entity> removeentities;
    protected GameState gameState;
    protected Dimension dimension = new Dimension(500,500);

    public Game(GameProperties properties){
        this.entities = new CopyOnWriteArrayList<Entity>();
        this.gameProperties = properties;
        gameState = GameState.Running;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void add(Entity entity){
        entities.add(entity);
    }

    public void remove(Entity entity){
        removeentities.add(entity);
    }

    public Dimension getDimension() {
        return dimension;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        notifyObservers();
    }

    public abstract FpsCalculator getFpsCalculator();

    public abstract void run();

}
