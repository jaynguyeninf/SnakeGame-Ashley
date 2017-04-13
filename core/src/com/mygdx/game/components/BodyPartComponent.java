package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Jay Nguyen on 4/8/2017.
 */

public class BodyPartComponent implements Component,Pool.Poolable {

    //Flag used to fix avoiding collision with just added  body parts
    public boolean justAdded = true;

    @Override
    public void reset() {
        justAdded = true;
    }
}
