package com.mygdx.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.game.components.BoundsComponent;
import com.mygdx.game.components.PositionComponent;
import com.mygdx.game.components.SizeComponent;
import com.mygdx.game.utils.Mappers;

/**
 * Created by Jay Nguyen on 4/8/2017.
 */

public class BoundsSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(
            BoundsComponent.class,
            PositionComponent.class,
            SizeComponent.class
    ).get();

    public BoundsSystem(){
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BoundsComponent boundsComponent = Mappers.BOUNDS_COMPONENT.get(entity);
        PositionComponent positionComponent = Mappers.POSITION_COMPONENT.get(entity);
        SizeComponent sizeComponent = Mappers.SIZE_COMPONENT.get(entity);

        boundsComponent.bounds.setPosition(positionComponent.x, positionComponent.y);
        boundsComponent.bounds.setSize(sizeComponent.width, sizeComponent.height);
    }
}
