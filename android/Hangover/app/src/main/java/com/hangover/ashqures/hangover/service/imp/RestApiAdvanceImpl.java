package com.hangover.ashqures.hangover.service.imp;

import android.content.Context;

import com.hangover.ashqures.hangover.entity.ItemEntity;
import com.hangover.ashqures.hangover.entity.mapper.JsonMapper;
import com.hangover.ashqures.hangover.service.RestApiAdvance;

import java.util.List;
import java.util.Map;

import rx.Observable;

/**
 * Created by ashqures on 8/22/16.
 */
public class RestApiAdvanceImpl extends RestApiImpl implements RestApiAdvance{

    public RestApiAdvanceImpl(Context context, JsonMapper jsonMapper) {
        super(context,jsonMapper);
    }


    @Override
    public Observable<List<ItemEntity>> getItemList(Map<String, String> paramMap) {
        /* return Observable.create(subscriber -> {
            if (isThereInternetConnection()) {
                try {
                    RestResponse restResponse = getItemsFromApi(paramMap);
                    if (null != restResponse && restResponse.getCode().equals(HttpStatus.SC_OK)) {
                        subscriber.onNext(this.jsonMapper.transformCollection(restResponse.getResponse()));
                        subscriber.onCompleted();
                    } else {
                        StatusDTO statusDTO = this.jsonMapper.transformStatus(restResponse.getResponse());
                        subscriber.onError(new DefaultErrorBundle(statusDTO));
                    }
                } catch (Exception e) {
                    subscriber.onError(new DefaultErrorBundle(e));
                }
            } else {
                subscriber.onError(new NetworkConnectionException());
            }
        });*/
        return null;
    }
}
