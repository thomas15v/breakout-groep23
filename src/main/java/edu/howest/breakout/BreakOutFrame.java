package edu.howest.breakout;

import edu.howest.breakout.client.GameGrid;
import edu.howest.breakout.game.Database;
import edu.howest.breakout.game.Game;
import edu.howest.breakout.game.info.GameProperties;
import edu.howest.breakout.game.info.GameState;
import edu.howest.breakout.game.LocalGame;
import edu.howest.breakout.game.input.InputManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observable;
import java.util.Observer;

public class BreakOutFrame extends JFrame implements Observer {

    private Game game;
    private GameGrid gameGrid;

    public BreakOutFrame(){
        try {
            Database database = new Database("root", "", "jdbc:mysql://localhost:3306/breakout");
            setPreferredSize(new Dimension(1200,700));
            setVisible(true);
            InputManager inputManager = new InputManager();
            addKeyListener(inputManager);

            this.game = new LocalGame(GameProperties.BASIC_PROPERTIES, inputManager, database.getLevel(1));
            this.gameGrid = new GameGrid(game);
            game.addObserver(this);
            add(gameGrid);
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    game.setGameState(GameState.closed);
                }
            });
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            pack();
            new Thread(gameGrid).start();
            game.run();
        }catch (Exception e){
            e.printStackTrace();
            close(true);
        }
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

    @Override
    public void update(Observable o, Object arg) {
        if (arg != null)
        {
            if (arg.equals(GameState.Errored))
                close(true);
            else if (arg.equals(GameState.closed))
                close(false);
        }
    }

    public void close(Boolean error){
        setVisible(false);
        dispose();
        if (error)
            System.out.println("Program exited unexpectedly, lets hope this didn't happen during demonstration!!");
    }
}
