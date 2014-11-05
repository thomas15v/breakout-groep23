package edu.howest.breakout.client;

import edu.howest.breakout.client.Render.RenderBlock;
import edu.howest.breakout.game.Game;
import edu.howest.breakout.client.Render.Render;
import edu.howest.breakout.client.Render.RenderBall;
import edu.howest.breakout.game.entity.Entity;
import edu.howest.breakout.game.entity.EntityBall;
import edu.howest.breakout.game.entity.EntityBlock;
import edu.howest.breakout.game.info.GameState;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Constructor;
import java.util.*;
import java.util.List;

public class GameGrid extends JPanel implements Observer, Runnable  {

    private List<Render> renders = new ArrayList<Render>();
    private Map<Class<? extends Entity>, Class<? extends Render>> renderclasses = new HashMap<Class<? extends Entity>, Class<? extends Render>>();
    private Game game;
    private FpsCalculator fpsCalculator = new FpsCalculator();

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
        registerRender(EntityBlock.class, RenderBlock.class);
    }

    public void addEntity(Entity entity) throws Exception {
        System.out.println("added entity: " + entity.getClass().getName() );
        if (!renderclasses.containsKey(entity.getClass()))
            throw new ClassNotFoundException("We didn't found a renderclass for " + entity.getClass().getName() + ". I gues you forgot registering it!");
        Constructor c = renderclasses.get(entity.getClass()).getConstructor(Entity.class);
        c.setAccessible(true);
        renders.add((Render) c.newInstance(entity));
    }

    public void registerRender(Class entity, Class render){
            renderclasses.put(entity, render);
    }

    @Override
    protected void paintComponent(Graphics g) {
        fpsCalculator.tick();
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        paintDebug(graphics2D);
        for (Render e : renders)
            e.render(graphics2D);
    }

    private void paintDebug(Graphics2D g){
        g.drawString("fps: " + fpsCalculator.getFps(), 0,10);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    @Override
    public void run() {
        while (game.getGameState() == GameState.Running)
        {
            repaint();
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
