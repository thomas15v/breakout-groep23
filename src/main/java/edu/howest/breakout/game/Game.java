package edu.howest.breakout.game;

import edu.howest.breakout.game.difficulty.Difficulty;
import edu.howest.breakout.game.entity.Entity;
import edu.howest.breakout.game.entity.EntityBlock;
import edu.howest.breakout.game.entity.EntityPad;
import edu.howest.breakout.game.info.GameState;
import edu.howest.breakout.game.info.Level;
import edu.howest.breakout.game.input.InputManager;
import edu.howest.breakout.game.score.Player;
import edu.howest.breakout.game.score.ScoreManager;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

public abstract class Game extends Observable implements Runnable {
    private List<Entity> entities;
    protected GameState gameState;
    protected Dimension dimension = new Dimension(1000,700);
    private TickCalculator tickCalculator;
    private Logger logger = Logger.getLogger("GAME");
    private int lives;
    private LevelManager levelManager;
    private Difficulty difficulty;
    private boolean paused;
    private InputManager inputManager;
    private ScoreManager scoreManager;
    private boolean won;

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
        this(Arrays.asList(level), difficulty);
    }

    public Game(List<Level> levels, Difficulty difficulty){
        this(difficulty);
        this.levelManager = new LevelManager(levels);
        this.difficulty = difficulty;
        loadLevel(levelManager.getCurrentLevel());
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
        setChanged();
        notifyObservers(gameState);
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
        }else {
            setGameState(GameState.EndGame);
            won = false;
        }

    }

    public void loadLevel(Level level){
        for (Entity entity : getEntities())
            remove(entity);
        for (Entity entity : level.getBlockList())
            add(entity);
    }

    public void checkWin() {
        int blocksleft = 0;
        for (Entity e : this.getEntities()) {
            if (e instanceof EntityBlock && !(e instanceof EntityPad)) {
                blocksleft++;
            }
        }
        if (blocksleft == 1) {
            if (levelManager.hasNextLevel()) {
                loadLevel(levelManager.getNextLevel());
                getInputManager().clearPads();
                addPads(getScoreManager().getPlayers());
            }
            else {
                won = true;
                setGameState(GameState.EndGame);
            }
        }
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

    public boolean isWon() {
        return won;
    }

    public Level getLevel() {
        return levelManager.getCurrentLevel();
    }

    public abstract void addPads(List<Player> players);

}
