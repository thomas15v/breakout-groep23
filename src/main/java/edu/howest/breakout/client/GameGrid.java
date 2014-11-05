package edu.howest.breakout.client;

import edu.howest.breakout.game.Game;
import edu.howest.breakout.client.Render.Render;
import edu.howest.breakout.client.Render.RenderBall;
import edu.howest.breakout.game.entity.Entity;
import edu.howest.breakout.game.entity.EntityBall;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.List;

public class GameGrid extends JPanel implements Observer {

    private List<Render> renders = new ArrayList<Render>();
    private Map<Class<? extends Entity>, Class<? extends Render>> renderclasses = new HashMap<Class<? extends Entity>, Class<? extends Render>>();
    private Game game;

    public GameGrid(Game game) throws Exception {
        this.game = game;
        this.game.addObserver(this);
        registerRenders();
        for (Entity entity : game.getEntities())
            addEntity(entity);
        setBackground(Color.white);
    }

    private void registerRenders(){
        registerRender(EntityBall.class, RenderBall.class);
    }

    public void addEntity(Entity entity) throws Exception {
        System.out.println("added entity: " + entity.getClass().getName() );
        Constructor c = renderclasses.get(entity.getClass()).getConstructor(Entity.class);
        c.setAccessible(true);
        renders.add((Render) c.newInstance(entity));
    }

    public void registerRender(Class entity, Class render){
            renderclasses.put(entity, render);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        for (Render e : renders)
            e.render(graphics2D);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
