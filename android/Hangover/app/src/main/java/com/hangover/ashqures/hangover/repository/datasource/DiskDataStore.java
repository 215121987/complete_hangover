package com.hangover.ashqures.hangover.repository.datasource;

import com.hangover.ashqures.hangover.cache.DataCache;
import com.hangover.ashqures.hangover.entity.ItemEntity;

import java.util.List;
import java.util.Map;

import rx.Observable;

/**
 * Created by ashqures on 8/6/16.
 */
public class DiskDataStore implements DataStore {

    private final DataCache dataCache;

    public DiskDataStore(DataCache dataCache){
        this.dataCache = dataCache;
    }



    @Override
    public Observable<List<ItemEntity>> getItems(Map<String, String> paramMap) {
        return null;
    }
}
