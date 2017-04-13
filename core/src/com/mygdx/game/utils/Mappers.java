package com.mygdx.game.utils;

import com.badlogic.ashley.core.ComponentMapper;
import com.mygdx.game.components.BodyPartComponent;
import com.mygdx.game.components.BoundsComponent;
import com.mygdx.game.components.CoinComponent;
import com.mygdx.game.components.DirectionComponent;
import com.mygdx.game.components.MovementComponent;
import com.mygdx.game.components.PositionComponent;
import com.mygdx.game.components.SizeComponent;
import com.mygdx.game.components.SnakeComponent;
import com.mygdx.game.components.TextureComponent;
import com.mygdx.game.components.ZOrderComponent;

/**
 * Created by Jay Nguyen on 4/7/2017.
 */

public final class Mappers {

    public static final ComponentMapper<PositionComponent> POSITION_COMPONENT = ComponentMapper.getFor(PositionComponent.class);
    public static final ComponentMapper<BoundsComponent> BOUNDS_COMPONENT = ComponentMapper.getFor(BoundsComponent.class);
    public static final ComponentMapper<SizeComponent> SIZE_COMPONENT = ComponentMapper.getFor(SizeComponent.class);
    public static final ComponentMapper<SnakeComponent> SNAKE_COMPONENT = ComponentMapper.getFor(SnakeComponent.class);
    public static final ComponentMapper<DirectionComponent> DIRECTION_COMPONENT = ComponentMapper.getFor(DirectionComponent.class);
    public static final ComponentMapper<MovementComponent> MOVEMENT_COMPONENT = ComponentMapper.getFor(MovementComponent.class);
    public static final ComponentMapper<CoinComponent> COIN_COMPONENT = ComponentMapper.getFor(CoinComponent.class);
    public static final ComponentMapper<BodyPartComponent> BODY_PART_COMPONENT = ComponentMapper.getFor(BodyPartComponent.class);
    public static final ComponentMapper<TextureComponent> TEXTURE_COMPONENT = ComponentMapper.getFor(TextureComponent.class);
    public static final ComponentMapper<ZOrderComponent> Z_ORDER_COMPONENT = ComponentMapper.getFor(ZOrderComponent.class);


    private Mappers(){}
}
