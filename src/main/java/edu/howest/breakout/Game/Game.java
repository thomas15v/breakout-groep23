package edu.howest.breakout.game;

import edu.howest.breakout.game.entity.Entity;
import edu.howest.breakout.game.entity.EntityBall;

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
    private boolean running;
    private Dimension dimension = new Dimension(500,500);

    public Game(GameProperties properties){
        this.entities = new ArrayList<Entity>();
        this.gameProperties = properties;
        running = true;
        entities.add(new EntityBall(10,10));
    }

    @Override
    public void run() {
        while (running) {
            tick();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void tick(){
        for (Entity e : entities)
            e.tick(this);
        setChanged();
        notifyObservers();
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public Dimension getDimension() {
        return dimension;
    }
}
