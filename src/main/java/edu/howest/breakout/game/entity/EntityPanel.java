package edu.howest.breakout.game.entity;

import edu.howest.breakout.game.Game;

import java.awt.*;

public class EntityPanel extends Entity {

    public EntityPanel(int x, int y, Color color, int width, int height) {
        super(x, y);
        setSizex(width);
        setSizey(height);
        setColor(Color.BLUE);

    }

    @Override
    public void tick(Game game) {
    }

    public void DoAction(Game game, Entity entity){
        setDestroyed(true);
    }


}
