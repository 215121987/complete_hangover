package com.hangover.ashqures.hangover.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.TextView;

import com.hangover.ashqures.hangover.activity.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ashqures on 9/3/16.
 */
public class MobileVerificationDialog extends DialogFragment {

    public static interface DialogListener {

        boolean resendOTP();

        boolean verifyMobile(String otp);
    }

    private DialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        this.listener = (DialogListener) getActivity();
        builder.setView(inflater.inflate(R.layout.mobile_verification_dialog, null));
        final EditText otp = (EditText) getActivity().findViewById(R.id.otp);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.button_verify, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                MobileVerificationDialog.this.listener.verifyMobile(otp.getText().toString());
            }
        }).setNegativeButton(R.string.button_resend, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MobileVerificationDialog.this.listener.resendOTP();
                    }
                });
        return builder.create();
    }

}
