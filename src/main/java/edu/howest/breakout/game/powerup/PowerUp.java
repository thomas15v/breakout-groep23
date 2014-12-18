package edu.howest.breakout.game.powerup;

import edu.howest.breakout.game.Game;
import edu.howest.breakout.game.entity.Entity;
import edu.howest.breakout.game.entity.EntityBall;
import edu.howest.breakout.game.entity.EntityPad;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

/**
 * Created by thomas15v on 18/12/14.
 */
public class PowerUp {

    private int id;
    private int extralive;
    private int extrawidth;
    private int extraballspeed;
    private int extrapadspeed;
    private int ticks;
    private boolean needsundo;
    private String powerUpMessage;
    private Color color;
    /***
     *
     * @param id
     * @param extralive
     * @param extrawidth
     * @param ticks when set lower than zero the powerup doesn't get undone
     */
    public PowerUp(int id, String powerUpMessage,  int extralive, int extrawidth, int extraballspeed, int extrapadspeed, String color, int ticks){
        this.id = id;
        this.powerUpMessage = powerUpMessage;
        this.extralive = extralive;
        this.extrawidth = extrawidth;
        this.extraballspeed = extraballspeed;
        this.extrapadspeed = extrapadspeed;
        this.ticks = ticks;
        this.needsundo = ticks > 0;
        this.color = new Color(Integer.parseInt(color, 16));
    }

    private void execute(Game game, EntityPad entityPad, int modifier)
    {
        game.setLives(game.getLives() + modifier*extralive);
        entityPad.setWidth(entityPad.getWidth() + modifier*extrawidth);
        //todo: fix this when pad is on the left or right side
        entityPad.setX(entityPad.getWidth() + modifier*(extrawidth/2));
        for (Entity ball : game.getEntities())
            if (ball instanceof EntityBall)
                ball.setSpeed(ball.getSpeed() + modifier*extraballspeed);
        entityPad.setSpeed(entityPad.getSpeed() + modifier*extrapadspeed);
    }

    public void execute(Game game, EntityPad entityPad){
        execute(game, entityPad, 1);
    }

    public void undo(Game game, EntityPad entityPad){
        if (needsundo)
            execute(game, entityPad, -1);
    }

    public void tick(){
        ticks--;
    }

    public int getTicks() {
        return ticks;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return String.format("%s %d", powerUpMessage == null ? "None" : powerUpMessage, id);
    }
}
