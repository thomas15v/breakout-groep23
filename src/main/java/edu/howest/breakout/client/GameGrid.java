package edu.howest.breakout.client;

import edu.howest.breakout.game.Game;
import edu.howest.breakout.client.Render.Render;
import edu.howest.breakout.client.Render.RenderBall;
import edu.howest.breakout.game.entity.Entity;
import edu.howest.breakout.game.entity.EntityBall;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class GameGrid extends JPanel implements Observer {

    private List<Render> entityList = new ArrayList<Render>();;
    private Game game;

    public GameGrid(Game game){
        this.game = game;
        this.game.addObserver(this);
        for (Entity entity : game.getEntities())
            addEntity(entity);
        setBackground(Color.white);
    }

    public void addEntity(Entity entity){
        System.out.println("added entity: " + entity );
        if (entity instanceof EntityBall)
            entityList.add(new RenderBall(entity));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        for (Render e : entityList)
            e.render(graphics2D);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
