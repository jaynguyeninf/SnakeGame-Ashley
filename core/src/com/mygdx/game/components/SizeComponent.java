package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Jay Nguyen on 4/7/2017.
 */

public class SizeComponent implements Component, Pool.Poolable {

    public float width = 1;
    public float height = 1;

    @Override
    public void reset() {
        width = 1;
        height = 1;
    }
}
