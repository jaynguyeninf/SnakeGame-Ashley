package com.mygdx.game.systems.debugs;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.utils.ViewportUtils;

/**
 * Created by Jay Nguyen on 4/7/2017.
 */

public class GridRenderSystem extends EntitySystem {

    private final Viewport viewport;
    private final ShapeRenderer shapeRenderer;

    // == constructor ==
    public GridRenderSystem(Viewport viewport, ShapeRenderer shapeRenderer) {
        this.viewport = viewport;
        this.shapeRenderer = shapeRenderer;
    }

    @Override
    public void update(float deltaTime) {
        viewport.apply();
        ViewportUtils.drawGrid(viewport,shapeRenderer);
    }
}
