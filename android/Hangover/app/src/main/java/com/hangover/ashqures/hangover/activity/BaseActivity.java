package com.hangover.ashqures.hangover.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.hangover.ashqures.hangover.AndroidApplication;
import com.hangover.ashqures.hangover.auth.SessionManager;
import com.hangover.ashqures.hangover.component.ApplicationComponent;
import com.hangover.ashqures.hangover.dialog.CustomDialog;
import com.hangover.ashqures.hangover.modules.ActivityModule;
import com.hangover.ashqures.hangover.navigation.Navigator;

import javax.inject.Inject;

/**
 * Created by ashqures on 8/7/16.
 */
public class BaseActivity extends AppCompatActivity{

    @Inject
    Navigator navigator;

    @Inject
    SessionManager sessionManager;

    protected Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);

        /*if(sessionManager.isLoggedIn()){
            if(!sessionManager.checkPermission(SessionManager.KEY_MOBILE_VERIFIED)){
                CustomDialog.MblVrfctnDialogListener listener = new CustomDialog.MblVrfctnDialogListener() {
                    @Override
                    public boolean resendOTP() {
                        return false;
                    }

                    @Override
                    public boolean verifyMobile(String otp) {
                        dialog.cancel();
                        return false;
                    }
                };
                this.dialog = CustomDialog.mobileVerificationDialog(this, listener);
                this.dialog.show();
            }
        }*/
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        showDataOnDrawer();
    }



    protected void setupToolbar(boolean onBackEnable) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if(null!= ab){
            if(onBackEnable){
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                if(null!=toolbar){
                    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onBackPressed();
                        }
                    });
                }
            }else{
                ab.setHomeAsUpIndicator(R.mipmap.ic_menu);
                ab.setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.store_action, menu);
        if(sessionManager.isLoggedIn()){
            handleMenuItem(menu, R.id.action_sign_in, false);
            handleMenuItem(menu, R.id.action_order, true);
            handleMenuItem(menu, R.id.action_account, true);
        }else{
            handleMenuItem(menu, R.id.action_sign_in, false);
            handleMenuItem(menu, R.id.action_order, false);
            handleMenuItem(menu, R.id.action_account, false);
            handleMenuItem(menu, R.id.action_sign_out, true);
        }
        return true;
    }

    private void handleMenuItem(Menu menu, int itemId, boolean visible){
        MenuItem menuItem = menu.findItem(itemId);
        menuItem.setVisible(visible);
    }

    private void showDataOnDrawer() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (null != navigationView) {
            if(sessionManager.isLoggedIn()){
                TextView loggedInUserName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.logged_in_user_name);
                if(null!= loggedInUserName)
                    loggedInUserName.setText(sessionManager.getUserDetails().get(SessionManager.KEY_NAME));
                TextView loggedInUserMobile = (TextView) navigationView.getHeaderView(0).findViewById(R.id.logged_in_user_mobile);
                if(null!= loggedInUserMobile)
                    loggedInUserMobile.setText(sessionManager.getUserDetails().get(SessionManager.KEY_PHONE_NUMBER));
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        boolean status = false;
        switch (id){
            case R.id.action_shopping_cart :
                this.navigator.navigateToCart(this);
                break;
            case R.id.action_settings : status = true;break;
            case R.id.action_sign_in : navigator.navigateToLogin(getApplicationContext());break;
            case R.id.action_sign_out : sessionManager.logoutUser();break;
            default:status=true;
        }
        return status?status:super.onOptionsItemSelected(item);
    }

    protected void initFloatingAction(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if(null != fab)
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
    }

    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    protected ApplicationComponent getApplicationComponent() {
        System.out.println("Ashif:- "+getApplication().getClass().getName());
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
