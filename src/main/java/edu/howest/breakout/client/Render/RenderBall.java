package edu.howest.breakout.client.Render;

import edu.howest.breakout.game.entity.Entity;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class RenderBall extends Render {

    Ellipse2D ball;

    public RenderBall(Entity e) {
        super(e);
        ball = new Ellipse2D.Double(e.getX(), e.getY(),e.getWidth(),e.getHeight());
    }

    @Override
    public void render(Graphics2D g) {
        Entity e = getEntity();
        ball.setFrame(e.getX(), e.getY(),e.getWidth(),e.getHeight());
        g.setColor(e.getColor());
        g.fill(ball);
    }
}
