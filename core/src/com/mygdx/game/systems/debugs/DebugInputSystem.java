package com.mygdx.game.systems.debugs;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Logger;

/**
 * Created by Jay Nguyen on 4/12/2017.
 */

public class DebugInputSystem extends EntitySystem {

    private static final Logger log = new Logger(DebugInputSystem.class.getSimpleName(), Logger.DEBUG);

    private EntitySystem debugGridSystem;
    private EntitySystem debugRenderSystem;

    private boolean debugGrid = true;
    private boolean debugRender = true;


    @Override
    public void addedToEngine(Engine engine) {
        debugGridSystem = engine.getSystem(GridRenderSystem.class);
        debugRenderSystem = engine.getSystem(DebugRenderSystem.class);
    }

    @Override
    public void update(float deltaTime) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.F1)) {
            debugGrid = !debugGrid;
            debugGridSystem.setProcessing(debugGrid);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.F2)) {
            debugRender = !debugRender;
            debugRenderSystem.setProcessing(debugRender);
        }
    }

}
