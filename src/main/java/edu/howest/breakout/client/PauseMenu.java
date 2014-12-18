package edu.howest.breakout.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by thomas15v on 18/12/14.
 */
public class PauseMenu extends JPanel {
    private JButton backToMenuButton;
    private JButton continueButton;
    private JPanel rootPanel;
    private JLabel PauseLabel;

    public static final String PAUSE_MESSAGE = "Pause!";

    public PauseMenu(){
        add(rootPanel);
        backToMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTitle(PAUSE_MESSAGE);
                continueButton.setVisible(true);
            }
        });
    }

    public void setTitle(String text){
        PauseLabel.setText(text);
        continueButton.setVisible(false);
    }

    public JButton getBackToMenuButton() {
        return backToMenuButton;
    }

    public JButton getContinueButton() {
        return continueButton;
    }
}
