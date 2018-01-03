package com.hangover.ashqures.hangover.service;

import com.hangover.ashqures.hangover.entity.ItemEntity;

import java.util.List;
import java.util.Map;

import rx.Observable;

/**
 * Created by ashqures on 8/22/16.
 */
public interface RestApiAdvance extends RestApi {


    Observable<List<ItemEntity>> getItemList(Map<String, String> paramMap);
}
