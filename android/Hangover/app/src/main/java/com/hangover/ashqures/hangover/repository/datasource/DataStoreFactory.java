package com.hangover.ashqures.hangover.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import com.hangover.ashqures.hangover.cache.DataCache;
import com.hangover.ashqures.hangover.entity.mapper.JsonMapper;
import com.hangover.ashqures.hangover.service.RestApi;
import com.hangover.ashqures.hangover.service.RestApiAdvance;
import com.hangover.ashqures.hangover.service.imp.RestApiAdvanceImpl;
import com.hangover.ashqures.hangover.service.imp.RestApiImpl;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ashqures on 8/6/16.
 */
@Singleton
public class DataStoreFactory {

    private Context context;
    private DataCache dataCache;


    @Inject
    public DataStoreFactory(@NonNull Context context, @NonNull DataCache dataCache){
        this.context = context;
        this.dataCache = dataCache;
    }

    public DataStore createCloudDataStore() {
        JsonMapper jsonMapper = new JsonMapper();
        RestApiAdvance restApi = new RestApiAdvanceImpl(this.context, jsonMapper);
        return new CloudDataStore(restApi, this.dataCache);
    }

}
