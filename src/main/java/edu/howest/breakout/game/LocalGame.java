package edu.howest.breakout.game;

import edu.howest.breakout.game.difficulty.Difficulty;
import edu.howest.breakout.game.entity.Entity;
import edu.howest.breakout.game.entity.EntityPad;
import edu.howest.breakout.game.info.GameState;
import edu.howest.breakout.game.info.Level;
import edu.howest.breakout.game.info.Wall;
import edu.howest.breakout.game.input.PadController;
import edu.howest.breakout.game.input.PauseController;
import edu.howest.breakout.game.score.Player;

import java.awt.*;
import java.util.Iterator;

/**
 * Created by thomas on 04/11/2014.
 */
public class LocalGame extends Game {

    public LocalGame(Level level, Difficulty difficulty, Player... player) {
        super(level, difficulty);
        getScoreManager().addPlayer(player);
        getInputManager().addController(new PauseController(this));
        addPads(player);
    }

    protected void addPads(Player... players){
        EntityPad entityPad = new EntityPad(Wall.bottom, Color.RED, 150, 15 , this, players[0], false);
        getInputManager().addController(new PadController(entityPad, this));
        add(entityPad);
        entityPad.setSpeed(15);
    }


    private void tick(){
        Iterator<Entity> it = getEntities().iterator();
        while (it.hasNext()){
            Entity e = it.next();
            e.tick(this);
            if (e.isDestroyed()) {
                remove(e);
            }
        }
        setChanged();
        notifyObservers();
    }

    @Override
    public void run() {
        while (gameState == GameState.Running) {
            try {
                if (isPaused())
                    Thread.sleep(100);
                else {
                    tick();
                    getTickCalculator().tick();
                    Thread.sleep(getTickCalculator().getDelay());
                }
            } catch (Exception e) {
                e.printStackTrace();
                gameState = GameState.Errored;
            }
        }
    }

    @Override
    public void setGameState(GameState gameState) {
        if (gameState != GameState.Running) {
            System.out.println("Game Thread stopped!");
        }
        super.setGameState(gameState);
    }
}
