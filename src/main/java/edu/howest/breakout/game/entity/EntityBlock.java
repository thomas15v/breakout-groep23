package edu.howest.breakout.game.entity;

import edu.howest.breakout.game.Game;

import java.awt.*;

public class EntityBlock extends Entity {

    public EntityBlock(int x, int y, Color color, int width, int height) {
        super(x, y);
        setSizex(width);
        setSizey(height);
        setColor(Color.CYAN);
    }

    public void DoAction(Game game, Entity entity){
        setDestroyed(true);
    }
}
