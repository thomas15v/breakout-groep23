package edu.howest.breakout.game.entity;

import edu.howest.breakout.game.Game;

import java.awt.*;

/**
 * Created by thomas on 04/11/2014.
 */
public abstract class Entity {
    private int speed;
    private double x;
    private double y;
    private int angle;
    private int rotation;
    private int sizex;
    private int sizey;

    protected Entity(int x, int y){
        this.x = x;
        this.y = y;
        this.angle = 80;
        this.speed = 2;
        this.rotation = 0;
    }

    public int getSpeed() {
        return speed;
    }

    protected void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getX() {
        return x;
    }

    protected void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    protected void setY(double y) {
        this.y = y;
    }

    public int getAngle() {
        return angle;
    }

    protected void setAngle(int angle) {
        this.angle = angle;
    }

    public int getRotation() {
        return rotation;
    }

    protected void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public abstract void tick(Game game);

    protected Point getPosition(){
        return new Point((int)getX(), (int)getY());
    }

    protected void setPosition(Point position){
        setY((int)position.getY());
        setX((int)position.getX());
    }

    protected double getXdir(){
        return speed * Math.sin(Math.toRadians(angle));
    }

    protected double getYdir(){
        return speed * Math.cos(Math.toRadians(angle));
    }

    public int getSizex() {
        return sizex;
    }

    public int getSizey() {
        return sizey;
    }

    public void setSizex(int sizex) {
        this.sizex = sizex;
    }

    public void setSizey(int sizey) {
        this.sizey = sizey;
    }
}
