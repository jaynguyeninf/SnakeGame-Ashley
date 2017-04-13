package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Jay Nguyen on 4/7/2017.
 */

public class PositionComponent implements Component, Pool.Poolable {

    public float x;
    public float y;

    @Override
    public void reset() {
        x = 0;
        y = 0;
    }
}
