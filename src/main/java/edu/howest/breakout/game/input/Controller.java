package edu.howest.breakout.game.input;

import java.awt.event.KeyEvent;
import java.util.BitSet;

/**
 * Created by thomas15v on 15/12/14.
 */
public interface Controller {

    public void keyPressed(BitSet e);

    public void keyReleased(BitSet e);

}
