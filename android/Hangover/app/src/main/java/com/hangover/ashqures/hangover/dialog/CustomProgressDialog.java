package com.hangover.ashqures.hangover.dialog;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by ashqures on 8/4/16.
 */
public class CustomProgressDialog extends ProgressDialog{


    public CustomProgressDialog(Context context) {
        super(context);
    }

    public CustomProgressDialog(Context context, int theme) {
        super(context, theme);
    }
}
