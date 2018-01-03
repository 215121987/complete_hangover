package com.hangover.ashqures.hangover.listener;

import android.widget.AbsListView;
import android.widget.GridView;

/**
 * Created by ashqures on 8/3/16.
 */
public class EnableScrollListener implements AbsListView.OnScrollListener  {


    private GridView gridView;
    private boolean isLoading;
    private boolean hasMorePages;
    private int pageNumber=0;
    private RefreshView refreshView;
    private boolean isRefreshing;

    public EnableScrollListener(GridView gridView,RefreshView refreshView){
        this.gridView = gridView;
        this.isLoading = false;
        this.hasMorePages = true;
        this.refreshView=refreshView;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (gridView.getLastVisiblePosition() + 1 == totalItemCount && !isLoading) {
            isLoading = true;
            if (hasMorePages&&!isRefreshing) {
                isRefreshing=true;
                refreshView.onRefresh(pageNumber);
                notifyMorePages();
                if(pageNumber>5)
                    noMorePages();
            }
        } else {
            isLoading = false;
        }
    }


    public void noMorePages() {
        this.hasMorePages = false;
    }

    public void notifyMorePages(){
        isRefreshing=false;
        pageNumber=pageNumber+1;
    }

    public interface RefreshView {
        public void onRefresh(int pageNumber);
    }
}
