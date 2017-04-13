package com.mygdx.game.systems.debugs;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.utils.debug.DebugCameraController;

/**
 * Created by Jay Nguyen on 4/7/2017.
 */

public class DebugCameraSystem extends EntitySystem {

    private static final DebugCameraController DEBUG_CAMERA_CONTROLLER = new DebugCameraController();

    private final OrthographicCamera camera;

    public DebugCameraSystem(float startX, float startY, OrthographicCamera camera) {
        this.camera = camera;
        DEBUG_CAMERA_CONTROLLER.setStartPosition(startX,startY);
    }

    @Override
    public void update(float deltaTime) {
        DEBUG_CAMERA_CONTROLLER.handleDebugInput(deltaTime);
        DEBUG_CAMERA_CONTROLLER.applyTo(camera);
    }
}
