package edu.howest.breakout.game.input;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas15v on 13/12/14.
 */
public class InputManager implements KeyEventDispatcher {

    private List<Controller> controllerList = new ArrayList<Controller>();

    public void addController(Controller controller){
        controllerList.add(controller);
    }

    public void removeController(Controller controller){
        controllerList.remove(controller);
    }

    public void keyTyped(KeyEvent e) {
        System.out.println("ya");
        for (Controller controller : controllerList)
            controller.keyTyped(e);
    }

    public void keyPressed(KeyEvent e) {
        for (Controller controller : controllerList)
            controller.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        for (Controller controller : controllerList)
            controller.keyReleased(e);
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
