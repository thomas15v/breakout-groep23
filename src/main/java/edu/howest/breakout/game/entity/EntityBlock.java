package edu.howest.breakout.game.entity;

import edu.howest.breakout.game.Game;
import edu.howest.breakout.game.powerup.PowerUp;

import java.awt.*;

public class EntityBlock extends Entity {

    private PowerUp powerUp;

    public EntityBlock(int x, int y, int width, int height, PowerUp powerUp){
        this(x,y,width,height, powerUp.getColor());
        setPowerUp(powerUp);
    }

    public EntityBlock(int x, int y, int width, int height, Color color) {
        super(x, y);
        setWidth(width);
        setHeight(height);
        setColor(color);
    }

    public void DoAction(Game game, Entity entity){
        setDestroyed(true);
        game.addScore(calculateScore());
        if (powerUp.isWorth())
            game.add(new EntityPowerup((int) getX(), (int)getY(), powerUp));
    }

    public int calculateScore(){
        return (getWidth()*getHeight())/5;
    }

    public void setPowerUp(PowerUp powerUp) {
        this.powerUp = powerUp;
    }

    public boolean hasPowerup(){
        return powerUp != null;
    }

    public int getPowerUpType(){
        return powerUp.getId();
    }
}
