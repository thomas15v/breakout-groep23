package edu.howest.breakout.client.render;

import edu.howest.breakout.game.entity.Entity;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by thomas on 05/11/2014.
 */
public class RenderBlock implements Render {

    private Rectangle2D block;

    public RenderBlock() {
        this.block = new Rectangle2D.Double();
    }

    @Override
    public void render(Graphics2D g, Entity entity) {
        block.setFrame(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
        g.setColor(entity.getColor());
        g.fill(block);

        g.setColor(Color.black);
        g.draw(block);


    }
}
