/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.hangover.ashqures.hangover.fragments;

import android.Manifest;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hangover.ashqures.hangover.activity.R;
import com.hangover.ashqures.hangover.activity.StoreActivity;
import com.hangover.ashqures.hangover.auth.SessionManager;
import com.hangover.ashqures.hangover.component.HasComponent;
import com.hangover.ashqures.hangover.dialog.CustomDialog;
import com.hangover.ashqures.hangover.dto.StatusDTO;
import com.hangover.ashqures.hangover.listener.AppLocationService;
import com.hangover.ashqures.hangover.presenter.DialogPresenter;
import com.hangover.ashqures.hangover.util.CommonUtil;
import com.hangover.ashqures.hangover.util.FlipAnimationUtil;
import com.hangover.ashqures.hangover.util.RestConstants;
import com.hangover.ashqures.hangover.util.ValidatorUtil;
import com.hangover.ashqures.hangover.view.DialogView;
import com.hangover.ashqures.hangover.view.LoadDataView;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Base {@link Fragment} class for every fragment in this application.
 */
public abstract class BaseFragment extends Fragment implements LoadDataView, DialogView {

    protected static final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 100;
    private static final int MY_PERMISSIONS_REQUEST_READ_LOCATION = 200;


    @Inject
    SessionManager sessionManager;

    protected Dialog dialog;

    @Inject
    DialogPresenter presenter;

    private AppLocationService appLocationService;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        checkPermissionAnsShowDialog();
        checkUserLocation();
        showSnackBar(view);
    }

    private void checkPermissionAnsShowDialog(){
        this.presenter.setView(this);
        if (sessionManager.isLoggedIn()) {
            if (!sessionManager.checkPermission(SessionManager.KEY_MOBILE_VERIFIED)) {
                CustomDialog.MblVrfctnDialogListener listener = new CustomDialog.MblVrfctnDialogListener() {
                    @Override
                    public boolean resendOTP() {
                        BaseFragment.this.resendOTP();
                        return false;
                    }

                    @Override
                    public boolean verifyMobile(String otp) {
                        BaseFragment.this.verifyMobile(otp);
                        return true;
                    }
                };
                this.dialog = CustomDialog.mobileVerificationDialog(getActivity(), listener);
                this.dialog.show();
            }
        }
    }

    public void positiveAction(StatusDTO status){
        onSuccessOfMobileVerification();
    }

    public void negativeAction(StatusDTO status){
        //TODO handle resend respone
        /*EditText otp = (EditText) this.dialog.findViewById(R.id.otp);
        otp.setError(context().getString(R.string.error_invalid_otp));*/
    }

    public void onFailure(StatusDTO statusDTO){
        EditText otp = (EditText) this.dialog.findViewById(R.id.otp);
        otp.setError(context().getString(R.string.error_invalid_otp));
    }

    private void resendOTP(){
        Map<String,String> formAttr = new HashMap<>();
        formAttr.put(RestConstants.DEVICE_ID, CommonUtil.getDeviceInfo(context()).get("deviceId"));
        formAttr.put(RestConstants.USERNAME,sessionManager.getUserDetails().get(SessionManager.KEY_PHONE_NUMBER));
        this.presenter.initiateNegativeAction(formAttr);
    }

    private void verifyMobile(String otp){
        Map<String,String> formAttr = new HashMap<>();
        formAttr.put(RestConstants.DEVICE_ID, CommonUtil.getDeviceInfo(context()).get("deviceId"));
        formAttr.put(RestConstants.OTP, otp);
        formAttr.put(RestConstants.USERNAME,sessionManager.getUserDetails().get(SessionManager.KEY_PHONE_NUMBER));
        this.presenter.initiatePositiveAction(formAttr);
    }

    public void onSuccessOfMobileVerification(){
        sessionManager.updatePermission(SessionManager.KEY_MOBILE_VERIFIED, true);
        this.dialog.cancel();
    }

    /**
     * Shows a {@link Toast} message.
     *
     * @param message An string representing a message to be shown.
     */
    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

    public boolean checkSelfPermission(String permission, int resultCode) {
        if (ContextCompat.checkSelfPermission(getActivity(), permission) != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (!ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permission)) {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{permission}, resultCode);
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_PHONE_STATE: {
                if (grantResults.length > 0) {
                    if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                        showToastMessage("Some Permission is Denied");
                    }
                }
            }
            break;
            case MY_PERMISSIONS_REQUEST_READ_LOCATION:{
                if (grantResults.length > 0) {
                    if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                        showToastMessage("Some Permission is Denied");
                    }else{
                        handleLocation();
                    }
                }
            }
            break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void checkUserLocation(){
        if (sessionManager.isLoggedIn() && sessionManager.checkPermission(SessionManager.KEY_MOBILE_VERIFIED)
                && null == sessionManager.getStringValue(SessionManager.KEY_ZIP_CODE)) {
            showLocationDialog("560075");
        }
    }

    public void handleLocation(){
        this.appLocationService = new AppLocationService(getActivity(),new GeoCoderHandler());
        if(this.appLocationService.checkPermission(LocationManager.NETWORK_PROVIDER)){
            this.appLocationService.execute(LocationManager.NETWORK_PROVIDER);
        }else if(this.appLocationService.checkPermission(LocationManager.GPS_PROVIDER)){
            this.appLocationService.execute(LocationManager.GPS_PROVIDER);
        }
    }


    private class GeoCoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    showLocationDialog(locationAddress);
                    break;
                default:
                    break;
            }
        }
    }
    public void showLocationDialog(String address){
        showToastMessage(address);
        CustomDialog.LocationCnfrmDialogListener listener = new CustomDialog.LocationCnfrmDialogListener() {
            @Override
            public void updateLocation(String location) {
                //showToastMessage(location);
                sessionManager.add(SessionManager.KEY_ZIP_CODE, location);
                BaseFragment.this.dialog.cancel();
                Intent intentToLaunch = StoreActivity.getCallingIntent(context());
                intentToLaunch.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context().startActivity(intentToLaunch);
                getActivity().finish();
            }
        };
        this.dialog = CustomDialog.locationConfirmationDialog(getActivity(), listener, address);
        this.dialog.show();
    }


    protected void slideUp(View view) {
        view.setAnimation(FlipAnimationUtil.slideUp());
        //view.setVisibility(View.GONE);
    }

    protected void slideDown(View view) {
        view.setAnimation(FlipAnimationUtil.slideDown());
        //view.setVisibility(View.VISIBLE);
    }

    protected void toggleTabView(ViewGroup titleView, ViewGroup contentView, boolean show) {
        if (show){
            contentView.setAnimation(FlipAnimationUtil.slideDown());
            titleView.getChildAt(titleView.getChildCount()-1).setBackgroundResource(R.drawable.btn_minus);
            contentView.setVisibility(View.VISIBLE);
        }else{
            contentView.setAnimation(FlipAnimationUtil.slideUp());
            titleView.getChildAt(titleView.getChildCount()-1).setBackgroundResource(R.drawable.btn_plus);
            contentView.setVisibility(View.GONE);
        }
    }



    private void showSnackBar(View view){
        Snackbar snackbar = Snackbar
                .make(view, "Delivery ZipCode is "+ sessionManager.getStringValue(SessionManager.KEY_ZIP_CODE), Snackbar.LENGTH_LONG)
                .setAction("Change", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showLocationDialog("560075");
                    }
                });
        snackbar.setActionTextColor(Color.RED);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.setDuration(Snackbar.LENGTH_INDEFINITE);
        snackbar.show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {
        showToastMessage(message);
    }


    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }
}
