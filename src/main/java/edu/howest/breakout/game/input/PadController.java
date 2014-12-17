package edu.howest.breakout.game.input;

import edu.howest.breakout.game.entity.EntityPad;
import edu.howest.breakout.game.info.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PadController implements Controller {

    private EntityPad controller;

    public PadController(EntityPad controller){
        this.controller = controller;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("their is input");
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            controller.setMovement(Direction.left);
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            controller.setMovement(Direction.right);
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
            controller.launchBall();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        controller.setMovement(Direction.none);
    }
}
