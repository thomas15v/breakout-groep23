package edu.howest.breakout.game.input;

import edu.howest.breakout.game.Game;

import java.awt.event.KeyEvent;
import java.util.BitSet;

/**
 * Created by thomas15v on 18/12/14.
 */
public class PauseController implements Controller {

    private Game game;

    public PauseController(Game game){
        this.game = game;
    }

    @Override
    public void keyPressed(BitSet e) {
        System.out.println(e);
        if (e.get(KeyEvent.VK_ESCAPE))
            game.setPaused(!game.isPaused());
    }

    @Override
    public void keyReleased(BitSet e) {}
}
