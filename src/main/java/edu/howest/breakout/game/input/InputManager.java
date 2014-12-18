package edu.howest.breakout.game.input;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * Created by thomas15v on 13/12/14.
 */
public class InputManager implements KeyEventDispatcher {

    private BitSet keybits = new BitSet(265);
    private List<Controller> controllerList = new ArrayList<Controller>();

    public void addController(Controller controller){
        controllerList.add(controller);
    }

    public void removeController(Controller controller){
        controllerList.remove(controller);
    }

    public void keyTyped(KeyEvent e) {
        //Nope nope and nope, I hate you
    }

    public void keyPressed(KeyEvent e) {
        keybits.set(e.getKeyCode());
        for (Controller controller : controllerList)
            controller.keyPressed(keybits);
    }

    public void keyReleased(KeyEvent e) {
        for (Controller controller : controllerList)
            controller.keyReleased(keybits);
        keybits.clear(e.getKeyCode());
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        switch (e.getID()) {
            case KeyEvent.KEY_PRESSED:
                keyPressed(e);
                break;
            case KeyEvent.KEY_TYPED:
                keyTyped(e);
                break;
            case KeyEvent.KEY_RELEASED:
                keyReleased(e);
                break;
        }
        return false;
    }
}
