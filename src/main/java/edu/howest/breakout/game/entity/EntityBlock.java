package edu.howest.breakout.game.entity;

import edu.howest.breakout.game.Game;

import java.awt.*;

/**
 * Created by thomas on 04/11/2014.
 */
public class EntityBlock extends Entity {

    public EntityBlock(int x, int y, Color color, int width, int height) {
        super(x, y);
        setColor(Color.CYAN);
    }

    @Override
    public void tick(Game game) {

    }
}
