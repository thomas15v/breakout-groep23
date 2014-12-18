package edu.howest.breakout.game.entity;

import edu.howest.breakout.game.Game;
import edu.howest.breakout.game.info.Direction;
import edu.howest.breakout.game.info.Wall;

import java.awt.*;

import static edu.howest.breakout.game.info.Wall.*;

public class EntityPad extends EntityBlock {

    private Wall wall;
    private Direction MovementDirection;
    private EntityBall ball;
    private boolean hasBall;

    public EntityPad(Wall wall, Color color, int width, int height, int gamehight) {
        super(0, 0, width, height, color);
        int x = 0;
        int y = 0;
        this.hasBall = false;
        this.wall = wall;
        this.MovementDirection = Direction.none;
        switch (wall){
            case bottom:
                x = 50;

                y = gamehight - height - 10;


                break;
        }
        setX(x);
        setY(y);

    }

    @Override
    public void tick(Game game) {
        int motion = getSpeed();
        if (MovementDirection != Direction.none) {
            if (MovementDirection == Direction.left)
                motion *= -1;

            if (this.wall == bottom || this.wall == top)
                setX(getX() + motion);
        }
        if(getX()<0){
            setX(0);
        }
        if(getX()+getWidth()>game.getDimension().width-1){
            setX(game.getDimension().width-getWidth()-1);
        }
        if (hasBall) {
            ball.setX(getX() + getWidth()/2 - ball.getWidth()/2);
            ball.setY(getY() - getHeight() - 10);
        }
    }

    @Override
    public void DoAction(Game game, Entity entity){
        System.out.println("bouncy bouncy");
    }

    public void setMovement(Direction direction){
        this.MovementDirection = direction;
    }

    public void launchBall(Game game){
        if (hasBall) {
            ball.setSpeed(game.getDifficulty().getBallSpeed());
            ball.setAngle(180);
            hasBall = false;
        }
    }

    public Wall getWall() {
        return wall;
    }

    public void setBall(EntityBall ball) {
        hasBall = true;
        this.ball = ball;
    }
}
