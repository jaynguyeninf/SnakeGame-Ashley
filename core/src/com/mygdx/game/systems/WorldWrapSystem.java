package com.mygdx.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.game.components.PositionComponent;
import com.mygdx.game.components.WorldWrapComponent;
import com.mygdx.game.configs.GameConfig;
import com.mygdx.game.utils.Mappers;

/**
 * Created by Jay Nguyen on 4/8/2017.
 */

public class WorldWrapSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(
            WorldWrapComponent.class,
            PositionComponent.class
    ).get();

    public WorldWrapSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent positionComponent = Mappers.POSITION_COMPONENT.get(entity);

        if (positionComponent.x >= GameConfig.WORLD_WIDTH) {
            positionComponent.x = 0;
        } else if (positionComponent.x < 0) {
            positionComponent.x = GameConfig.WORLD_WIDTH - GameConfig.SNAKE_SIZE;
        }

        if (positionComponent.y >= GameConfig.WORLD_HEIGHT) {
            positionComponent.y = 0;
        } else if (positionComponent.y < 0) {
            positionComponent.y = GameConfig.WORLD_HEIGHT - GameConfig.SNAKE_SIZE;
        }

    }
}
