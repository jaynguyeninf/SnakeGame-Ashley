package com.mygdx.game.systems;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.common.GameManager;

/**
 * Created by Jay Nguyen on 4/10/2017.
 */

public class HudRenderSystem extends EntitySystem {

    private static final float PADDING = 10;

    private final Viewport hudViewport;
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final GlyphLayout glyphLayout = new GlyphLayout();

    public HudRenderSystem(Viewport hudViewport, SpriteBatch batch, BitmapFont font) {
        this.hudViewport = hudViewport;
        this.batch = batch;
        this.font = font;
    }

    @Override
    public void update(float deltaTime) {
        GameManager.INSTANCE.updateDisplayScores(deltaTime);

        hudViewport.apply();
        batch.setProjectionMatrix(hudViewport.getCamera().combined);
        batch.begin();

        draw();

        batch.end();
    }

    private void draw() {



        String displayHighScoreText = "High Score: " + (int)GameManager.INSTANCE.getDisplayHighScore();
        glyphLayout.setText(font, displayHighScoreText);
        font.draw(batch, glyphLayout, PADDING, hudViewport.getWorldHeight() - PADDING);

        String displayScoreText = "Score: " + (int)GameManager.INSTANCE.getDisplayScore();
        glyphLayout.setText(font, displayScoreText);
        font.draw(batch, glyphLayout, hudViewport.getWorldWidth() - glyphLayout.width, hudViewport.getWorldHeight() - PADDING);
    }
}
