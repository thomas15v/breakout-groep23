package edu.howest.breakout;

import edu.howest.breakout.client.GameGrid;
import edu.howest.breakout.game.Database;
import edu.howest.breakout.game.Game;
import edu.howest.breakout.game.info.GameState;
import edu.howest.breakout.game.LocalGame;
import edu.howest.breakout.game.input.InputManager;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

public class GameFrame extends JFrame implements Observer {

    private Game game;
    private JPanel rootPannel;
    private GameGrid gameGrid;

    public GameFrame(){
        try {
            Database database = new Database("root", "", "jdbc:mysql://localhost:3306/breakout");
            InputManager inputManager = new InputManager();
            addKeyListener(inputManager);

            this.game = new LocalGame(inputManager, database.getLevel(2));
            gameGrid.setGame(game);
            game.addObserver(this);

            setMinimumSize(game.getDimension());
            setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
            setVisible(true);
            setContentPane(rootPannel);
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
        GameFrame frame = new GameFrame();
    }

    public Game getGame() {
        return game;
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

    private void createUIComponents() {
        gameGrid = new GameGrid();
    }
}
