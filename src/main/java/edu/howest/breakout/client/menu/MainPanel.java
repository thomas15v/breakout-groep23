package edu.howest.breakout.client.menu;


import edu.howest.breakout.client.GameGrid;
import edu.howest.breakout.game.Database;
import edu.howest.breakout.game.Game;
import edu.howest.breakout.game.LocalGame;
import edu.howest.breakout.game.info.GameProperties;
import edu.howest.breakout.game.info.GameState;
import edu.howest.breakout.game.input.InputManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

public class MainPanel extends JFrame implements Observer {
    private JButton exitButton;
    private JButton highScoresButton;
    private JButton multiPlayerButton;
    private JButton singlePlayerButton;
    private JPanel RootPanel;
    private Game game;
    private GameGrid gameGrid;


    public MainPanel(){

        setVisible(true);
        setContentPane(RootPanel);
    }

    public static void main(String[] args){
        final MainPanel frame = new MainPanel();
        frame.setSize(1000,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.singlePlayerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Start Single Player");
                frame.startSinglePlayer();
            }
        });


        frame.multiPlayerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Start Multi Player");
            }
        });


        frame.highScoresButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Show High Scores");
            }
        });


        frame.exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Exit Game");
                System.exit(0);
            }
        });

    }


    public void startSinglePlayer(){


        try {
            Database database = new Database("root", "", "jdbc:mysql://localhost:3306/breakout");
            setPreferredSize(new Dimension(1000,700));
            setVisible(true);
            InputManager inputManager = new InputManager();
            addKeyListener(inputManager);

            this.game = new LocalGame(GameProperties.BASIC_PROPERTIES, inputManager, database.getLevel(2));
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

    public Game getGame() {
        return game;
    }

    public GameGrid getGameGrid() {
        return gameGrid;
    }

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

