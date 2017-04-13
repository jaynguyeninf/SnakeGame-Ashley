package com.mygdx.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.common.Direction;
import com.mygdx.game.components.DirectionComponent;
import com.mygdx.game.components.UserInputComponent;
import com.mygdx.game.utils.Mappers;

/**
 * Created by Jay Nguyen on 4/8/2017.
 */

public class UserInputSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(
            UserInputComponent.class,
            DirectionComponent.class
    ).get();

    public UserInputSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        boolean leftPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean rightPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean upPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean downPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);

        DirectionComponent directionComponent = Mappers.DIRECTION_COMPONENT.get(entity);

        if (leftPressed) {
            updateDirection(directionComponent, Direction.LEFT);
        } else if (rightPressed) {
            updateDirection(directionComponent, Direction.RIGHT);
        } else if (upPressed) {
            updateDirection(directionComponent, Direction.UP);
        } else if (downPressed) {
            updateDirection(directionComponent, Direction.DOWN);
        }
    }

    private void updateDirection(DirectionComponent directionComponent, Direction newDirection) {
        if(!directionComponent.isOpposite(newDirection)){
            directionComponent.direction = newDirection;
        }
    }
}
