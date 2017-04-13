package com.mygdx.game.systems.passive;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;

/**
 * Created by Jay Nguyen on 4/12/2017.
 */

public class StartUpSystem extends EntitySystem {

    private EntityFactorySystem entityFactorySystem;

    public StartUpSystem(){}

    @Override
    public void addedToEngine(Engine engine) {
        entityFactorySystem = engine.getSystem(EntityFactorySystem.class);
        entityFactorySystem.createBackground();
        entityFactorySystem.createSnake();
        entityFactorySystem.createCoin();
    }

    @Override
    public void update(float deltaTime) {}

    @Override
    public boolean checkProcessing() {
        return false;
    }
}
