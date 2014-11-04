package edu.howest.breakout.client;

import edu.howest.breakout.Game.Game;
import edu.howest.breakout.client.Render.Render;
import edu.howest.breakout.client.Render.RenderBall;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class GameGrid extends JPanel implements Observer {

    private List<Render> entityList;
    private Game game;

    public GameGrid(Game game){
        this.game = game;
        entityList = new ArrayList<Render>();
        entityList.add(new RenderBall());
        setBackground(Color.white);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Render e : entityList)
            e.render(g);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
