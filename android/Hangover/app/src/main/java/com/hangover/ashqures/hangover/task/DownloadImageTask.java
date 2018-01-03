package com.hangover.ashqures.hangover.task;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.hangover.ashqures.hangover.adapter.ImageViewHolder;
import com.hangover.ashqures.hangover.adapter.StoreAdapter;
import com.hangover.ashqures.hangover.dto.StatusDTO;
import com.hangover.ashqures.hangover.entity.ItemEntity;
import com.hangover.ashqures.hangover.exception.DefaultErrorBundle;
import com.hangover.ashqures.hangover.exception.NetworkConnectionException;
import com.hangover.ashqures.hangover.exception.RepositoryErrorBundle;
import com.hangover.ashqures.hangover.interactor.DefaultSubscriber;
import com.hangover.ashqures.hangover.service.imp.RestResponse;
import com.hangover.ashqures.hangover.view.LoadDataView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.HttpStatus;

/**
 * Created by ashqures on 8/13/16.
 */
public class DownloadImageTask extends BaseTask<ImageViewHolder, ImageViewHolder> {


    public DownloadImageTask(Context context) {
        super(context);
    }


    @Override
    public DefaultSubscriber<ImageViewHolder> getSubscriber() {
        return null;
    }

    @Override
    public RestResponse getDataFromApi(Map<String, String> paramMap) throws RepositoryErrorBundle {
        return null;
    }

    @Override
    protected AsyncTaskResult<ImageViewHolder> doInBackground(ImageViewHolder[] params) {
        AsyncTaskResult<ImageViewHolder> asyncTaskResult;
        if (isThereInternetConnection()) {
            try {
                ImageViewHolder imageViewHolder = params[0];
                URL imageURL = new URL(imageViewHolder.getImageURL());
                imageViewHolder.setBitmap(BitmapFactory.decodeStream(imageURL.openStream()));
                asyncTaskResult = new AsyncTaskResult<ImageViewHolder>(imageViewHolder);
            }catch (IOException re){
                asyncTaskResult = new AsyncTaskResult<>(new DefaultErrorBundle(re));
            }
        } else {
            asyncTaskResult = new AsyncTaskResult<>(new NetworkConnectionException());
        }
        return asyncTaskResult;
    }

    @Override
    protected void onPostExecute(final AsyncTaskResult<ImageViewHolder> asyncTaskResult) {
        if(!isCancelled()){
            if(!asyncTaskResult.hasError()){
                ImageViewHolder imageViewHolder = asyncTaskResult.getResult();
                if(null!=imageViewHolder.getBitmap())
                    imageViewHolder.updateImageResource();
            }
        }
        super.onPostExecute(asyncTaskResult);
    }


}
