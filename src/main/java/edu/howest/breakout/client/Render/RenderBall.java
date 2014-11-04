package edu.howest.breakout.client.Render;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class RenderBall implements Render {

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval(0,0,20,20);
    }
}
