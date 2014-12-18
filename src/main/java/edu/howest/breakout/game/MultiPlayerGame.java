package edu.howest.breakout.game;

import edu.howest.breakout.game.difficulty.Difficulty;
import edu.howest.breakout.game.entity.Entity;
import edu.howest.breakout.game.entity.EntityBall;
import edu.howest.breakout.game.entity.EntityBlock;
import edu.howest.breakout.game.entity.EntityPad;
import edu.howest.breakout.game.info.Level;
import edu.howest.breakout.game.info.Wall;
import edu.howest.breakout.game.input.InputManager;
import edu.howest.breakout.game.input.PadController;

import java.awt.*;

/**
 * Created by Administrator on 18/12/2014.
 */
public class MultiPlayerGame extends LocalGame {

    private EntityPad entityPadBoven;

    public MultiPlayerGame(InputManager inputManager, Level level, Difficulty difficulty) {
        super(inputManager, level, difficulty);
        //System.out.println("multiplayer started");

        this.entityPadBoven = new EntityPad(Wall.top, Color.BLUE, 150, 15, 0);
        this.getInputManager().addController(new PadController(entityPadBoven, this));
        add(entityPadBoven);
        entityPadBoven.setSpeed(15);
        MoveBlocks();
    }

    public void MoveBlocks(){
        for (Entity e : this.getEntities()) {
            if (e instanceof EntityBlock && !(e instanceof EntityPad)) {
                e.setY(e.getY()+200);

            }
        }

    }
}
