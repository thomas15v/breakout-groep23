package edu.howest.breakout;

import edu.howest.breakout.client.GameGrid;

import javax.swing.*;
import java.awt.*;

public class BreakOutFrame extends JFrame {

    public BreakOutFrame(){
        setPreferredSize(new Dimension(700,700));
        setVisible(true);
        add(new GameGrid());
        pack();
    }

    public static void main(String[] args){
        new BreakOutFrame();
    }

}
