package edu.howest.breakout.game.input;

import edu.howest.breakout.game.Game;
import edu.howest.breakout.game.entity.EntityPad;
import edu.howest.breakout.game.info.Direction;

import java.awt.event.KeyEvent;
import java.util.BitSet;

public class PadController implements Controller {

    private EntityPad controller;
    private Game game;
    private PadKeyMap padKeyMap;

    public enum PadKeyMap {
        MPplayer1(KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN),
        MPplayer2(KeyEvent.VK_Q, KeyEvent.VK_D, KeyEvent.VK_S),
        Splayer(KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE);

        private int leftKey;
        private int rightKey;
        private int spaceKey;

        private PadKeyMap(int leftKey, int rightKey, int spaceKey) {
            this.leftKey = leftKey;
            this.rightKey = rightKey;
            this.spaceKey = spaceKey;
        }
    }

    public PadController(EntityPad controller, Game game, PadKeyMap padKeyMap){
        this.controller = controller;
        this.game = game;
        this.padKeyMap = padKeyMap;
    }

    public PadController(EntityPad controller, Game game){
        this(controller, game, PadKeyMap.MPplayer1);
    }

    @Override
    public void keyPressed(BitSet e) {
        if (e.get(padKeyMap.leftKey))
            controller.setMovement(Direction.left);
        if (e.get(padKeyMap.rightKey))
            controller.setMovement(Direction.right);
        if (e.get(padKeyMap.spaceKey))
            controller.launchBall(game);
    }

    @Override
    public void keyReleased(BitSet e) {
        controller.setMovement(Direction.none);
    }
}
