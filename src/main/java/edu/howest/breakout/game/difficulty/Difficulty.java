package edu.howest.breakout.game.difficulty;

/**
 * Created by Administrator on 18/12/2014.
 */


public class Difficulty {

    private int difficulty;

    public Difficulty(){
        difficulty = 2;
    }

    public int getBallSpeed(){
        if (difficulty==1){
            return 10;
        }else if(difficulty==2){
            return 17;
        }else{
            return 25;
        }
    }

    public void setDifficulty(int i) {
        this.difficulty = i;
    }

}
