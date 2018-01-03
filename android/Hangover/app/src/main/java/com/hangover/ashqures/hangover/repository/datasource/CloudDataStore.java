package com.hangover.ashqures.hangover.repository.datasource;

import com.hangover.ashqures.hangover.cache.DataCache;
import com.hangover.ashqures.hangover.entity.ItemEntity;
import com.hangover.ashqures.hangover.service.RestApi;
import com.hangover.ashqures.hangover.service.RestApiAdvance;
import com.hangover.ashqures.hangover.service.imp.RestApiImpl;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by ashqures on 8/6/16.
 */
public class CloudDataStore implements DataStore {


    private final RestApiAdvance restApi;
    private final DataCache dataCache;

   /* private final Action1<ItemEntity> saveToCacheAction = itemEntity -> {
        if (itemEntity != null) {
            CloudDataStore.this.dataCache.put(itemEntity);
        }
    };*/

    public CloudDataStore(RestApiAdvance restApi, DataCache dataCache){
        this.restApi = restApi;
        this.dataCache = dataCache;
    }

    @Override
    public Observable<List<ItemEntity>> getItems(Map<String, String> paramMap) {
        return this.restApi.getItemList(paramMap);
    }
}
