package com.hangover.ashqures.hangover.service;

import com.hangover.ashqures.hangover.dto.ItemDTO;
import com.hangover.ashqures.hangover.exception.RepositoryErrorBundle;
import com.hangover.ashqures.hangover.service.imp.ApiConnection;
import com.hangover.ashqures.hangover.util.RestConstants;

import org.json.JSONObject;

import java.util.Map;

import cz.msebera.android.httpclient.HttpStatus;

/**
 * Created by ashqures on 7/27/16.
 */
public class StoreService {


    public JSONObject getStore(Map<String,String> paramMap) throws RepositoryErrorBundle, Exception {
        String url = RestConstants.STORE_URL;
        ApiConnection apiConnection = new ApiConnection(url);
        apiConnection.addParams(paramMap);
        apiConnection.execute(ApiConnection.RequestMethod.GET);
        JSONObject obj = null;
        /*if(apiConnection.getResponseCode()== HttpStatus.SC_OK){
            obj =  new JSONObject(apiConnection.getResponse());
        }*/
        return obj;
    }

    public ItemDTO getStoreItem(Long itemId) throws RepositoryErrorBundle, Exception {
        String url = RestConstants.STORE_ITEM_URL;
        url = url.replace("PATH_PARAM", itemId+"");
        ApiConnection apiConnection = new ApiConnection(url);
        apiConnection.execute(ApiConnection.RequestMethod.GET);
        ItemDTO itemDTO = null;
        /*if(apiConnection.getResponseCode()== HttpStatus.SC_OK){
            JSONObject obj =  new JSONObject(apiConnection.getResponse());
            itemDTO = new ItemDTO();
            itemDTO.setId(itemId);
            itemDTO.setName(obj.getString("name"));
        }*/
        return itemDTO;
    }



}
