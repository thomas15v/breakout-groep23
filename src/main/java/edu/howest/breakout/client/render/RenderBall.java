package edu.howest.breakout.client.render;

import edu.howest.breakout.game.entity.Entity;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class RenderBall implements Render {

    Ellipse2D ball;

    public RenderBall() {
        ball = new Ellipse2D.Double();
    }

    @Override
    public void render(Graphics2D g, Entity entity) {
        ball.setFrame(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
        g.setColor(entity.getColor());
        g.fill(ball);
        g.setColor(Color.black);
        g.draw(ball);
    }

}
