package com.mygdx.game.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Logger;
import com.mygdx.game.common.GameManager;
import com.mygdx.game.components.BodyPartComponent;
import com.mygdx.game.components.BoundsComponent;
import com.mygdx.game.components.CoinComponent;
import com.mygdx.game.components.PositionComponent;
import com.mygdx.game.components.SnakeComponent;
import com.mygdx.game.configs.GameConfig;
import com.mygdx.game.systems.passive.EntityFactorySystem;
import com.mygdx.game.systems.passive.SoundSystem;
import com.mygdx.game.utils.Mappers;

/**
 * Created by Jay Nguyen on 4/8/2017.
 */


public class CollisionSystem extends IntervalSystem {

    private static final Logger log = new Logger(CollisionSystem.class.getSimpleName(), Logger.DEBUG);

    private static final Family SNAKE_FAMILY = Family.all(SnakeComponent.class).get();
    private static final Family COIN_FAMILY = Family.all(CoinComponent.class).get();

    private EntityFactorySystem factory;
    private SoundSystem soundSystem;

    public CollisionSystem() {
        super(GameConfig.MOVE_TIME); //use IntervalSystem or it coin will glitch when spawning

    }

    @Override
    public void addedToEngine(Engine engine) {
        factory = getEngine().getSystem(EntityFactorySystem.class);
        soundSystem = getEngine().getSystem(SoundSystem.class);
    }

    @Override
    protected void updateInterval() {

        Engine engine = getEngine();
        ImmutableArray<Entity> snakes = engine.getEntitiesFor(SNAKE_FAMILY);
        ImmutableArray<Entity> coins = engine.getEntitiesFor(COIN_FAMILY);

        //check collision between snake head and body parts
        for (Entity snakeHeadEntity : snakes) {
            SnakeComponent snakeComponent = Mappers.SNAKE_COMPONENT.get(snakeHeadEntity);

            for (Entity snakeBodyPartEntity : snakeComponent.bodyParts) {
                BodyPartComponent bodyPartComponent = Mappers.BODY_PART_COMPONENT.get(snakeBodyPartEntity);

                if (bodyPartComponent.justAdded) {
                    bodyPartComponent.justAdded = false;
                    continue;
                }

                if (overlaps(snakeComponent.head, snakeBodyPartEntity)) {
                    soundSystem.playLoseSound();
                    GameManager.INSTANCE.updateHighScore();
                    GameManager.INSTANCE.setGameOver();
                }


            }
        }

        //check collision between snake and coin
        for (Entity snakeEntity : snakes) {
            for (Entity coinEntity : coins) {

                SnakeComponent snakeComponent = Mappers.SNAKE_COMPONENT.get(snakeEntity);
                CoinComponent coinComponent = Mappers.COIN_COMPONENT.get(coinEntity);

                if (coinComponent.available && overlaps(snakeComponent.head, coinEntity)) {
                    coinComponent.available = false;

                    //get snake head's position and add body parts to array
                    PositionComponent positionComponent = Mappers.POSITION_COMPONENT.get(snakeComponent.head);
                    Entity bodyPart = factory.createBodyPart(positionComponent.x, positionComponent.y);
                    snakeComponent.bodyParts.insert(0, bodyPart);
                    GameManager.INSTANCE.incrementScore(GameConfig.COIN_SCORE);
                    soundSystem.playCoinSound();

                }

            }
        }
    }

    private boolean overlaps(Entity snakeEntity, Entity coinEntity) {
        BoundsComponent snakeBounds = Mappers.BOUNDS_COMPONENT.get(snakeEntity);
        BoundsComponent coinBounds = Mappers.BOUNDS_COMPONENT.get(coinEntity);

        return Intersector.overlaps(snakeBounds.bounds, coinBounds.bounds);
    }
}
