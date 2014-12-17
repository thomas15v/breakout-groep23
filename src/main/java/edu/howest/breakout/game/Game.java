package edu.howest.breakout.game;

import edu.howest.breakout.game.entity.Entity;
import edu.howest.breakout.game.entity.EntityBall;
import edu.howest.breakout.game.info.GameProperties;
import edu.howest.breakout.game.info.GameState;
import edu.howest.breakout.game.info.Level;

import java.awt.*;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

public abstract class Game extends Observable implements Runnable {
    private List<Entity> entities;
    private Level level;
    protected GameState gameState;
    protected Dimension dimension = new Dimension(1000,700);
    private TickCalculator tickCalculator;
    private Logger logger = Logger.getLogger("GAME");
    private int score;
    private int lives;

    public Game(){
        this.entities = new CopyOnWriteArrayList<Entity>();
        gameState = GameState.Running;
        tickCalculator = new TickCalculator();
        lives = 5;
    }

    public Game(Level level){
        this();
        this.level = level;
        loadLevel(level);
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void add(Entity entity){
        entities.add(entity);
        System.out.println("add " + entity);
        setChanged();
        notifyObservers(entity);
    }

    public void remove(Entity entity){
        System.out.println("remove " + entity);
        entities.remove(entity);
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

    public void lostlife(){
        add(new EntityBall(250, 400));
        lives-=1;

    }

    public void loadLevel(Level level){
        for (Entity entity : getEntities())
            remove(entity);
        for (Entity entity : level.getBlockList())
            add(entity);
    }

    public void addScore(int i){
        score+=i;
        setChanged();
        notifyObservers();
    }

    public int getScore() {
        return score;
    }

    public int getLives(){return lives;}
}
