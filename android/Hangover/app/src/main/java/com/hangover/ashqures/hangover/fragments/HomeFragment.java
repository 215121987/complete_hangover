package com.hangover.ashqures.hangover.fragments;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hangover.ashqures.hangover.activity.R;
import com.hangover.ashqures.hangover.component.StoreComponent;
import com.hangover.ashqures.hangover.dialog.CustomDialog;
import com.hangover.ashqures.hangover.dialog.MobileVerificationDialog;
import com.hangover.ashqures.hangover.presenter.HomePresenter;
import com.hangover.ashqures.hangover.view.HomeView;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by ashqures on 8/21/16.
 */
public class HomeFragment extends BaseFragment implements HomeView, CustomDialog.MblVrfctnDialogListener {



    public interface HomeListener {
    }

    private HomeListener listener;

    @Inject
    HomePresenter presenter;

    public HomeFragment(){
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HomeListener) {
            this.listener = (HomeListener) context;
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
        final View fragmentView = inflater.inflate(R.layout.fragment_auth_flipper, container, false);
        ButterKnife.bind(this, fragmentView);
        setUpView();
        return fragmentView;
    }


    private void setUpView(){
        //TODO Define home view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.presenter.setView(this);
        checkSelfPermission(Manifest.permission.READ_PHONE_STATE, MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
        if (savedInstanceState == null) {
            this.loadHome();
        }
    }



    @Override
    public boolean resendOTP() {
        return false;
    }

    @Override
    public boolean verifyMobile(String otp) {
        return false;
    }

    private  void loadHome(){
        this.presenter.initialize();
    }


}
