package edu.howest.breakout.game.powerup;

import edu.howest.breakout.game.Game;
import edu.howest.breakout.game.entity.EntityPad;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by thomas15v on 18/12/14.
 */
public class PowerUpManager {

    private List<PowerUp> powerUps;

    public PowerUpManager(){
        this.powerUps = new ArrayList<PowerUp>();
    }

    public void addPowerUp(PowerUp powerUp){
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
