package edu.howest.breakout.game.input;

import edu.howest.breakout.game.entity.EntityPad;
import edu.howest.breakout.game.info.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by thomas15v on 15/12/14.
 */
public class PadController implements Controller {

    private EntityPad controller;


    public PadController(EntityPad controller){
        this.controller = controller;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            controller.setMovement(Direction.left);
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            controller.setMovement(Direction.right);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        controller.setMovement(Direction.none);
    }

}
