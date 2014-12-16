package edu.howest.breakout.game.entity;

import edu.howest.breakout.game.Game;

import java.awt.*;

public class EntityBlock extends Entity {

    private int blockCat;

    public EntityBlock(int id, int x, int y, int width, int height, int blockcat){
        this(x,y,width,height,Color.cyan);
        this.blockCat = blockcat;
    }

    public EntityBlock(int x, int y, int width, int height, Color color) {
        super(x, y);
        setWidth(width);
        setHeight(height);
        setColor(Color.CYAN);
    }

    public void DoAction(Game game, Entity entity){
        setDestroyed(true);
    }

    public int getBlockCat() {
        return blockCat;
    }
}
