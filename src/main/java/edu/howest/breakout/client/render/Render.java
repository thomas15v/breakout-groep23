package edu.howest.breakout.client.render;

import edu.howest.breakout.game.entity.Entity;

import java.awt.*;

public interface Render {

    void render(Graphics2D g, Entity entity);

}
