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
package com.hangover.ashqures.hangover.interactor;

import com.hangover.ashqures.hangover.executor.PostExecutionThread;
import com.hangover.ashqures.hangover.executor.ThreadExecutor;

import javax.inject.Inject;
import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link ItemDTO}.
 */
public class GetItemDetails extends UseCase {

  private final Long itemId;
  private final DataRepository dataRepository;

  @Inject
  public GetItemDetails(Long itemId, DataRepository dataRepository,
                        ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.itemId = itemId;
    this.dataRepository = dataRepository;
  }

  @Override
  protected Observable buildUseCaseObservable() {
    return this.dataRepository.getItem(this.itemId);
  }
}
