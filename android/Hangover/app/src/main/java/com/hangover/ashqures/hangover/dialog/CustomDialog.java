package com.hangover.ashqures.hangover.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.hangover.ashqures.hangover.activity.R;
import com.hangover.ashqures.hangover.util.ValidatorUtil;

/**
 * Created by ashqures on 9/4/16.
 */
public class CustomDialog {


    public static interface MblVrfctnDialogListener {
        boolean resendOTP();
        boolean verifyMobile(String otp);
    }

    public static interface LocationCnfrmDialogListener {
        void updateLocation(String location);
    }

    public static interface AgeCnfrmDialogListener {
        void upload(String location);
    }

    public static Dialog mobileVerificationDialog(Context context, final MblVrfctnDialogListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View promptsView = inflater.inflate(R.layout.mobile_verification_dialog, null);
        builder.setView(promptsView);
        builder.setCancelable(false);
        final EditText otp = (EditText) promptsView.findViewById(R.id.otp);
        Button resendOTP = (Button)promptsView.findViewById(R.id.resend_otp);
        Button verify = (Button)promptsView.findViewById(R.id.verify_mobile);
        resendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.resendOTP();
            }
        });
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otpText = otp.getText().toString();
                if(TextUtils.isEmpty(otpText)){
                    otp.setError(v.getContext().getString(R.string.error_field_required));
                }else{
                    listener.verifyMobile(otp.getText().toString());
                }
            }
        });
        return builder.create();
    }

    public static Dialog locationConfirmationDialog(Context context, final LocationCnfrmDialogListener listener, String location){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View promptsView = inflater.inflate(R.layout.location_confirmation_dialog, null);
        builder.setView(promptsView);
        builder.setCancelable(false);
        final EditText zipCodeView = (EditText) promptsView.findViewById(R.id.otp);
        zipCodeView.setText(location);
        Button confirmLocation = (Button)promptsView.findViewById(R.id.confirm_location_btn);
        confirmLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String zipCode = zipCodeView.getText().toString();
                if(ValidatorUtil.isValidZipCode(zipCode)){
                    listener.updateLocation(zipCode);
                }else if(TextUtils.isEmpty(zipCode)){
                    zipCodeView.setError(v.getContext().getString(R.string.error_field_required));
                }else{
                    zipCodeView.setError(v.getContext().getString(R.string.error_invalide_zipcode));
                }
            }
        });
        return builder.create();
    }

    public static Dialog ageConfirmationDialog(Context context, final AgeCnfrmDialogListener listener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View promptsView = inflater.inflate(R.layout.age_confirmation_dialog, null);
        builder.setView(promptsView);
        builder.setCancelable(false);
        ImageView preview = (ImageView) promptsView.findViewById(R.id.doc_preview);
        Button chooseImageBtn = (Button)promptsView.findViewById(R.id.choose_image_btn);
        chooseImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Button captureImageBtn = (Button)promptsView.findViewById(R.id.capture_image_btn);
        captureImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Button uploadBtn = (Button)promptsView.findViewById(R.id.upload_image_btn);
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.upload("");
            }
        });
        return builder.create();
    }

}
