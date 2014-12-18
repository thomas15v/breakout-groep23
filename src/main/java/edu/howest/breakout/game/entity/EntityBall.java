package edu.howest.breakout.game.entity;

import edu.howest.breakout.game.Game;
import edu.howest.breakout.game.info.Wall;

import java.awt.*;;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Random;

public class EntityBall extends Entity {

    private boolean lostBall = false;
    private EnumSet<Wall> walls;
    private int bereik = 90;
    EntityPad owner;

    public EntityBall(int x, int y){
        this(x, y, EnumSet.of(Wall.bottom));
    }

    public EntityBall(int x, int y, Wall wall){
        this(x, y, EnumSet.of(wall));
    }

    public EntityBall(int x, int y, EnumSet<Wall> walls) {
        super(x, y);
        this.walls = walls;
        setColor(Color.RED);
        setWidth(20);
        setHeight(20);
        setSpeed(15);
        setAngle(/*new Random().nextInt()*/ 160);
    }

    public EntityBall(EntityPad owner){
        this(-80,-80 , owner.getWall());
        this.owner = owner;
        setSpeed(0);
    }

    @Override
    public void tick(Game game) {
        setX(getX() + getXdir());
        setY(getY() + getYdir());
        if (getX() <= 0)
            bounceX(Wall.left);
        else if(getX() > (game.getDimension().getWidth() - 2*getStraalX()))
            bounceX(Wall.right);
        else if (getY() <= 0)
            bounceY(Wall.top);
        else if (getY() > (game.getDimension().getHeight() - getStraalY()))
            bounceY(Wall.bottom);

        if (getY()<0){setY(0);}
        if (getX()<0){setX(0);}
        if (getX()>game.getDimension().width-getWidth()){setX(game.getDimension().width - getWidth());}

        if (lostBall) {
            game.lostlife(owner);
            setDestroyed(true);
        }
        for (Entity e : game.getEntities()) {
            if (e instanceof EntityBlock) {
                boolean hadbounce = true;
                if (collide(e, getX() + getStraalX(), getY() + getHeight())) { //collision met bovenkant
                    if (e instanceof EntityPad){
                        changeAngle(e);
                    }
                    bounceY();
                    setY(e.getY() - getHeight());

                }else if (collide(e, getX() + getStraalX() , getY())) { //collision met onderkant
                    if (e instanceof EntityPad){
                        changeAngle(e);
                    }
                    bounceY();
                    setY(e.getY() + e.getHeight());
                }
                else if (collide(e, getX(), getY() + getStraalY())) { //collision met rechterkant
                    bounceX();
                    setX(e.getX() + e.getWidth());
                }
                else if (collide(e, getX() + getWidth(), getY() + getStraalY())) { //collision met linkerkant
                    bounceX();
                    setX(e.getX() - getHeight());
                }else
                {
                    hadbounce = false;
                }
                if (hadbounce)
                    ((EntityBlock) e).DoAction(game, this);


            }
        }
    }

    private void changeAngle(Entity e){ //op basis van positie op pad
        double locatiePad = ((getX() - e.getX() + getWidth()/2)/e.getWidth())*bereik;
        //int oldAngle = getAngle();


        int newAngle = ((int)locatiePad)-bereik/2;
        setAngle(newAngle);

    }


    private void bounceX(){
        setAngle(getAngle() * -1);
    }
    //needs refactor
    private void bounceX(Wall wall){
        if (!            walls.contains(wall))
            bounceX();
        else
            lostBall = true;
    }

    private void bounceY(){
        setAngle(180 - getAngle());
    }
    //needs refactor
    private void bounceY(Wall wall){
        if (!walls.contains(wall))
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
