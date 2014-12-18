package edu.howest.breakout.game.entity;

import edu.howest.breakout.game.Game;
import edu.howest.breakout.game.powerup.PowerUp;

/**
 * Created by thomas15v on 17/12/14.
 */
public class EntityPowerup extends Entity {

    PowerUp powerUp;

    public EntityPowerup(int x, int y, PowerUp powerUp) {
        super(x, y);
        setWidth(5);
        setHeight(5);
        setSpeed(4);
        setAngle(0);
        this.powerUp = powerUp;
        setColor(powerUp.getColor());
    }

    @Override
    public void tick(Game game) {
        setX(getX() + getXdir());
        setY(getY() + getYdir());
        for (Entity e : game.getEntities())
            if (e instanceof EntityPad)
                if (collide(e, getX() , getY())) {
                    setDestroyed(true);
                    ((EntityPad) e).getPowerUpManager().addPowerUp(powerUp, game);
                }
        if (getY() > game.getDimension().getHeight())
            setDestroyed(true);
    }
}
//        return e.getX() < x && e.getWidth() + e.getX() > x && e.getY() < y && e.getHeight() + e.getY() > y;
