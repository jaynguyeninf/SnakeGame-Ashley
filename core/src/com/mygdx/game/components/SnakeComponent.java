package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Jay Nguyen on 4/7/2017.
 */

public class SnakeComponent implements Component,Pool.Poolable{

    public Entity head;
    public final Array<Entity> bodyParts = new Array<Entity>();

    public boolean hasBodyParts(){
        return bodyParts.size > 0;
    }

    @Override
    public void reset() {
        head = null;
        bodyParts.clear();
    }
}
