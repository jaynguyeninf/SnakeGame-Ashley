package com.mygdx.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.components.PositionComponent;
import com.mygdx.game.components.SizeComponent;
import com.mygdx.game.components.TextureComponent;
import com.mygdx.game.components.ZOrderComponent;
import com.mygdx.game.utils.Mappers;
import com.mygdx.game.utils.ZOrderComparator;

/**
 * Created by Jay Nguyen on 4/10/2017.
 */


public class TextureRenderSystem extends SortedIteratingSystem {//no need to use Array to sort

    private static final Family FAMILY = Family.all(
            TextureComponent.class,
            PositionComponent.class,
            SizeComponent.class,
            ZOrderComponent.class
    ).get();

    private final SpriteBatch batch;
    private final Viewport viewport;

    public TextureRenderSystem(SpriteBatch batch, Viewport viewport) {
        super(FAMILY, ZOrderComparator.INSTANCE);
        this.batch = batch;
        this.viewport = viewport;
    }

    @Override
    public void update(float deltaTime) {

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        super.update(deltaTime);

        batch.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        TextureComponent textureComponent = Mappers.TEXTURE_COMPONENT.get(entity);
        PositionComponent positionComponent = Mappers.POSITION_COMPONENT.get(entity);
        SizeComponent sizeComponent = Mappers.SIZE_COMPONENT.get(entity);

        batch.draw(textureComponent.textureRegion,
                positionComponent.x, positionComponent.y,
                sizeComponent.width, sizeComponent.height);
    }

}


