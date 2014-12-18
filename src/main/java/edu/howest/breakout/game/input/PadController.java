package edu.howest.breakout.game.input;

import edu.howest.breakout.game.Game;
import edu.howest.breakout.game.entity.EntityPad;
import edu.howest.breakout.game.info.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PadController implements Controller {

    private EntityPad controller;
    private Game game;

    public PadController(EntityPad controller, Game game){
        this.controller = controller;
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            controller.setMovement(Direction.left);
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            controller.setMovement(Direction.right);
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
            controller.launchBall(game);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        controller.setMovement(Direction.none);
    }
}
