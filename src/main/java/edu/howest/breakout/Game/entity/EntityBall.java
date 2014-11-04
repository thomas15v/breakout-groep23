package edu.howest.breakout.game.entity;

import edu.howest.breakout.game.Game;

/**
 * Created by thomas on 04/11/2014.
 */
public class EntityBall extends Entity {
    
    public EntityBall(int x, int y) {
        super(x, y);
    }

    @Override
    public void tick(Game game) {
        double hoogte = game.getDimension().getHeight();
        double breete = game.getDimension().getWidth();
        setX(getX() + getXdir());
        setY(getY() + getYdir());
    }

}
