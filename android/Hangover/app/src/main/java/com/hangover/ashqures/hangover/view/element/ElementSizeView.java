package com.hangover.ashqures.hangover.view.element;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;

import com.hangover.ashqures.hangover.activity.R;

/**
 * Created by ashqures on 8/15/16.
 */
public class ElementSizeView extends Button {


    public ElementSizeView(SizeViewBuilder sizeViewBuilder) {
        super(sizeViewBuilder.context);
        setBackgroundResource(R.drawable.circle);
        setText(sizeViewBuilder.text);

    }

    public ElementSizeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ElementSizeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    static class SizeViewBuilder{

        private Context context;
        private String text;
        private int drawableId;

        private SizeViewBuilder(Context context){
            this.context = context;
        }

        public SizeViewBuilder text(String text){
            this.text = text;
            return this;
        }

        public SizeViewBuilder drawableId(int drawableId){
            this.drawableId = drawableId;
            return this;
        }

        public ElementSizeView build(){
            return new ElementSizeView(this);
        }
    }
}
