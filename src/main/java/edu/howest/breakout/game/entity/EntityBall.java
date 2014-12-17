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
        setWidth(20);
        setHeight(20);
        setSpeed(10);
        setAngle(/*new Random().nextInt()*/ 45);
    }

    @Override
    public void tick(Game game) {
        setX(getX() + getXdir());
        setY(getY() + getYdir());
        if (getX() <= 0)
            bounceX(Wall.left);
        else if(getX() > (game.getDimension().getWidth() - getStraalX()))
            bounceX(Wall.right);
        else if (getY() <= 0)
            bounceY(Wall.top);
        else if (getY() > (game.getDimension().getHeight() - getStraalY()))
            bounceY(Wall.bottom);

        if (lostBall) {
            game.lostlife();
            setDestroyed(true);
        }
        for (Entity e : game.getEntities()) {
            if (e instanceof EntityBlock) {
                boolean hadbounce = true;
                if (collide(e, getX() + getStraalX(), getY() + getHeight())) {
                    bounceY();
                    setY(e.getY() - getHeight());

                }else if (collide(e, getX() + getStraalX() , getY())) {
                    bounceY();
                    setY(e.getY() + e.getHeight());
                }
                else if (collide(e, getX(), getY() + getStraalY())) {
                    bounceX();
                    setX(e.getX() + e.getWidth());
                }
                else if (collide(e, getX() + getWidth(), getY() + getStraalY())) {
                    bounceX();
                    setX(e.getX() - getHeight());
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
        return e.getX() < x && e.getWidth() + e.getX() > x && e.getY() < y && e.getHeight() + e.getY() > y;
    }

    public int getStraalX(){
        return getWidth() / 2;
    }

    public int getStraalY(){
        return getHeight() / 2;
    }
}
