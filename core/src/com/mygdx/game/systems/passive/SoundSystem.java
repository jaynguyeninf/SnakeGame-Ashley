package com.mygdx.game.systems.passive;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.asset_helpers.AssetDescriptors;

/**
 * Created by Jay Nguyen on 4/6/2017.
 */

public class SoundSystem extends EntitySystem {

    private final AssetManager assetManager;

    private Sound coinSound, loseSound;

    public SoundSystem(AssetManager assetManager) {
        this.assetManager = assetManager;
        coinSound = assetManager.get(AssetDescriptors.COIN_SOUND);
        loseSound = assetManager.get(AssetDescriptors.LOSE_SOUND);
    }

    public void playCoinSound(){
        coinSound.play();
    }

    public void playLoseSound(){
        loseSound.play();
    }

    // "passive" system
    @Override
    public void update(float deltaTime) {
    }

    @Override
    public boolean checkProcessing() {
        return false;
    }
}
