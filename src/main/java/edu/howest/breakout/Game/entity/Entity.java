package edu.howest.breakout.Game.entity;

/**
 * Created by thomas on 04/11/2014.
 */
public abstract class Entity {
    private int speed;
    private int x;
    private int y;
    private int angle;
    private int rotation;

    protected Entity(int x, int y){
        this.x = x;
        this.y = y;
        this.angle = 0;
        this.speed = 0;
        this.rotation = 0;
    }

    public int getSpeed() {
        return speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAngle() {
        return angle;
    }

    public int getRotation() {
        return rotation;
    }
}
