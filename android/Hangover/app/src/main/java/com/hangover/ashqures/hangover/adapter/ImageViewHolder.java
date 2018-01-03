package com.hangover.ashqures.hangover.adapter;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by ashqures on 8/13/16.
 */
public class ImageViewHolder {

    ImageView imageView;
    String imageURL;
    Bitmap bitmap;

    public ImageViewHolder(ImageView imageView, String imageURL) {
        this.imageView = imageView;
        this.imageURL = imageURL;
    }


    public String getImageURL() {
        return imageURL;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void updateImageResource(){
        this.imageView.setImageBitmap(this.bitmap);
    }
}
