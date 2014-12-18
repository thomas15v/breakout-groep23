package edu.howest.breakout.game.input;

import edu.howest.breakout.game.Game;

import java.awt.event.KeyEvent;

/**
 * Created by thomas15v on 18/12/14.
 */
public class PauseController implements Controller {

    private Game game;

    public PauseController(Game game){
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            game.setPaused(!game.isPaused());
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
