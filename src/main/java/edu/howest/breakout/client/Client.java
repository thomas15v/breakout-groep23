package edu.howest.breakout.client;


import edu.howest.breakout.game.Database;
import edu.howest.breakout.game.Game;
import edu.howest.breakout.game.LocalGame;
import edu.howest.breakout.game.MultiPlayerGame;
import edu.howest.breakout.game.score.Player;
import edu.howest.breakout.game.difficulty.Difficulty;
import edu.howest.breakout.game.info.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

public class Client extends JFrame implements Observer {
    private JButton exitButton;
    private JButton highScoresButton;
    private JButton multiPlayerButton;
    private JButton singlePlayerButton;
    private JPanel MainMenuPannel;
    private JComboBox comboBoxDifficulty;
    private JTextField player1Value;
    private JTextField player2Value;
    private Difficulty difficulty;
    private Database database;
    private HighScorePanel highScorePanel;
    private CardLayout cardLayout;
    private JPanel RootPannel;
    private GameFrame gameFrame;

    public Client(){
        this.database = new Database("root", "", "jdbc:mysql://localhost:3306/breakout");
        RootPannel = new JPanel();
        setContentPane(RootPannel);
        this.difficulty = new Difficulty();
        setVisible(true);
        cardLayout = new CardLayout();
        RootPannel.setLayout(cardLayout);
        this.highScorePanel = new HighScorePanel();
        RootPannel.add(MainMenuPannel, "Main");
        RootPannel.add(highScorePanel, "HighScores");
        setMinimumSize(new Dimension(1000, 700));
        //setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

        String[] difficultyList = { "Easy","Normal","Hard" };

        for (int i = 0; i < difficultyList.length; i++)
            	    comboBoxDifficulty.addItem(difficultyList[i]);
        comboBoxDifficulty.setSelectedIndex(2);

        comboBoxDifficulty.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("changed difficulty to " + comboBoxDifficulty.getSelectedIndex());
                System.out.println(difficulty);
                difficulty.setLevel(comboBoxDifficulty.getSelectedIndex() + 1);
            }
        });


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
                startMultiPlayer();
            }


        });

        highScoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Show High Scores");
                show(highScorePanel);
            }
        });


        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Exit Game");
                System.exit(0);
            }
        });

        highScorePanel.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                show(MainMenuPannel);
                repaint();
            }
        });

        pack();
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

    }

    private void startMultiPlayer() {
        try {
            Player player1 = new Player(player1Value.getText());
            Player player2 = new Player(player2Value.getText());
            startGame(new MultiPlayerGame(database.getLevel(1), difficulty, player1, player2));
        }catch (Exception e){
            e.printStackTrace();
            close(true);
        }
    }

    public void startGame(final Game game){
        game.addObserver(this);
        this.gameFrame = new GameFrame(game);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("gave shutdown signal");
                game.setGameState(GameState.EndGame);
            }
        });
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        MainMenuPannel.setVisible(false);
        add(gameFrame);
        gameFrame.setVisible(true);
        repaint();
    }

    public static void main(String[] args){
        Client frame = new Client();
    }


    public void startSinglePlayer(){
        try {
            Player player = new Player(player1Value.getText());
            startGame(new LocalGame(database.getLevel(1), difficulty, player));
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
            else if (arg.equals(GameState.EndGame)) {
                for (Player player : ((Game) o).getScoreManager().getPlayers())
                    database.addPlayer(player);
                show(MainMenuPannel);
                remove(gameFrame);
                repaint();
            }
        }
    }

    private void show(JPanel panel){
       for (Component component : RootPannel.getComponents())
            component.setVisible(false);
        panel.setVisible(true);
        repaint();
    }

    public void close(Boolean error){
        setVisible(false);
        dispose();
        if (error)
            System.out.println("Program exited unexpectedly, lets hope this didn't happen during demonstration!!");
    }
}

