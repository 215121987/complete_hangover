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

import java.util.Map;

import javax.inject.Inject;
import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link ItemDTO}.
 */
public class GetItems extends UseCase {

  private final DataRepository dataRepository;
  private final Map<String,String> paramMap;

  @Inject
  public GetItems(Map<String,String> paramMap, DataRepository dataRepository, ThreadExecutor threadExecutor,
                  PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.paramMap = paramMap;
    this.dataRepository = dataRepository;
  }

  @Override
  public Observable buildUseCaseObservable() {
    return this.dataRepository.getItems(this.paramMap);
  }
}
