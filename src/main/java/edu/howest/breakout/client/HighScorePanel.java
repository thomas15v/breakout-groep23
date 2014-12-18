package edu.howest.breakout.client;


import edu.howest.breakout.game.Database;
import edu.howest.breakout.game.score.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Administrator on 18/12/2014.
 */
public class HighScorePanel extends JPanel {
    private JList lstHighScores;
    private JPanel panel1;
    private JLabel highScoresLabel;
    private JButton singlePlayerButton;
    private JButton multiPlayerButton;
    private JButton backButton;
    private Database database;

    private DefaultListModel<Player> players;

    public HighScorePanel(final Database database){
        this.database = database;
        add(panel1);
        players = new DefaultListModel<Player>();
        singlePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lstHighScores.setListData(database.getHighScores().toArray());
            }
        });
        multiPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lstHighScores.setListData(database.getMPHightScores().toArray());
            }
        });

        lstHighScores.setListData(database.getHighScores().toArray());
    }

    public JButton getBackButton() {
        return backButton;
    }
}
