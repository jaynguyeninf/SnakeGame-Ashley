package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Jay Nguyen on 4/10/2017.
 */

public class TextureComponent implements Component, Pool.Poolable {

    public TextureRegion textureRegion;

    @Override
    public void reset() {
        textureRegion = null;
    }
}
