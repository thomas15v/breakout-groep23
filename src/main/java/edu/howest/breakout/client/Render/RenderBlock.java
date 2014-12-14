package edu.howest.breakout.client.Render;

import edu.howest.breakout.game.entity.Entity;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by thomas on 05/11/2014.
 */
public class RenderBlock extends Render {

    private Rectangle2D block;

    public RenderBlock(Entity e) {
        super(e);
        this.block = new Rectangle2D.Double(e.getX(), e.getY(), e.getSizex(), e.getSizey());
    }

    @Override
    public void render(Graphics2D g) {
        Entity e = getEntity();
        block.setFrame(e.getX(), e.getY(), e.getSizex(), e.getSizey());
        g.setColor(e.getColor());
        g.fill(block);
    }
}
