package com.hangover.ashqures.hangover.util;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;

/**
 * Created by ashqures on 8/28/16.
 */
public class Handler {


    public static void handleCartListView(ListView listView) {
        Adapter adapter = listView.getAdapter();
        if (adapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int size = 0; size < adapter.getCount(); size++) {
            View listItem = adapter.getView(size, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
       // params.height = totalHeight-listView.getMeasuredHeight();
        params.height = CommonUtil.pxToDp(totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1)));
        listView.setLayoutParams(params);
       // myListView.setLayoutParams(params);
        // print height of adapter on log
        Log.i("height of listItem:", String.valueOf(totalHeight));
    }
}
