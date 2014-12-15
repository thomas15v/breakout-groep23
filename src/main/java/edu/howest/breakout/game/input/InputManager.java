package edu.howest.breakout.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas15v on 13/12/14.
 */
public class InputManager implements KeyListener {

    private List<Controller> controllerList = new ArrayList<Controller>();

    public void addController(Controller controller){
        controllerList.add(controller);
    }

    public void removeController(Controller controller){
        controllerList.remove(controller);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        for (Controller controller : controllerList)
            controller.keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for (Controller controller : controllerList)
            controller.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for (Controller controller : controllerList)
            controller.keyReleased(e);
    }

}
