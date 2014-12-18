package edu.howest.breakout.game;

import edu.howest.breakout.game.info.Level;

import java.util.List;

/**
 * Created by thomas15v on 18/12/14.
 */
public class LevelManager {

    public int currentLevel;
    public List<Level> levels;

    public LevelManager(List<Level> levels){
        this.levels = levels;
    }

    public Level getCurrentLevel(){
        return levels.get(currentLevel);
    }

    public boolean hasNextLevel(){
        return currentLevel  < levels.size() - 1;
    }

    public Level getNextLevel(){
        currentLevel++;
        return getCurrentLevel();
    }

}
