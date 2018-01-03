/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hangover.ashqures.hangover.cache;

import android.content.Context;

import com.hangover.ashqures.hangover.cache.serializer.JsonSerializer;
import com.hangover.ashqures.hangover.entity.CartItemEntity;
import com.hangover.ashqures.hangover.executor.ThreadExecutor;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * {@link CartCache} implementation.
 */
@Singleton
public class CartCacheImpl implements CartCache {

  private static final String SETTINGS_FILE_NAME = "com.hangover.ashqures.hangover.SETTINGS";
  private static final String SETTINGS_KEY_LAST_CACHE_UPDATE = "last_cache_update";

  private static final String DEFAULT_FILE_NAME = "cart_";
  private static final long EXPIRATION_TIME = 60 * 10 * 1000;

  private final Context context;
  private final File cacheDir;
  private final JsonSerializer serializer;
  private final FileManager fileManager;
  private final ThreadExecutor threadExecutor;

  /**
   * Constructor of the class {@link CartCacheImpl}.
   *
   * @param context A
   * @param userCacheSerializer {@link JsonSerializer} for object serialization.
   * @param fileManager {@link FileManager} for saving serialized objects to the file system.
   */
  @Inject
  public CartCacheImpl(Context context, JsonSerializer userCacheSerializer,
                       FileManager fileManager, ThreadExecutor executor) {
    if (context == null || userCacheSerializer == null || fileManager == null || executor == null) {
      throw new IllegalArgumentException("Invalid null parameter");
    }
    this.context = context.getApplicationContext();
    this.cacheDir = this.context.getCacheDir();
    this.serializer = userCacheSerializer;
    this.fileManager = fileManager;
    this.threadExecutor = executor;
  }

  @Override public Observable<CartItemEntity> get(final Long cartId) {
    /*return Observable.create(subscriber -> {
      File userEntityFile = CartCacheImpl.this.buildFile(cartId);
      String fileContent = CartCacheImpl.this.fileManager.readFileContent(userEntityFile);
      CartItemEntity cartItemEntity = CartCacheImpl.this.serializer.deserialize(fileContent);

      if (userEntity != null) {
        subscriber.onNext(userEntity);
        subscriber.onCompleted();
      } else {
        subscriber.onError(new DataNotFoundException());
      }
    });*/
    return null;
  }

  @Override public void put(CartItemEntity cartItemEntity) {
    if (cartItemEntity != null) {
      File userEntityFile = this.buildFile(cartItemEntity.getId());
      if (!isCached(cartItemEntity.getId())) {
        String jsonString = this.serializer.serialize(cartItemEntity);
        this.executeAsynchronously(new CacheWriter(this.fileManager, userEntityFile,
            jsonString));
        setLastCacheUpdateTimeMillis();
      }
    }
  }

  @Override public boolean isCached(Long cartId) {
    File userEntitiyFile = this.buildFile(cartId);
    return this.fileManager.exists(userEntitiyFile);
  }

  @Override public boolean isExpired() {
    long currentTime = System.currentTimeMillis();
    long lastUpdateTime = this.getLastCacheUpdateTimeMillis();

    boolean expired = ((currentTime - lastUpdateTime) > EXPIRATION_TIME);

    if (expired) {
      this.evictAll();
    }

    return expired;
  }

  @Override public void evictAll() {
    this.executeAsynchronously(new CacheEvictor(this.fileManager, this.cacheDir));
  }

  /**
   * Build a file, used to be inserted in the disk cache.
   *
   * @param cartId The id user to build the file.
   * @return A valid file.
   */
  private File buildFile(Long cartId) {
    StringBuilder fileNameBuilder = new StringBuilder();
    fileNameBuilder.append(this.cacheDir.getPath());
    fileNameBuilder.append(File.separator);
    fileNameBuilder.append(DEFAULT_FILE_NAME);
    fileNameBuilder.append(cartId);

    return new File(fileNameBuilder.toString());
  }

  /**
   * Set in millis, the last time the cache was accessed.
   */
  private void setLastCacheUpdateTimeMillis() {
    long currentMillis = System.currentTimeMillis();
    this.fileManager.writeToPreferences(this.context, SETTINGS_FILE_NAME,
        SETTINGS_KEY_LAST_CACHE_UPDATE, currentMillis);
  }

  /**
   * Get in millis, the last time the cache was accessed.
   */
  private long getLastCacheUpdateTimeMillis() {
    return this.fileManager.getFromPreferences(this.context, SETTINGS_FILE_NAME,
        SETTINGS_KEY_LAST_CACHE_UPDATE);
  }

  /**
   * Executes a {@link Runnable} in another Thread.
   *
   * @param runnable {@link Runnable} to execute
   */
  private void executeAsynchronously(Runnable runnable) {
    this.threadExecutor.execute(runnable);
  }

  /**
   * {@link Runnable} class for writing to disk.
   */
  private static class CacheWriter implements Runnable {
    private final FileManager fileManager;
    private final File fileToWrite;
    private final String fileContent;

    CacheWriter(FileManager fileManager, File fileToWrite, String fileContent) {
      this.fileManager = fileManager;
      this.fileToWrite = fileToWrite;
      this.fileContent = fileContent;
    }

    @Override public void run() {
      this.fileManager.writeToFile(fileToWrite, fileContent);
    }
  }

  /**
   * {@link Runnable} class for evicting all the cached files
   */
  private static class CacheEvictor implements Runnable {
    private final FileManager fileManager;
    private final File cacheDir;

    CacheEvictor(FileManager fileManager, File cacheDir) {
      this.fileManager = fileManager;
      this.cacheDir = cacheDir;
    }

    @Override public void run() {
      this.fileManager.clearDirectory(this.cacheDir);
    }
  }
}
