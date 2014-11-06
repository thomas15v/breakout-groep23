package edu.howest.breakout.client;

/**
 * Created by thomas on 05/11/2014.
 */
public class FpsCalculator {

    private final int TARGET_FPS = 59;
    private final int TARGET_DELAY = 1000/TARGET_FPS;
    private int delay = TARGET_DELAY;

    private long lastTime;
    private int ticks;
    private int lastfps = TARGET_FPS;

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
            updateDelay();
        }
        ticks++;
    }

    public double getFps(){
        return lastfps;
    }

    private void updateDelay(){
        if (lastfps > TARGET_FPS)
            delay++;
        else if (lastfps < TARGET_FPS && delay > 0)
            delay--;
        System.out.println(delay);
    }

    public int getDelay(){
        return delay;
    }
}