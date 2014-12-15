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
    private Color color;
    private double vX;
    private double vY;
    private boolean destroyed;

    protected Entity(int x, int y){
        this.x = x;
        this.y = y;
        setSpeed(1);
        destroyed = false;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
        this.vX = speed * Math.sin(Math.toRadians(angle)) * 0.5;
        this.vY = speed * Math.cos(Math.toRadians(angle)) * 0.5;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getAngle() {
        return angle;
    }

    protected void setAngle(int angle) {
        this.angle = angle;
        setSpeed(speed);
    }

    public int getRotation() {
        return rotation;
    }

    protected void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public void tick(Game game){}

    protected Point getPosition(){
        return new Point((int)getX(), (int)getY());
    }

    protected void setPosition(Point position){
        setY((int)position.getY());
        setX((int)position.getX());
    }

    protected double getXdir(){
        return vX;
    }

    protected double getYdir(){
        return vY;
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    @Override
    public String toString() {
        return "Entity@" + Integer.toHexString(hashCode());
    }
}
