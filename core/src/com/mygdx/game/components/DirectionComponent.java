package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.game.common.Direction;

/**
 * Created by Jay Nguyen on 4/7/2017.
 */

public class DirectionComponent implements Component, Pool.Poolable {

    public Direction direction = Direction.RIGHT; //default direction

    public boolean isLeft(){
        return direction.isLeft();
    }

    public boolean isRight(){
        return direction.isRight();
    }

    public boolean isUp(){
        return direction.isUp();
    }

    public boolean isDown(){
        return direction.isDown();
    }

    public boolean isOpposite(Direction newDirection){
        return direction.isOppositeDirection(newDirection);
    }

    @Override
    public void reset() {
        direction = Direction.RIGHT;
    }
}
