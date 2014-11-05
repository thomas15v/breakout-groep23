package edu.howest.breakout.game.entity;

import edu.howest.breakout.game.Game;

import java.awt.*;

public class EntityBall extends Entity {
    public EntityBall(int x, int y) {
        super(x, y);
        setColor(Color.RED);
        setSizex(20);
        setSizey(20);
        setSpeed(10);
    }

    @Override
    public void tick(Game game) {
        if(getX()<=0 || getX()>game.getDimension().getWidth()-(2*getSizex())){
            setAngle(getAngle()*-1);
        }
        if(getY()<=0 || getY()>game.getDimension().getHeight()-(3*getSizey())){
            setAngle(180-getAngle());
        }
        for (Entity e : game.getEntities()){
            if (!e.equals(this) && e instanceof EntityBlock){

            }
        }
        setX(getX() + getXdir());
        setY(getY() + getYdir());
    }



}
