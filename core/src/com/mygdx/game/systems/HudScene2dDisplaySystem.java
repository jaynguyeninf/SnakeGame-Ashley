package com.mygdx.game.systems;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.SnakeGame;
import com.mygdx.game.asset_helpers.AssetDescriptors;
import com.mygdx.game.common.GameManager;
import com.mygdx.game.configs.GameConfig;

/**
 * Created by Jay Nguyen on 4/11/2017.
 */

public class HudScene2dDisplaySystem extends EntitySystem {

    private final SnakeGame game;
    private final SpriteBatch batch;
    private final AssetManager assetManager;

    private Stage stage;
    private Viewport hudViewport;
    private Skin skin;
    private Table table;
    private Label scoreLabel, highScoreLabel;


    public HudScene2dDisplaySystem(SnakeGame game, Viewport hudViewport, SpriteBatch batch, AssetManager assetManager) {
        this.game = game;
        this.hudViewport = hudViewport;
        this.batch = batch;
        this.assetManager = assetManager;

        stage = new Stage(hudViewport, game.getBatch());

        skin = assetManager.get(AssetDescriptors.UI_SKIN_SCENE2D);
        init();
    }

    @Override
    public void update(float deltaTime) {

        GameManager.INSTANCE.updateDisplayScores(deltaTime);


        stage.act();
        stage.draw();

        //update text
        highScoreLabel.setText("High Score: " + (int)GameManager.INSTANCE.getDisplayHighScore());
        scoreLabel.setText("Score: " + (int)GameManager.INSTANCE.getDisplayScore());


    }

    private void init() {
        table = new Table(skin);
        table.setBounds(0,0, GameConfig.WORLD_WIDTH,GameConfig.WORLD_HEIGHT);

        String highScoreText = "";
        highScoreLabel = new Label(highScoreText, skin);

        String scoreText = "";
        scoreLabel = new Label(scoreText, skin);


        table.add(highScoreLabel).expand().left().top();
        table.add(scoreLabel).right().top().row();

        table.setFillParent(true);
        table.pack();

        stage.addActor(table);
    }
}
