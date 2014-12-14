package edu.howest.breakout.game.entity;

import edu.howest.breakout.game.Game;

import java.awt.*;
import java.util.Random;

public class EntityBall extends Entity {
    public EntityBall(int x, int y) {
        super(x, y);
        setColor(Color.RED);
        setSizex(20);
        setSizey(20);
        setSpeed(10);
        setAngle(45);
        //setAngle(new Random().nextInt());
    }

    @Override
    public void tick(Game game) {
        setX(getX() + getXdir());
        setY(getY() + getYdir());
        if (getX() <= 0 || getX() > game.getDimension().getWidth() - (2 * getSizex())) {
            bounceX();
        }
        if (getY() <= 0 || getY() > game.getDimension().getHeight() - (3 * getSizey())) {
            bounceY();
        }
        for (Entity e : game.getEntities()) {
            if (e instanceof EntityBlock || e instanceof EntityPanel) {
                boolean hadbounce = true;
                if (collide(e, getX() + getStraalX(), getY() + getSizey())) {
                    bounceY();
                    setY(e.getY() - getSizey());

                }else if (collide(e, getX() + getStraalX() , getY())) {
                    bounceY();
                    setY(e.getY() + e.getSizey());
                }
                else if (collide(e, getX(), getY() + getStraalY())) {
                    bounceX();
                    setX(e.getX() + e.getSizex());
                }
                else if (collide(e, getX() + getSizex(), getY() + getStraalY())) {
                    bounceX();
                    setX(e.getX() - getSizey());
                }else
                {
                    hadbounce = false;
                }
                if (e instanceof EntityBlock && hadbounce)
                    ((EntityBlock) e).DoAction(game, this);


            }
        }
       // System.out.println();
        //System.out.println(this + ": x:" + getX() + " y: " + getY());
    }

    private void bounceX(){
        setAngle(getAngle() * -1);
    }

    private void bounceY(){
        setAngle(180 - getAngle());
    }

    private boolean collide(Entity e, double x, double y){
        //System.out.println("x:" + x  + "y: " + y);
        return e.getX() < x && e.getSizex() + e.getX() > x && e.getY() < y && e.getSizey() + e.getY() > y;
    }

    public int getStraalX(){
        return getSizex() / 2;
    }

    public int getStraalY(){
        return getSizey() / 2;
    }
}
