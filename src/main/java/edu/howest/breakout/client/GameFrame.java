package edu.howest.breakout.client;

import edu.howest.breakout.game.Game;
import edu.howest.breakout.game.LocalGame;
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


    private InputManager inputManager;
    private Thread gameThread;
    private Thread renderThread;

    public GameFrame(Level level, Difficulty difficulty) {
        this.add(rootPannel);
        this.inputManager = new InputManager();
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(inputManager);
        this.setGame(new LocalGame(inputManager, level, difficulty));
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
