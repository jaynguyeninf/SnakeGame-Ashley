package com.mygdx.game.screens.game;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.SnakeGame;
import com.mygdx.game.asset_helpers.AssetDescriptors;
import com.mygdx.game.common.GameManager;
import com.mygdx.game.configs.GameConfig;
import com.mygdx.game.screens.menu.MenuScreen;
import com.mygdx.game.systems.BoundsSystem;
import com.mygdx.game.systems.CollisionSystem;
import com.mygdx.game.systems.DirectionSystem;
import com.mygdx.game.systems.HudScene2dDisplaySystem;
import com.mygdx.game.systems.MovementSystem;
import com.mygdx.game.systems.SpawnCoinSystem;
import com.mygdx.game.systems.TextureRenderSystem;
import com.mygdx.game.systems.UserInputSystem;
import com.mygdx.game.systems.WorldWrapSystem;
import com.mygdx.game.systems.debugs.DebugCameraSystem;
import com.mygdx.game.systems.debugs.DebugInputSystem;
import com.mygdx.game.systems.debugs.DebugRenderSystem;
import com.mygdx.game.systems.debugs.GridRenderSystem;
import com.mygdx.game.systems.passive.EntityFactorySystem;
import com.mygdx.game.systems.passive.SnakeSystem;
import com.mygdx.game.systems.passive.SoundSystem;
import com.mygdx.game.systems.passive.StartUpSystem;


public class GameScreen extends ScreenAdapter {

    private static final Logger log = new Logger(GameScreen.class.getSimpleName(), Logger.DEBUG);

    private final SnakeGame game;
    private final AssetManager assetManager;
    private final SpriteBatch batch;

    private Viewport viewport;
    private Viewport hudViewport;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;
    private PooledEngine engine;
    private EntityFactorySystem factory;


    public GameScreen(SnakeGame game) {
        this.game = game;
        assetManager = game.getAssetManager();
        batch = game.getBatch();
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        hudViewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT);
        shapeRenderer = new ShapeRenderer();
        font = assetManager.get(AssetDescriptors.UI_FONT);
        engine = new PooledEngine();

        factory = new EntityFactorySystem(assetManager);

        //add systems
        engine.addSystem(factory);
        engine.addSystem(new SoundSystem(assetManager));
        engine.addSystem(new GridRenderSystem(viewport, shapeRenderer));
        engine.addSystem(new DebugCameraSystem(GameConfig.WORLD_X_CENTER, GameConfig.WORLD_Y_CENTER, camera));
        engine.addSystem(new DebugRenderSystem(viewport, shapeRenderer));

        engine.addSystem(new SnakeSystem());
        engine.addSystem(new DirectionSystem());
        engine.addSystem(new MovementSystem());
        engine.addSystem(new BoundsSystem());
        engine.addSystem(new UserInputSystem());
        engine.addSystem(new WorldWrapSystem());
        engine.addSystem(new SpawnCoinSystem());
        engine.addSystem(new CollisionSystem());
        engine.addSystem(new TextureRenderSystem(batch, viewport));
        engine.addSystem(new HudScene2dDisplaySystem(game, hudViewport));

        engine.addSystem(new DebugInputSystem());
        engine.addSystem(new StartUpSystem());

        /* Add entities.
            Doesn't matter the order because Z Component will take care of it
         */

        GameManager.INSTANCE.reset(); //reset game otherwise will stay in MenuScreen
    }


    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        engine.update(delta);

        if (GameManager.INSTANCE.isGameOver()) {
            game.setScreen(new MenuScreen(game));
        }

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        hudViewport.update(width, height, true);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        engine.removeAllEntities();//dispose or remove all entities
    }
}
