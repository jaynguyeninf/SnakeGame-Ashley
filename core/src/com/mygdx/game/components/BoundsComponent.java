package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Jay Nguyen on 4/7/2017.
 */

public class BoundsComponent implements Component, Pool.Poolable {

    public Rectangle bounds = new Rectangle(0, 0, 1, 1); // x,y,width,height

    @Override
    public void reset() {
        bounds.setPosition(0, 0);
        bounds.setSize(1, 1);
    }
}
