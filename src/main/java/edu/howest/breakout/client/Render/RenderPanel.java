package edu.howest.breakout.client.Render;

import edu.howest.breakout.game.entity.Entity;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class RenderPanel extends Render {

    private Rectangle2D panel;

    public RenderPanel(Entity e) {
        super(e);
        this.panel = new Rectangle2D.Double(e.getX(), e.getY(), e.getSizex(), e.getSizey());
    }

    @Override
    public void render(Graphics2D g) {
        Entity e = getEntity();
        if (!e.isDestroyed()) {
            panel.setFrame(e.getX(), e.getY(), e.getSizex(), e.getSizey());
            g.setColor(e.getColor());
            g.fill(panel);
        }
    }
}
