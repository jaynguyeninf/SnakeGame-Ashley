package com.mygdx.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Logger;
import com.mygdx.game.components.DirectionComponent;
import com.mygdx.game.components.MovementComponent;
import com.mygdx.game.configs.GameConfig;
import com.mygdx.game.utils.Mappers;

/**
 * Created by Jay Nguyen on 4/7/2017.
 */

public class DirectionSystem extends IteratingSystem {

    private static final Logger log = new Logger(DirectionSystem.class.getSimpleName(), Logger.DEBUG);

    private static final Family FAMILY = Family.all(
            DirectionComponent.class,
            MovementComponent.class
    ).get();

    public DirectionSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        DirectionComponent directionComponent = Mappers.DIRECTION_COMPONENT.get(entity);
        MovementComponent movementComponent = Mappers.MOVEMENT_COMPONENT.get(entity);

        //reset speed to 0
        movementComponent.xSpeed = 0;
        movementComponent.ySpeed = 0;

        if (directionComponent.isRight()) {
            movementComponent.xSpeed = GameConfig.SNAKE_SPEED;
        } else if (directionComponent.isLeft()) {
            movementComponent.xSpeed = -GameConfig.SNAKE_SPEED;
        } else if (directionComponent.isUp()) {
            movementComponent.ySpeed = GameConfig.SNAKE_SPEED;
        } else if (directionComponent.isDown()) {
            movementComponent.ySpeed = -GameConfig.SNAKE_SPEED;
        }
    }
}
