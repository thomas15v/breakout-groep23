package edu.howest.breakout.client;

import edu.howest.breakout.client.render.RenderBlock;
import edu.howest.breakout.game.Game;
import edu.howest.breakout.client.render.Render;
import edu.howest.breakout.client.render.RenderBall;
import edu.howest.breakout.game.entity.*;
import edu.howest.breakout.game.TickCalculator;
import edu.howest.breakout.game.info.GameState;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GameGrid extends JPanel implements Runnable {
    private Map<Class<? extends Entity>, Render> renders = new HashMap<Class<? extends Entity>, Render>();
    private Game game;
    private TickCalculator tickCalculator;
    private String title;

    public GameGrid(){
        registerRenders();
        setVisible(true);
        setBackground(Color.white);
        tickCalculator = new TickCalculator();
    }

    public GameGrid(Game game){
        this();
        setGame(game);
    }

    private void registerRenders(){
        RenderBlock renderBlock = new RenderBlock();
        registerRender(EntityBall.class, new RenderBall());
        registerRender(EntityBlock.class, renderBlock );
        registerRender(EntityPad.class, renderBlock );
    }

    public void registerRender(Class entity, Render render){
            renders.put(entity, render);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //graphics2D.scale(getHeight() / game.getDimension().getHeight(), getWidth() / game.getDimension().getWidth()); //NOPE NOPE NOPE NOPPPPE !!!
        Iterator<Entity> it = this.game.getEntities().iterator();
        while (it.hasNext()) {
            Entity entity = it.next();
            renders.get(entity.getClass()).render(graphics2D, entity);
        }
        paintDebug(graphics2D);
    }

    private void paintDebug(Graphics2D g){
        g.setColor(Color.BLACK);
        g.drawString("client fps: " + tickCalculator.getFps(), 0,30);
        g.drawString("client tickduration: " + tickCalculator.getDelay(), 0,40);
        g.drawString("server fps: " + game.getTickCalculator().getFps(), 0, 10);
        g.drawString("server tickduration: " + game.getTickCalculator().getDelay(), 0, 20);
        g.drawString("score " + game.getScore(), 0, 50);
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
        if (game.getGameState() == GameState.EndWon)
            setTitle("You won the Game!");
        else if (game.getGameState() == GameState.EndLost)
            setTitle("Game Over!");
        repaint();
        System.out.println("Client render Thread stopped!");
    }

    public void setGame(Game game) {
        setPreferredSize(game.getDimension());
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}