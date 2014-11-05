package edu.howest.breakout.client;

/**
 * Created by thomas on 05/11/2014.
 */
public class FpsCalculator {

    private long lastTime;
    private int ticks;
    private int lastfps;

    public FpsCalculator(){
        lastTime = System.currentTimeMillis();
    }

    private long getNextSecondTime(){
        return lastTime + 1000;
    }

    public void tick(){
        if (System.currentTimeMillis() > getNextSecondTime()) {
            lastTime = System.currentTimeMillis();
            lastfps = ticks;
            ticks = 0;
        }
        ticks++;
    }

    public double getFps(){
        return lastfps;
    }

}
