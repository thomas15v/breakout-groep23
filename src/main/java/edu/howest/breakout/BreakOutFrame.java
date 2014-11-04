package edu.howest.breakout;

import edu.howest.breakout.client.GameGrid;
import edu.howest.breakout.game.Game;
import edu.howest.breakout.game.GameProperties;
import edu.howest.breakout.game.LocalGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BreakOutFrame extends JFrame {

    private Game game;
    private GameGrid gameGrid;

    public BreakOutFrame(){
        setPreferredSize(new Dimension(700,700));
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                game.setRunning(false);
            }
        });
        this.game = new LocalGame(GameProperties.BASIC_PROPERTIES);
        this.gameGrid = new GameGrid(game);
        add(gameGrid);
        pack();
        game.run();
    }

    public static void main(String[] args){
        BreakOutFrame frame = new BreakOutFrame();
    }

    public Game getGame() {
        return game;
    }

    public GameGrid getGameGrid() {
        return gameGrid;
    }
}
