package edu.howest.breakout.client.Render;

import edu.howest.breakout.game.entity.Entity;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by thomas on 05/11/2014.
 */
public class RenderBlock extends Render {

    public RenderBlock(Entity entity) {
        super(entity);
    }

    @Override
    public void render(Graphics2D g) {
        Entity e = getEntity();
        g.setColor(e.getColor());
        g.fill(new Rectangle2D.Double(e.getX(), e.getY(), e.getX() + e.getSizex(), e.getY() + e.getSizey()));
    }
}
