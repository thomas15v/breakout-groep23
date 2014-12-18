package edu.howest.breakout.game;

import edu.howest.breakout.game.difficulty.Difficulty;
import edu.howest.breakout.game.entity.Entity;
import edu.howest.breakout.game.entity.EntityBall;
import edu.howest.breakout.game.entity.EntityPad;
import edu.howest.breakout.game.info.GameState;
import edu.howest.breakout.game.info.Level;
import edu.howest.breakout.game.info.Wall;
import edu.howest.breakout.game.input.InputManager;
import edu.howest.breakout.game.input.PadController;

import java.awt.*;
import java.util.Iterator;

/**
 * Created by thomas on 04/11/2014.
 */
public class LocalGame extends Game {

    private EntityPad entityPad;
    private InputManager inputManager;

    public LocalGame(InputManager inputManager, Level level, Difficulty difficulty) {
        super(level, difficulty);
        this.inputManager = inputManager;

        /*for (int x = 0; x < 8; x++)
            for (int y = 0; y < 4; y++)
                add(new EntityBlock(x * 52 + 10, y * 52 + 10, Color.black, 50, 50));*/
        this.entityPad = new EntityPad(Wall.bottom, Color.RED, 150, 15, (int) getDimension().getHeight());
        EntityBall ball = new EntityBall(entityPad);
        add(ball);
        this.inputManager.addController(new PadController(entityPad, this));
        entityPad.setBall(ball);
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
                tick();
                getTickCalculator().tick();
                Thread.sleep(getTickCalculator().getDelay());
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
