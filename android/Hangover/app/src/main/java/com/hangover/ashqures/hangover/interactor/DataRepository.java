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

import com.hangover.ashqures.hangover.dto.ItemDTO;

import java.util.List;
import java.util.Map;

import rx.Observable;

/**
 * Interface that represents a Repository for getting {@link ItemDTO} related data.
 */
public interface DataRepository {
  /**
   * Get an {@link Observable} which will emit a List of {@link ItemDTO}.
   */
  Observable<List<ItemDTO>> getItems(Map<String, String> paramMap);

  /**
   * Get an {@link Observable} which will emit a {@link ItemDTO}.
   *
   * @param itemId The Item id used to retrieve Item data.
   */
  Observable<ItemDTO> getItem(final Long itemId);
}
