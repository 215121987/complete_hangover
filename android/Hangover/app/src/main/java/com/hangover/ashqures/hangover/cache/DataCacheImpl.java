package com.hangover.ashqures.hangover.cache;

import android.content.Context;

import com.hangover.ashqures.hangover.cache.serializer.JsonSerializer;
import com.hangover.ashqures.hangover.entity.ItemEntity;
import com.hangover.ashqures.hangover.executor.ThreadExecutor;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ashqures on 8/6/16.
 */
@Singleton
public class DataCacheImpl implements DataCache {


    private static final String SETTINGS_FILE_NAME = "com.hangover.ashqures.hangover.SETTINGS";
    private static final String SETTINGS_KEY_LAST_CACHE_UPDATE = "last_cache_update";

    private static final String DEFAULT_FILE_NAME = "user_";
    private static final long EXPIRATION_TIME = 60 * 10 * 1000;

    private final Context context;
    private final File cacheDir;
    private final JsonSerializer serializer;
    private final FileManager fileManager;
    private final ThreadExecutor threadExecutor;

    @Inject
    public DataCacheImpl(Context context, JsonSerializer cacheSerializer,
                         FileManager fileManager, ThreadExecutor executor) {
        if (context == null || cacheSerializer == null || fileManager == null || executor == null) {
            throw new IllegalArgumentException("Invalid null parameter");
        }
        this.context = context.getApplicationContext();
        this.cacheDir = this.context.getCacheDir();
        this.serializer = cacheSerializer;
        this.fileManager = fileManager;
        this.threadExecutor = executor;
    }


    @Override
    public void put(ItemEntity itemEntity) {

    }

    @Override
    public void get(Long itemId) {

    }
}
