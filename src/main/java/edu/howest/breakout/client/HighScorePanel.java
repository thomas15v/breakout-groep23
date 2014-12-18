package edu.howest.breakout.client;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Administrator on 18/12/2014.
 */
public class HighScorePanel extends  JPanel {
    private JList lstHighScores;
    private JPanel panel1;
    private JLabel highScoresLabel;
    private JButton singlePlayerButton;
    private JButton multiPlayerButton;
    private JButton backButton;

    public HighScorePanel(){
        add(panel1);
    }

    public JButton getBackButton() {
        return backButton;
    }
}
