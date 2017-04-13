package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Jay Nguyen on 4/10/2017.
 */

// Used to draw textures in z-order
public class ZOrderComponent implements Component, Pool.Poolable {

    public int z = -1;


    @Override
    public void reset() {
        z = -1;
    }
}
