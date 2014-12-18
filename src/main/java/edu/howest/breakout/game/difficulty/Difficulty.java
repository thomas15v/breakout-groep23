package edu.howest.breakout.game.difficulty;

/**
 * Created by Administrator on 18/12/2014.
 */


public class Difficulty {

    private int level;

    public Difficulty(){
        level = 2;
    }

    public int getBallSpeed(){
        if (level ==1){
            return 10;
        }else if(level ==2){
            return 17;
        }else{
            return 25;
        }
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public int getLevel() {
        return level;
    }
}
