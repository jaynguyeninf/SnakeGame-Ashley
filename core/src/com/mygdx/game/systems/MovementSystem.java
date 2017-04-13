package com.mygdx.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.gdx.utils.Logger;
import com.mygdx.game.components.MovementComponent;
import com.mygdx.game.components.PositionComponent;
import com.mygdx.game.components.SnakeComponent;
import com.mygdx.game.configs.GameConfig;
import com.mygdx.game.utils.Mappers;

/**
 * Created by Jay Nguyen on 4/7/2017.
 */

public class MovementSystem extends IntervalIteratingSystem {

    private static final Logger log = new Logger(MovementSystem.class.getSimpleName(), Logger.DEBUG);

    private static final Family FAMILY = Family.all(
            SnakeComponent.class
    ).get();

    private float xHeadPositionBeforeUpdate;
    private float yHeadPositionBeforeUpdate;

    public MovementSystem() {
        super(FAMILY, GameConfig.MOVE_TIME);
    }

    @Override
    protected void processEntity(Entity entity) {
        SnakeComponent snakeComponent = Mappers.SNAKE_COMPONENT.get(entity);


        moveHead(snakeComponent.head);
        moveBodyParts(snakeComponent);
    }

    private void moveHead(Entity headEntity) {
        MovementComponent movementComponent = Mappers.MOVEMENT_COMPONENT.get(headEntity);
        PositionComponent positionComponent = Mappers.POSITION_COMPONENT.get(headEntity);

        xHeadPositionBeforeUpdate = positionComponent.x;
        yHeadPositionBeforeUpdate = positionComponent.y;

        positionComponent.x += movementComponent.xSpeed;
        positionComponent.y += movementComponent.ySpeed;
    }

    private void moveBodyParts(SnakeComponent snakeComponent){
        if(snakeComponent.hasBodyParts()){
            Entity tail = snakeComponent.bodyParts.removeIndex(0);
            PositionComponent positionComponent = Mappers.POSITION_COMPONENT.get(tail); //get tail position
            positionComponent.x = xHeadPositionBeforeUpdate; //set tail position
            positionComponent.y = yHeadPositionBeforeUpdate;
            snakeComponent.bodyParts.add(tail);

        }
    }
}
