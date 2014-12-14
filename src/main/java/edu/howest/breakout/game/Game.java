package edu.howest.breakout.game;

import edu.howest.breakout.game.entity.Entity;
import edu.howest.breakout.game.info.GameProperties;
import edu.howest.breakout.game.info.GameState;

import java.awt.*;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

public abstract class Game extends Observable implements Runnable {
    protected GameProperties gameProperties;
    private List<Entity> entities;
    private List<Entity> removeentities;
    protected GameState gameState;
    protected Dimension dimension = new Dimension(500,500);
    private TickCalculator tickCalculator;
    private Logger logger = Logger.getLogger("GAME");

    public Game(GameProperties properties){
        this.entities = new CopyOnWriteArrayList<Entity>();
        this.gameProperties = properties;
        gameState = GameState.Running;
        tickCalculator = new TickCalculator();
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

    public TickCalculator getTickCalculator() {
        return tickCalculator;
    }

    public Logger getLogger() {
        return logger;
    }
}
