package com.hangover.ashqures.hangover.dialog;

import com.hangover.ashqures.hangover.task.AsyncTaskHandler;
import com.hangover.ashqures.hangover.task.CommonRXTask;

/**
 * Created by ashqures on 9/4/16.
 */
public class DialogListenerImpl implements CustomDialog.MblVrfctnDialogListener {


    CommonRXTask rxTask;

    @Override
    public boolean resendOTP() {
        return false;
    }

    @Override
    public boolean verifyMobile(String otp) {
        return false;
    }

    private void initiateResendOTP(){
        //this.rxTask = AsyncTaskHandler.getSendOTPTask()
    }


}
