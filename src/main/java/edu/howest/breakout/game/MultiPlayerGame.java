package edu.howest.breakout.game;

import edu.howest.breakout.game.difficulty.Difficulty;
import edu.howest.breakout.game.entity.Entity;
import edu.howest.breakout.game.entity.EntityBlock;
import edu.howest.breakout.game.entity.EntityPad;
import edu.howest.breakout.game.info.Level;
import edu.howest.breakout.game.info.Wall;
import edu.howest.breakout.game.input.PadController;
import edu.howest.breakout.game.score.Player;

import java.awt.*;
import java.util.EnumSet;
import java.util.List;

/**
 * Created by Administrator (<-- loool) on 18/12/2014.
 */
public class MultiPlayerGame extends LocalGame {

    @Deprecated
    public MultiPlayerGame(Level level, Difficulty difficulty, List<Player> players) {
        super(level, difficulty, players );
        moveBlocks();
    }

    public MultiPlayerGame(List<Level> levels, Difficulty difficulty, List<Player> players) {
        super(levels, difficulty, players );
    }

    @Override
    public void addPads(List<Player> players) {
        EntityPad entityPad1 = new EntityPad(Wall.bottom, Color.RED, 150, 15, this,players.get(0), EnumSet.of(Wall.top, Wall.bottom));
        getInputManager().addController(new PadController(entityPad1, this, PadController.PadKeyMap.MPplayer1));
        add(entityPad1);
        entityPad1.setSpeed(15);
        EntityPad entityPad2 = new EntityPad(Wall.top, Color.BLUE, 150, 15, this,players.get(1), /*true*/ EnumSet.of(Wall.top, Wall.bottom));
        this.getInputManager().addController(new PadController(entityPad2, this, PadController.PadKeyMap.MPplayer2));
        add(entityPad2);
        entityPad2.setSpeed(15);
    }

    public void moveBlocks(){
        for (Entity e : this.getEntities()) {
            if (e instanceof EntityBlock && !(e instanceof EntityPad)) {
                e.setY(e.getY()+200);
            }
        }
    }

    @Override
    public void loadLevel(Level level) {
        super.loadLevel(level);
        moveBlocks();
    }
}
