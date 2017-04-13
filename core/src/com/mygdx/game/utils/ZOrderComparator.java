package com.mygdx.game.utils;

import com.badlogic.ashley.core.Entity;

import java.util.Comparator;

/**
 * Created by Jay Nguyen on 4/10/2017.
 */


//Custom Comparator
public class ZOrderComparator implements Comparator<Entity> {

    public static final ZOrderComparator INSTANCE = new ZOrderComparator();

    private ZOrderComparator(){}

    @Override
    public int compare(Entity entity1, Entity entity2) {
        return Float.compare(
                Mappers.Z_ORDER_COMPONENT.get(entity1).z,
                Mappers.Z_ORDER_COMPONENT.get(entity2).z
        );
    }
}
