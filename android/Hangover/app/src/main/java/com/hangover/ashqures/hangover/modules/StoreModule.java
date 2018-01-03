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
package com.hangover.ashqures.hangover.modules;


import android.os.AsyncTask;

import com.hangover.ashqures.hangover.component.PerActivity;
import com.hangover.ashqures.hangover.dto.ItemDTO;
import com.hangover.ashqures.hangover.entity.ItemEntity;
import com.hangover.ashqures.hangover.fragments.StoreFragment;
import com.hangover.ashqures.hangover.fragments.StoreItemFragment;
import com.hangover.ashqures.hangover.task.ApiBuilder;
import com.hangover.ashqures.hangover.task.BaseTask;
import com.hangover.ashqures.hangover.task.CommonTask;
import com.hangover.ashqures.hangover.task.GetStoreDataTask;
import com.hangover.ashqures.hangover.task.TaskConstant;
import com.hangover.ashqures.hangover.view.StoreView;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides user related collaborators.
 */
@Module
public class StoreModule {

  private Long itemId = -1L;

  public StoreModule() {}

  public StoreModule(Long itemId) {
    this.itemId = itemId;
  }

  @Provides
  @PerActivity
  @Named("storeTask")
  GetStoreDataTask provideStoreTask(StoreFragment storeFragment) {
    return null;//new GetStoreDataTask(storeFragment);
  }


  @Provides
  @PerActivity
  @Named("storeItemTask")
  CommonTask provideStoreItemTask(StoreItemFragment storeItemFragment) {
    String url = new ApiBuilder(TaskConstant.STORE_ITEM_URL)
            .addPath(this.itemId+"").build();
    return null;//new CommonTask<ItemEntity>(storeItemFragment,url);
  }

}