package edu.howest.breakout.game.powerup;

import edu.howest.breakout.game.Game;
import edu.howest.breakout.game.entity.Entity;
import edu.howest.breakout.game.entity.EntityPad;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by thomas15v on 18/12/14.
 */
public class PowerUpManager {

    private List<PowerUp> powerUps;
    private EntityPad entityPad;

    public PowerUpManager(EntityPad entityPad){
        this.powerUps = new ArrayList<PowerUp>();
        this.entityPad = entityPad;
    }

    public void addPowerUp(PowerUp powerUp, Game game){
        powerUp.execute(game, entityPad);
        powerUps.add(powerUp);
    }

    public void tick(Game game, EntityPad entityPad){
        Iterator<PowerUp> powerUpIterator = powerUps.iterator();
        while (powerUpIterator.hasNext()){
            PowerUp powerUp = powerUpIterator.next();
            powerUp.tick();
            if (powerUp.getTicks() < 0) {
                powerUp.undo(game, entityPad);
                powerUpIterator.remove();
            }
        }
    }

}
