package edu.howest.breakout.client.menu;


import edu.howest.breakout.client.GameFrame;
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

public class MainMenuPannel extends JFrame implements Observer {
    private JButton exitButton;
    private JButton highScoresButton;
    private JButton multiPlayerButton;
    private JButton singlePlayerButton;
    private JPanel RootPanel;

    Database database;


    public MainMenuPannel(){
        this.database = new Database("root", "", "jdbc:mysql://localhost:3306/breakout");

        setVisible(true);
        setContentPane(RootPanel);

        setMinimumSize(new Dimension(1000, 700));
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);


        singlePlayerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Start Single Player");
                startSinglePlayer();
            }
        });


        multiPlayerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Start Multi Player");
            }
        });


        highScoresButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Show High Scores");
            }
        });


        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Exit Game");
                System.exit(0);
            }
        });

        pack();
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

    }

    public static void main(String[] args){
        MainMenuPannel frame = new MainMenuPannel();
    }


    public void startSinglePlayer(){
        try {
            final GameFrame gameFrame = new GameFrame(database.getLevel(5));
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.out.println("gave shutdown signal");
                    gameFrame.getGame().setGameState(GameState.closed);
                }
            });
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            RootPanel.setVisible(false);
            setContentPane(gameFrame);
            rootPane.setVisible(true);
        }catch (Exception e){
            e.printStackTrace();
            close(true);
        }
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

