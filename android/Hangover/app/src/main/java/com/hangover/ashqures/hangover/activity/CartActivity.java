package com.hangover.ashqures.hangover.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hangover.ashqures.hangover.component.DaggerStoreComponent;
import com.hangover.ashqures.hangover.component.HasComponent;
import com.hangover.ashqures.hangover.component.StoreComponent;
import com.hangover.ashqures.hangover.fragments.CartFragment;

/**
 * Created by ashqures on 8/27/16.
 */
public class CartActivity extends BaseActivity implements HasComponent<StoreComponent>, CartFragment.CartListener{

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, CartActivity.class);
    }

    private StoreComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_store_item_layout);
        setupToolbar(true);
        this.initializeInjector();
        if (savedInstanceState == null) {
            addFragment(R.id.fragmentItemContainer, new CartFragment());
        }
    }

    private void handleTooBar(){

    }

    private void initializeInjector() {
        this.component = DaggerStoreComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public StoreComponent getComponent() {
        return this.component;
    }


    @Override
    public void onItemClick(Long itemId) {
        this.navigator.navigateToItemDetails(this, itemId);
    }

    @Override
    public void checkout() {
        this.navigator.navigateToCheckout(this);
    }
}
