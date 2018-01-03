package com.hangover.ashqures.hangover.fragments;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.hangover.ashqures.hangover.activity.R;
import com.hangover.ashqures.hangover.adapter.StoreAdapter;
import com.hangover.ashqures.hangover.component.StoreComponent;
import com.hangover.ashqures.hangover.dto.ItemDTO;
import com.hangover.ashqures.hangover.listener.EnableScrollListener;
import com.hangover.ashqures.hangover.presenter.StorePresenter;
import com.hangover.ashqures.hangover.task.BaseTask;
import com.hangover.ashqures.hangover.task.GetStoreDataTask;
import com.hangover.ashqures.hangover.view.StoreView;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ashqures on 8/7/16.
 */
public class StoreFragment extends BaseFragment implements StoreView {

    public interface StoreListener {
        void onItemClicked(final ItemDTO itemDTO);
    }

    private StoreListener listener;

    @Inject
    public StorePresenter presenter;

    public StoreAdapter storeAdapter;

    @Bind(R.id.item_grid_view)
    GridView gridView;

    public StoreFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof StoreListener) {
            this.listener = (StoreListener) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(StoreComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_store, container, false);
        ButterKnife.bind(this, fragmentView);
        setUpGridView();
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.presenter.setView(this);
        if (savedInstanceState == null) {
            this.loadItemList();
        }
    }

    private void loadItemList() {
        /*Map<String,String> paramMap = new HashMap<>();
        paramMap.put("category","1");
        this.mTask = new GetStoreDataTask(this);
        mTask.execute(paramMap);*/
        this.presenter.initialize();
    }


    private void initializeScrolling(){
        EnableScrollListener scrollListener=new EnableScrollListener(gridView,new EnableScrollListener.RefreshView() {
            @Override
            public void onRefresh(int pageNumber) {
                loadItemList();
            }
        });
        gridView.setOnScrollListener(scrollListener);
    }

    @Override
    public void renderStore(List<ItemDTO> itemDTOCollection) {
        if (itemDTOCollection != null) {
            this.storeAdapter.setItemCollection(itemDTOCollection);
            //this.storeAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void viewItem(ItemDTO itemDTO) {
        if (this.listener != null) {
            this.listener.onItemClicked(itemDTO);
        }
    }


    private void setUpGridView(){
        this.storeAdapter = new StoreAdapter(context(), R.layout.list_item);
        this.gridView.setAdapter(storeAdapter);
        this.storeAdapter.setOnItemClickListener(onItemClickListener);
        //this.gridView.setOnItemClickListener
        //initializeScrolling();
    }


    /*
    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(DashboardActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });



     */

    private StoreAdapter.OnItemClickListener onItemClickListener =
            new StoreAdapter.OnItemClickListener() {
                @Override
                public void onItemClicked(ItemDTO itemDTO) {
                    if (null != StoreFragment.this.presenter && null != itemDTO) {
                        StoreFragment.this.presenter.onItemClicked(itemDTO);
                    }
                    //StoreFragment.this.viewItem(itemDTO);
                }
            };
}
