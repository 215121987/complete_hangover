package com.hangover.ashqures.hangover.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hangover.ashqures.hangover.auth.SessionManager;
import com.hangover.ashqures.hangover.component.DaggerStoreComponent;
import com.hangover.ashqures.hangover.component.HasComponent;
import com.hangover.ashqures.hangover.component.StoreComponent;


public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, HasComponent<StoreComponent> {



    public static Intent getCallingIntent(Context context) {
        return new Intent(context, HomeActivity.class);
    }


    private  StoreComponent component;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_layout);
        setupToolbar(false);
        initNavigationDrawer();
        //this.initializeInjector();
    }

    /*private void initializeInjector() {
        this.component = DaggerStoreComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }*/


    private void checkPermission(){

    }

    @Override
    public StoreComponent getComponent() {
        return this.component;
    }


    private void initNavigationDrawer() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if(null!=drawerLayout)
            drawerLayout.addDrawerListener(toggle);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (null != navigationView) {
            addItemsRunTime(navigationView);
            navigationView.setNavigationItemSelectedListener(this);
            /*if(sessionManager.isLoggedIn()){
                TextView loggedInUserName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.logged_in_user_name);
                if(null!= loggedInUserName)
                    loggedInUserName.setText(sessionManager.getUserDetails().get(SessionManager.KEY_NAME));
                TextView loggedInUserMobile = (TextView) navigationView.getHeaderView(0).findViewById(R.id.logged_in_user_mobile);
                if(null!= loggedInUserMobile)
                    loggedInUserMobile.setText(sessionManager.getUserDetails().get(SessionManager.KEY_PHONE_NUMBER));
            }*/
        }
    }


    private void addItemsRunTime(NavigationView navigationView) {
        //adding items run time
        final Menu menu = navigationView.getMenu();
        for (int i = 1; i <= 3; i++) {
            menu.add("Runtime item "+ i);
        }
        // adding a section and items into it
        final SubMenu subMenu = menu.addSubMenu("SubMenu Title");
        for (int i = 1; i <= 2; i++) {
            subMenu.add("SubMenu Item " + i);
        }
        // refreshing navigation drawer adapter
        for (int i = 0, count = navigationView.getChildCount(); i < count; i++) {
            final View child = navigationView.getChildAt(i);
            if (child != null && child instanceof ListView) {
                final ListView menuView = (ListView) child;
                final HeaderViewListAdapter adapter = (HeaderViewListAdapter) menuView.getAdapter();
                final BaseAdapter wrapped = (BaseAdapter) adapter.getWrappedAdapter();
                wrapped.notifyDataSetChanged();
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            Intent i = HomeActivity.getCallingIntent(getApplicationContext());
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        } else if (id == R.id.nav_store) {
            // Handle the store action
            Intent i = StoreActivity.getCallingIntent(getApplicationContext());
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }else if(id== R.id.logout){
            System.out.println("Session detail:- "+ sessionManager.toString());
            sessionManager.logoutUser();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if(null != drawer)
            drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (null != drawer && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
