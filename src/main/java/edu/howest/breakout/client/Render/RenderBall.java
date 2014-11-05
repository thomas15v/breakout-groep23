package edu.howest.breakout.client.Render;

import edu.howest.breakout.game.entity.Entity;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class RenderBall extends Render {

    public RenderBall(Entity entity) {
        super(entity);
    }

    @Override
    public void render(Graphics2D g) {
        Entity e = getEntity();
        g.setColor(e.getColor());
        g.fill(new Ellipse2D.Double(e.getX(), e.getY(),e.getSizex(),e.getSizey()));
    }
}
