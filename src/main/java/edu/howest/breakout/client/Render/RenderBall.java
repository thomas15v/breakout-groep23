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
        g.setColor(Color.BLACK);
        g.fill(new Ellipse2D.Double(getEntity().getX(), getEntity().getY(), 20,20));
    }
}
