package edu.howest.breakout.game.entity;

import edu.howest.breakout.game.Game;
import edu.howest.breakout.game.info.Direction;
import edu.howest.breakout.game.info.Wall;
import edu.howest.breakout.game.powerup.PowerUpManager;

import java.awt.*;
import java.util.EnumSet;

import static edu.howest.breakout.game.info.Wall.*;

public class EntityPad extends EntityBlock {

    private Wall wall;
    private EnumSet<Wall> ballWalls;
    private Direction MovementDirection;
    private EntityBall ball;
    private boolean hasBall;
    private boolean ballLess;
    private PowerUpManager powerUpManager;
    private Game game;

    public EntityPad(Wall wall, Color color, int width, int height, Game game, boolean ballLess) {
        super(0, 0, width, height, color);
        this.game = game;
        this.ballLess = ballLess;
        this.powerUpManager = new PowerUpManager(this);
        int x = 0;
        int y = 0;
        this.wall = wall;
        this.ballWalls = EnumSet.of(wall);
        this.MovementDirection = Direction.none;
        switch (wall){
            case bottom:
                x = 50;
                y = (int) game.getDimension().getHeight() - height - 10;
                break;
            case top:
                x = 50;
                y = height + 10;
                break;
        }
        setX(x);
        setY(y);
        if (!ballLess)
            newBall();
    }

    public EntityPad(Wall wall, Color color, int width, int height, Game game, EnumSet<Wall> BallWalls){
        this(wall, color, width, height, game, false);
        this.ballWalls = BallWalls;
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
            switch (wall) {
                case top:
                    ball.setY(getY() + 10 + getHeight());
                    break;
                case bottom:
                    ball.setY(getY() - getHeight() - 10);
            }
        }

        powerUpManager.tick(game, this);
    }

    @Override
    public void DoAction(Game game, Entity entity){
        System.out.println("bouncy bouncy");
    }

    public void setMovement(Direction direction){
        this.MovementDirection = direction;
    }

    public void launchBall(Game game){
        if (hasBall && !ballLess) {
            ball.setSpeed(game.getDifficulty().getBallSpeed());
            ball.setAngle(180);
            hasBall = false;
        }
    }

    public Wall getWall() {
        return wall;
    }

    public void newBall() {
        hasBall = true;
        ball = new EntityBall(this, ballWalls);
        game.add(ball);
    }

    public PowerUpManager getPowerUpManager() {
        return powerUpManager;
    }
}
