package com.mygdx.game.systems.passive;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.asset_helpers.AssetDescriptors;
import com.mygdx.game.asset_helpers.RegionNames;
import com.mygdx.game.components.BodyPartComponent;
import com.mygdx.game.components.BoundsComponent;
import com.mygdx.game.components.CoinComponent;
import com.mygdx.game.components.DirectionComponent;
import com.mygdx.game.components.MovementComponent;
import com.mygdx.game.components.PositionComponent;
import com.mygdx.game.components.SizeComponent;
import com.mygdx.game.components.SnakeComponent;
import com.mygdx.game.components.TextureComponent;
import com.mygdx.game.components.UserInputComponent;
import com.mygdx.game.components.WorldWrapComponent;
import com.mygdx.game.components.ZOrderComponent;
import com.mygdx.game.configs.GameConfig;

/**
 * Created by Jay Nguyen on 4/7/2017.
 */

public class EntityFactorySystem extends EntitySystem {

    private static final int BACKGROUND_Z_ORDER = 0;
    private static final int COIN_Z_ORDER = 1;
    private static final int BODY_PART_Z_ORDER = 2;
    private static final int HEAD_Z_ORDER = 3;

    private final AssetManager assetManager;

    private PooledEngine engine;
    private TextureAtlas gameplayAtlas;

    public EntityFactorySystem(AssetManager assetManager) {
        this.assetManager = assetManager;
        init();
    }

    private void init() {
        gameplayAtlas = assetManager.get(AssetDescriptors.GAMEPLAY);
    }

    //"passive" system
    @Override
    public void update(float deltaTime) {
    }

    @Override
    public boolean checkProcessing() {
        return false;
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = (PooledEngine)engine;
    }

    public void createSnake() {
        SnakeComponent snakeComponent = engine.createComponent(SnakeComponent.class);
        snakeComponent.head = createSnakeHead();

        Entity snakeEntity = engine.createEntity();
        snakeEntity.add(snakeComponent);

        engine.addEntity(snakeEntity);

    }

    public Entity createSnakeHead() {
        PositionComponent positionComponent = engine.createComponent(PositionComponent.class);
        positionComponent.x = 0;
        positionComponent.y = 0;

        SizeComponent sizeComponent = engine.createComponent(SizeComponent.class);
        sizeComponent.width = GameConfig.SNAKE_SIZE;
        sizeComponent.height = GameConfig.SNAKE_SIZE;

        BoundsComponent boundsComponent = engine.createComponent(BoundsComponent.class);
        boundsComponent.bounds.setPosition(positionComponent.x, positionComponent.y);
        boundsComponent.bounds.setSize(sizeComponent.width, sizeComponent.height);

        MovementComponent movementComponent = engine.createComponent(MovementComponent.class);

        DirectionComponent directionComponent = engine.createComponent(DirectionComponent.class); //default right

        UserInputComponent userInputComponent = engine.createComponent(UserInputComponent.class);

        WorldWrapComponent worldWrapComponent = engine.createComponent(WorldWrapComponent.class);

        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
        textureComponent.textureRegion = gameplayAtlas.findRegion(RegionNames.HEAD);

        ZOrderComponent zOrderComponent = engine.createComponent(ZOrderComponent.class);
        zOrderComponent.z = HEAD_Z_ORDER;

        Entity entity = engine.createEntity();
        entity.add(positionComponent);
        entity.add(sizeComponent);
        entity.add(boundsComponent);
        entity.add(movementComponent);
        entity.add(directionComponent);
        entity.add(userInputComponent);
        entity.add(worldWrapComponent);
        entity.add(textureComponent);
        entity.add(zOrderComponent);

        engine.addEntity(entity);
        return entity;
    }

    public Entity createBodyPart(float x, float y) {
        PositionComponent positionComponent = engine.createComponent(PositionComponent.class);
        positionComponent.x = x;
        positionComponent.y = y;

        SizeComponent sizeComponent = engine.createComponent(SizeComponent.class);
        sizeComponent.width = GameConfig.SNAKE_SIZE;
        sizeComponent.height = GameConfig.SNAKE_SIZE;

        BoundsComponent boundsComponent = engine.createComponent(BoundsComponent.class);
        boundsComponent.bounds.setPosition(positionComponent.x, positionComponent.y);
        boundsComponent.bounds.setSize(sizeComponent.width, sizeComponent.height);

        BodyPartComponent bodyPartComponent = engine.createComponent(BodyPartComponent.class);

        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
        textureComponent.textureRegion = gameplayAtlas.findRegion(RegionNames.BODY);

        ZOrderComponent zOrderComponent = engine.createComponent(ZOrderComponent.class);
        zOrderComponent.z = BODY_PART_Z_ORDER;

        Entity entity = engine.createEntity();
        entity.add(positionComponent);
        entity.add(sizeComponent);
        entity.add(boundsComponent);
        entity.add(bodyPartComponent);
        entity.add(textureComponent);
        entity.add(zOrderComponent);


        engine.addEntity(entity);
        return entity;
    }

    public void createCoin() {
        PositionComponent positionComponent = engine.createComponent(PositionComponent.class);

        SizeComponent sizeComponent = engine.createComponent(SizeComponent.class);
        sizeComponent.width = GameConfig.COIN_SIZE;
        sizeComponent.height = GameConfig.COIN_SIZE;

        BoundsComponent boundsComponent = engine.createComponent(BoundsComponent.class);
        boundsComponent.bounds.setPosition(positionComponent.x, positionComponent.y);
        boundsComponent.bounds.setSize(sizeComponent.width, sizeComponent.height);

        CoinComponent coinComponent = engine.createComponent(CoinComponent.class);

        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
        textureComponent.textureRegion = gameplayAtlas.findRegion(RegionNames.COIN);

        ZOrderComponent zOrderComponent = engine.createComponent(ZOrderComponent.class);
        zOrderComponent.z = COIN_Z_ORDER;

        Entity entity = engine.createEntity();
        entity.add(positionComponent);
        entity.add(sizeComponent);
        entity.add(boundsComponent);
        entity.add(coinComponent);
        entity.add(textureComponent);
        entity.add(zOrderComponent);


        engine.addEntity(entity);
    }

    public Entity createBackground() {
        PositionComponent positionComponent = engine.createComponent(PositionComponent.class);
        positionComponent.x = 0;
        positionComponent.y = 0;

        SizeComponent sizeComponent = engine.createComponent(SizeComponent.class);
        sizeComponent.width = GameConfig.WORLD_WIDTH;
        sizeComponent.height = GameConfig.WORLD_HEIGHT;


        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
        textureComponent.textureRegion = gameplayAtlas.findRegion(RegionNames.BACKGROUND);

        ZOrderComponent zOrderComponent = engine.createComponent(ZOrderComponent.class);
        zOrderComponent.z = BACKGROUND_Z_ORDER;

        Entity entity = engine.createEntity();
        entity.add(positionComponent);
        entity.add(sizeComponent);
        entity.add(textureComponent);
        entity.add(zOrderComponent);


        engine.addEntity(entity);
        return entity;
    }


}
