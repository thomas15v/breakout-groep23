package edu.howest.breakout.client;

import edu.howest.breakout.client.Render.RenderBlock;
import edu.howest.breakout.game.Game;
import edu.howest.breakout.client.Render.Render;
import edu.howest.breakout.client.Render.RenderBall;
import edu.howest.breakout.game.entity.*;
import edu.howest.breakout.game.TickCalculator;
import edu.howest.breakout.game.info.GameState;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Constructor;
import java.util.*;
import java.util.List;

public class GameGrid extends JPanel implements Runnable, Observer  {

    private List<Render> renders = new ArrayList<Render>();
    private Map<Class<? extends Entity>, Class<? extends Render>> renderclasses = new HashMap<Class<? extends Entity>, Class<? extends Render>>();
    private Game game;
    private TickCalculator tickCalculator;

    public GameGrid(Game game) throws Exception {
        this.game = game;
        registerRenders();
        game.addObserver(this);
        for (Entity entity : game.getEntities())
            addEntity(entity);
        setBackground(Color.white);
        tickCalculator = new TickCalculator();
    }

    private void registerRenders(){
        registerRender(EntityBall.class, RenderBall.class);
        registerRender(EntityBlock.class, RenderBlock.class);
        registerRender(EntityPad.class, RenderBlock.class);
    }

    public void addEntity(Entity entity) throws Exception {
        if (!renderclasses.containsKey(entity.getClass()))
            throw new ClassNotFoundException("We didn't find a renderclass for " + entity.getClass().getName() + ". I gues you forgot registering it!");
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
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Iterator<Render> it = this.renders.iterator();
        while (it.hasNext()) {
            Render r = it.next();
            if (r.isDestroyed())
                it.remove();
            else
                r.render(graphics2D);
        }
        paintDebug(graphics2D);
    }

    private void paintDebug(Graphics2D g){
        g.setColor(Color.BLACK);
        g.drawString("server fps: " + game.getTickCalculator().getFps(), 0,10);
        g.drawString("server tickduration: " + game.getTickCalculator().getDelay(), 0,20);
        g.drawString("client fps: " + tickCalculator.getFps(), 0,30);
        g.drawString("client tickduration: " + tickCalculator.getDelay(), 0,40);
    }

    @Override
    public void run() {
        while (game.getGameState() == GameState.Running)
        {
            repaint();
            tickCalculator.tick();
            try {
                Thread.sleep(tickCalculator.getDelay());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Client Render Thread stopped!");
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Entity)
            try {
                addEntity((Entity) arg);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
