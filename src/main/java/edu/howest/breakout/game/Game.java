package edu.howest.breakout.game;

import edu.howest.breakout.game.difficulty.Difficulty;
import edu.howest.breakout.game.entity.Entity;
import edu.howest.breakout.game.entity.EntityBall;
import edu.howest.breakout.game.entity.EntityPad;
import edu.howest.breakout.game.info.GameProperties;
import edu.howest.breakout.game.info.GameState;
import edu.howest.breakout.game.info.Level;
import edu.howest.breakout.game.input.InputManager;
import edu.howest.breakout.game.score.ScoreManager;

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
    private Difficulty difficulty;
    private boolean paused;
    private InputManager inputManager;
    private ScoreManager scoreManager;

    public Game(Difficulty difficulty){
        this.entities = new CopyOnWriteArrayList<Entity>();
        gameState = GameState.Running;
        tickCalculator = new TickCalculator();
        lives = 5;
        this.difficulty = difficulty;
        this.inputManager = new InputManager();
        this.scoreManager = new ScoreManager();
    }

    public Game(Level level, Difficulty difficulty){
        this(difficulty);
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
        if (lives > 0) {
            lives -= 1;
        }else
            setGameState(GameState.EndLost);

    }

    public void loadLevel(Level level){
        for (Entity entity : getEntities())
            remove(entity);
        for (Entity entity : level.getBlockList())
            add(entity);
    }

    @Deprecated
    public void addScore(int i){
        score+=i;
        setChanged();
        notifyObservers();
    }

    @Deprecated
    public int getScore() {
        return score;
    }

    public int getLives(){return lives;}

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public InputManager getInputManager() {
        return inputManager;
    }

    public ScoreManager getScoreManager() {
        return scoreManager;
    }
}
