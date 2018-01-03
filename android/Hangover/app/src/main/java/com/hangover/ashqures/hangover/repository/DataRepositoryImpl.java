package com.hangover.ashqures.hangover.repository;

import com.hangover.ashqures.hangover.dto.ItemDTO;
import com.hangover.ashqures.hangover.entity.mapper.DataMapper;
import com.hangover.ashqures.hangover.interactor.DataRepository;
import com.hangover.ashqures.hangover.repository.datasource.DataStore;
import com.hangover.ashqures.hangover.repository.datasource.DataStoreFactory;

import java.util.List;
import java.util.Map;

import rx.Observable;

/**
 * Created by ashqures on 8/6/16.
 */
public class DataRepositoryImpl implements DataRepository{

    private DataStoreFactory dataStoreFactory;
    private DataMapper dataMapper;


    public DataRepositoryImpl(DataStoreFactory dataStoreFactory, DataMapper dataMapper){
        this.dataStoreFactory = dataStoreFactory;
        this.dataMapper = dataMapper;
    }


    public Observable<List<ItemDTO>> getItems(Map<String, String> paramMap) {
        //we always get all items from the cloud
        final DataStore dataStore = this.dataStoreFactory.createCloudDataStore();
        return null;// dataStore.getItems(paramMap).map(this.dataMapper::transform);
    }

    @Override
    public Observable<ItemDTO> getItem(Long itemId) {
        return null;
    }

}
