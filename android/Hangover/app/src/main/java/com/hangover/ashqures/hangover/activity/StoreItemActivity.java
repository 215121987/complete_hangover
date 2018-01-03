package com.hangover.ashqures.hangover.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.hangover.ashqures.hangover.activity.R;
import com.hangover.ashqures.hangover.component.DaggerStoreComponent;
import com.hangover.ashqures.hangover.component.HasComponent;
import com.hangover.ashqures.hangover.component.StoreComponent;
import com.hangover.ashqures.hangover.dto.ItemDTO;
import com.hangover.ashqures.hangover.fragments.StoreItemFragment;
import com.hangover.ashqures.hangover.modules.StoreModule;

public class StoreItemActivity extends BaseActivity implements HasComponent<StoreComponent> {

    public static final String INTENT_EXTRA_PARAM_ITEM_ID = "org.hangover.INTENT_PARAM_ITEM";
    public static final String INSTANCE_STATE_PARAM_ITEM_ID = "org.hangover.STATE_PARAM_ITEM";

    private Long itemId;

    private StoreComponent component;

    public static Intent getCallingIntent(Context context, Long itemId) {
        Intent callingIntent = new Intent(context, StoreItemActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_ITEM_ID, itemId);
        return callingIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_store_item_layout);
        setupToolbar(true);
        this.initializeActivity(savedInstanceState);
        this.initializeInjector();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putLong(INSTANCE_STATE_PARAM_ITEM_ID, this.itemId);
        }
        super.onSaveInstanceState(outState);
    }

    private void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            this.itemId = getIntent().getLongExtra(INTENT_EXTRA_PARAM_ITEM_ID, -1);
            addFragment(R.id.fragmentItemContainer, new StoreItemFragment());
        } else {
            this.itemId = savedInstanceState.getLong(INSTANCE_STATE_PARAM_ITEM_ID);
        }
    }



    private void initializeInjector() {
        this.component = DaggerStoreComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .storeModule(new StoreModule(this.itemId))
                .build();
    }

    @Override
    public StoreComponent getComponent() {
        return this.component;
    }

}
