package edu.howest.breakout.game.input;

import java.awt.event.KeyEvent;

/**
 * Created by thomas15v on 15/12/14.
 */
public interface Controller {

    public void keyTyped(KeyEvent e);

    public void keyPressed(KeyEvent e);

    public void keyReleased(KeyEvent e);

}
