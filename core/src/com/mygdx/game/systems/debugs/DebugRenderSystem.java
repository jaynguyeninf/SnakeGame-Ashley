package com.mygdx.game.systems.debugs;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.components.BoundsComponent;
import com.mygdx.game.utils.Mappers;

/**
 * Created by Jay Nguyen on 4/7/2017.
 */

public class DebugRenderSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(
            BoundsComponent.class
    ).get();

    private final Viewport viewport;
    private final ShapeRenderer shapeRenderer;

    public DebugRenderSystem(Viewport viewport, ShapeRenderer shapeRenderer) {
        super(FAMILY);
        this.viewport = viewport;
        this.shapeRenderer = shapeRenderer;
    }

    @Override
    public void update(float deltaTime) {

        Color oldColor = shapeRenderer.getColor();
        viewport.apply();
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        //call processEntity internally in for loop
        super.update(deltaTime);

        shapeRenderer.end();
        shapeRenderer.setColor(oldColor);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BoundsComponent boundsComponent = Mappers.BOUNDS_COMPONENT.get(entity);

        shapeRenderer.rect(boundsComponent.bounds.x, boundsComponent.bounds.y,
                boundsComponent.bounds.width, boundsComponent.bounds.height);
    }
}
