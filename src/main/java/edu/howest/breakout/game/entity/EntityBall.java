package edu.howest.breakout.game.entity;

import edu.howest.breakout.game.Game;
import edu.howest.breakout.game.info.Wall;

import java.awt.*;;
import java.util.Random;

public class EntityBall extends Entity {

    private boolean lostBall = false;
    private Wall wall;

    public EntityBall(int x, int y){
        this(x, y, Wall.bottom);
    }

    public EntityBall(int x, int y, Wall wall) {
        super(x, y);
        this.wall = wall;
        setColor(Color.RED);
        setSizex(20);
        setSizey(20);
        setSpeed(10);
        setAngle(new Random().nextInt());
    }

    @Override
    public void tick(Game game) {
        setX(getX() + getXdir());
        setY(getY() + getYdir());
        if (getX() <= 0)
            bounceX(Wall.left);
        else if(getX() > game.getDimension().getWidth() - (2 * getSizex()))
            bounceX(Wall.right);
        else if (getY() <= 0)
            bounceY(Wall.top);
        else if (getY() > game.getDimension().getHeight() - (3 * getSizey()))
            bounceY(Wall.bottom);

        if (lostBall) {
            game.lostlife();
            setDestroyed(true);
        }
        for (Entity e : game.getEntities()) {
            if (e instanceof EntityBlock) {
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
    }

    private void bounceX(){
        setAngle(getAngle() * -1);
    }
    //needs refactor
    private void bounceX(Wall wall){
        if (wall != this.wall)
            bounceX();
        else
            lostBall = true;
    }

    private void bounceY(){
        setAngle(180 - getAngle());
    }
    //needs refactor
    private void bounceY(Wall wall){
        if (wall != this.wall)
            bounceY();
        else
            lostBall = true;
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
