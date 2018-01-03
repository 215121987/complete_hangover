package com.hangover.ashqures.hangover.cache;


import com.hangover.ashqures.hangover.entity.ItemEntity;

import java.util.Observable;

/**
 * Created by ashqures on 8/6/16.
 */
public interface DataCache {


    void put(ItemEntity itemEntity);

    public void get(final Long itemId);
}
