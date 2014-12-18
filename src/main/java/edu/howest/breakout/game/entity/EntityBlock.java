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

    public void DoAction(Game game, EntityBall entity){
        setDestroyed(true);
        entity.getOwner().getPlayer().addScore(calculateScore(game));
        if (powerUp.isWorth())
            game.add(new EntityPowerup((int) getX() + getWidth() / 2, (int)getY() + getHeight() / 2, powerUp));
        game.checkWin();
    }

    public int calculateScore(Game game){
        return ((getWidth()*getHeight())/5)*game.getDifficulty().getLevel();
    }

    public void setPowerUp(PowerUp powerUp) {
        this.powerUp = powerUp;
        setColor(powerUp.getColor());
    }

    public boolean hasPowerup(){
        return powerUp != null;
    }

    public int getPowerUpType(){
        return powerUp.getId();
    }

    public PowerUp getPowerUp() {
        return powerUp;
    }
}
