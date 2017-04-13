package com.mygdx.game.systems.passive;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.utils.Logger;
import com.mygdx.game.components.SnakeComponent;
import com.mygdx.game.utils.Mappers;

/**
 * Created by Jay Nguyen on 4/7/2017.
 */

public class SnakeSystem extends EntitySystem implements EntityListener {

    private static final Logger log = new Logger(SnakeSystem.class.getSimpleName(), Logger.DEBUG);

    private static final Family FAMILY = Family.all(
            SnakeComponent.class
    ).get();

    @Override
    public boolean checkProcessing() {
        return false; //return false so engine won't call its update() method
    }

    @Override
    public void update(float deltaTime) {
        //NOT PROCESSING ANYTHING!!!

    }

    // == systems in engine ==
    @Override
    public void addedToEngine(Engine engine) {
        log.debug("SnakeSystem addedToEngine adding EntityListener");
        engine.addEntityListener(FAMILY, this);
    }

    @Override
    public void removedFromEngine(Engine engine) {
        log.debug("SnakeSystem removedFromEngine");
    }


    // == entities in engine
    @Override
    public void entityAdded(Entity entity) {
        log.debug("entityAdded entity = " + entity);
    }

    @Override
    public void entityRemoved(Entity entity) {
        log.debug("entityRemoved entity = " + entity);
        Engine engine = getEngine();
        SnakeComponent snakeComponent = Mappers.SNAKE_COMPONENT.get(entity);

        engine.removeEntity(snakeComponent.head);

        for(Entity bodyPart : snakeComponent.bodyParts){
            engine.removeEntity(bodyPart);
        }

    }
}
