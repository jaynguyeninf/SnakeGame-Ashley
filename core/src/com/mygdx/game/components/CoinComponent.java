package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Jay Nguyen on 4/8/2017.
 */

public class CoinComponent implements Component, Pool.Poolable {

    public boolean available;

    @Override
    public void reset() {
        available = false;
    }
}
