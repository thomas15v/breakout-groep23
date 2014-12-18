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
    }

    @Override
    public void tick(Game game) {
        setX(getX() + getXdir());
        setY(getY() + getYdir());
        for (Entity entity : game.getEntities())
            if (entity instanceof EntityPad)
                if (entity.collide(entity, + getHeight()/2 , getY())) {
                    setDestroyed(true);
                    ((EntityPad) entity).getPowerUpManager().addPowerUp(powerUp);
                }
        if (getX() > game.getDimension().getHeight())
            setDestroyed(true);
    }
}
