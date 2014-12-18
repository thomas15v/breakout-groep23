package edu.howest.breakout.client;

import edu.howest.breakout.game.Game;
import edu.howest.breakout.game.LocalGame;
import edu.howest.breakout.game.MultiPlayerGame;
import edu.howest.breakout.game.Player;
import edu.howest.breakout.game.difficulty.Difficulty;
import edu.howest.breakout.game.info.Level;
import edu.howest.breakout.game.input.InputManager;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JPanel{

    private Game game;
    private JPanel rootPannel;
    private GameGrid gameGrid;
    private ScorePanel scorePanel;


    private Thread gameThread;
    private Thread renderThread;


    public GameFrame(Game game) {
        this.add(rootPannel);
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(game.getInputManager());
        this.setGame(game);
        scorePanel.setGame(game);
        this.renderThread = new Thread(gameGrid);
        this.renderThread.start();
    }

    public Game getGame() {
        return game;
    }

    private void setGame(Game game) {
        this.game = game;
        gameGrid.setGame(game);
        //setMinimumSize(game.getDimension());
        this.gameThread = new Thread(game);
        gameThread.start();
    }

    private void createUIComponents() {
        gameGrid = new GameGrid();
    }
}
