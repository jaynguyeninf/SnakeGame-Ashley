package com.mygdx.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.components.CoinComponent;
import com.mygdx.game.components.PositionComponent;
import com.mygdx.game.configs.GameConfig;
import com.mygdx.game.utils.Mappers;

/**
 * Created by Jay Nguyen on 4/8/2017.
 */

public class SpawnCoinSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(
            CoinComponent.class,
            PositionComponent.class
    ).get();

    public SpawnCoinSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent positionComponent = Mappers.POSITION_COMPONENT.get(entity);
        CoinComponent coinComponent = Mappers.COIN_COMPONENT.get(entity);

        if (coinComponent.available) {
            return;
        }

        //when coin is not available, reposition and make coin available

        float coinPositionX = MathUtils.random((int) (GameConfig.WORLD_WIDTH - GameConfig.COIN_SIZE)); //wrap in paratheses to spawn inside a square
        float coinPositionY = MathUtils.random((int) (GameConfig.MAX_Y - GameConfig.COIN_SIZE));

        positionComponent.x = coinPositionX;
        positionComponent.y = coinPositionY;
        coinComponent.available = true;

    }
}
