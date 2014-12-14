package edu.howest.breakout.client.Render;

import edu.howest.breakout.game.entity.Entity;

import java.awt.*;

public abstract class Render {

    private Entity entity;

    public Render(Entity entity){
        this.entity = entity;
    }

    abstract public void render(Graphics2D g);

    public Entity getEntity() {
        return entity;
    }

    public boolean isDestroyed(){
        return getEntity().isDestroyed();
    }
}
