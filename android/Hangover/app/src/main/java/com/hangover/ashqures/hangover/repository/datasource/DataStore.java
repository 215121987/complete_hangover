package com.hangover.ashqures.hangover.repository.datasource;

import com.hangover.ashqures.hangover.entity.ItemEntity;

import java.util.List;
import java.util.Map;

import rx.Observable;

/**
 * Created by ashqures on 8/6/16.
 */
public interface DataStore {


    Observable<List<ItemEntity>> getItems(Map<String, String> paramMap);
}
