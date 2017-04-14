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

//public class SpawnCoinSystem extends EntitySystem implements EntityListener {
//
//    private static final Logger log = new Logger(SpawnCoinSystem.class.getSimpleName(), Logger.DEBUG);
//
//    // == constants ==
//    private static final Family FAMILY = Family.all(
//            CoinComponent.class,
//            PositionComponent.class
//    ).get();
//
//    private static final int MAX_COINS = 5;
//
//    // == attributes ==
//    private final EntityFactorySystem factory;
//    private int totalCoins;
//
//    // == constructors ==
//    public SpawnCoinSystem(EntityFactorySystem factory) {
//        this.factory = factory;
//    }
//
//    @Override
//    public void addedToEngine(Engine engine) {
//        engine.addEntityListener(FAMILY, this);
//    }
//
//    // == public methods ==
//    @Override
//    public void entityAdded(Entity entity) {
//        log.debug("before entityAdded = " + totalCoins);
//        totalCoins++;
//        log.debug("after entityAdded = "+totalCoins);
//    }
//
//    @Override
//    public void entityRemoved(Entity entity) {
//        totalCoins--;
//    }
//
//    @Override
//    public void update(float deltaTime) {
//        // cleanup collected coins
//        // to do: move to another system, it is here just for simplicity
//        ImmutableArray<Entity> entities = getEngine().getEntitiesFor(FAMILY);
//
//        for (Entity entity : entities) {
//            CoinComponent coin = Mappers.COIN_COMPONENT.get(entity);
//
//            if (!coin.available) { //remove coin from screen when sneak eats it
//                getEngine().removeEntity(entity);
//            }
//        }
//
//        // spawn new coins
//        if (totalCoins >= MAX_COINS) {
//            return;
//        }
//
//        float coinX = MathUtils.random((int) (GameConfig.WORLD_WIDTH - 1));
//        float coinY = MathUtils.random((int) (GameConfig.MAX_Y - 1));
//
//        // we need more coins
//        // this boolean is just so it is easy to see parameter name
//        // to do: init to true in #createCoin() method
//        boolean available = true;
//        factory.createCoin(coinX, coinY, available);
//    }
//}

