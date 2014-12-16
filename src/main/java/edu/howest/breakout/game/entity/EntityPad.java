package edu.howest.breakout.game.entity;

import edu.howest.breakout.game.Game;
import edu.howest.breakout.game.info.Direction;
import edu.howest.breakout.game.info.Wall;

import java.awt.*;

import static edu.howest.breakout.game.info.Wall.*;

public class EntityPad extends EntityBlock {

    private Wall wall;
    private Direction MovementDirection;

    public EntityPad(Wall wall, Color color, int width, int height, int gamehight) {
        super(0, 0, width, height, color);
        int x = 0;
        int y = 0;
        this.wall = wall;
        this.MovementDirection = Direction.none;
        switch (wall){
            case bottom:
                x = 50;
                y = gamehight - width;
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
    }

    @Override
    public void DoAction(Game game, Entity entity){
        System.out.println("bouncy bouncy");
    }

    public void setMovement(Direction direction){
        this.MovementDirection = direction;
    }

}
