package com.hangover.ashqures.hangover.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.hangover.ashqures.hangover.auth.SessionManager;
import com.hangover.ashqures.hangover.util.ValidatorUtil;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginAndSignUpActivity extends BaseActivity implements LoaderCallbacks<Cursor> {

    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, LoginAndSignUpActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }


    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    private SessionManager session;

    // UI references.
    /*private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;

    private View mLoginFormView;*/
    private View mProgressView;
    private TextView errorMessageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionManager(getApplicationContext());
        setUpSignIn();

    }

    private void populateAutoComplete(View view) {
        if (!mayRequestContacts(view)) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts(View view) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(view, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //populateAutoComplete();
            }
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin(View mLoginFormView, AutoCompleteTextView mEmailView, EditText mPasswordView) {
        if (mAuthTask != null) {
            return;
        }
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);
        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        boolean cancel = false;
        View focusView = null;
        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !ValidatorUtil.isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!ValidatorUtil.isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true, mLoginFormView);
            mAuthTask = new UserLoginTask(mLoginFormView, email, password);
            mAuthTask.execute((Void) null);
        }
    }

   /* private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }*/

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show, final View view) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            view.setVisibility(show ? View.GONE : View.VISIBLE);
            view.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    view.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            view.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        //addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection, AutoCompleteTextView mEmailView) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginAndSignUpActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    private void goToDashBoard(){
        Intent nextScreen = new Intent(getApplicationContext(), DashboardActivity.class);

        //Sending data to another Activity
            /*nextScreen.putExtra("name", inputName.getText().toString());
            nextScreen.putExtra("email", inputEmail.getText().toString());*/

        //Log.e("n", inputName.getText()+"."+ inputEmail.getText());

        startActivity(nextScreen);
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;
        private final View mView;

        UserLoginTask(View view, String email, String password) {
            mEmail = email;
            mPassword = password;
            mView = view;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            showProgress(false, mView);
            if (success) {
                //session.createLoginSession("Android HIVE", mAuthTask.mEmail,null);
                goToDashBoard();
                finish();
            } else {
                errorMessageView.setVisibility(View.VISIBLE);
                mAuthTask = null;
               /* mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();*/
            }

        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false, mView);
        }
    }


    private void setUpSignIn() {
        setContentView(R.layout.activity_login);
        mProgressView = findViewById(R.id.login_progress);
        errorMessageView = (TextView) findViewById(R.id.errorMessage);
        final AutoCompleteTextView mEmailView= (AutoCompleteTextView) findViewById(R.id.email);
        final EditText mPasswordView = (EditText) findViewById(R.id.password);
        final View mLoginFormView = findViewById(R.id.login_form);
        populateAutoComplete(mLoginFormView);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin(mLoginFormView, mEmailView, mPasswordView);
                    return true;
                }
                return false;
            }
        });

        Button mSignInButton = (Button) findViewById(R.id.sign_in_button);
        mSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin(mLoginFormView, mEmailView, mPasswordView);
            }
        });
        Button mSignUpButton = (Button) findViewById(R.id.sign_up_button);
        mSignUpButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpSignUp();
            }
        });
    }

    private void setUpSignUp(){
        setContentView(R.layout.activity_signup);
        mProgressView = findViewById(R.id.login_progress);
        errorMessageView = (TextView) findViewById(R.id.errorMessage);
        final View mSignUpFormView = findViewById(R.id.signup_form);
        Button mSignUnButton = (Button) findViewById(R.id.sign_up_button);
        mSignUnButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                doSignUp(mSignUpFormView);
            }
        });
        Button mSignIpButton = (Button) findViewById(R.id.sign_in_button);
        mSignIpButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpSignIn();
            }
        });
    }


    private void doSignUp(View view){

        EditText mFullNameView = (EditText) findViewById(R.id.fullName);
        AutoCompleteTextView mEmailView= (AutoCompleteTextView) findViewById(R.id.email);
        EditText mPasswordView = (EditText) findViewById(R.id.password);
        EditText mConfirmPasswordView = (EditText) findViewById(R.id.confirm_password);
        CheckBox mAgeConfirmationView = (CheckBox)findViewById(R.id.ageConfirmation);
        boolean isValid = validateSignUpForm(mFullNameView, mEmailView, mPasswordView, mConfirmPasswordView, mAgeConfirmationView);
        if(isValid){
            showProgress(true,view);
            //session.createLoginSession(mFullNameView.getText().toString(), mEmailView.getText().toString(),null);
            goToDashBoard();
        }else{
            errorMessageView.setVisibility(View.VISIBLE);
        }
    }


    private boolean validateSignUpForm(EditText fullNameView, AutoCompleteTextView emailView,
                                       EditText passwordView, EditText confirmPasswordView,
                                       CheckBox ageConfirmationView){
        boolean isValid = true;
        View focusView = null;
        String fullName = fullNameView.getText().toString();
        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();
        String confirmPassword = confirmPasswordView.getText().toString();
        boolean ageCofirmation = ageConfirmationView.isChecked();
        if(TextUtils.isEmpty(fullName)){
            fullNameView.setError(getString(R.string.error_field_required));
            focusView = fullNameView;
            isValid = false;
        }
        if(TextUtils.isEmpty(email)){
            emailView.setError(getString(R.string.error_field_required));
            focusView = emailView;
            isValid = false;
        }else if(!ValidatorUtil.isEmailValid(email)){
            emailView.setError(getString(R.string.error_invalid_email));
            focusView = emailView;
            isValid = false;
        }
        if(TextUtils.isEmpty(password) || !ValidatorUtil.isPasswordValid(password) ){
            passwordView.setError(getString(R.string.error_invalid_password));
            focusView = passwordView;
            isValid = false;
        }
        if(!password.equals(confirmPassword)){
            confirmPasswordView.setError(getString(R.string.error_field_confirm_password_mismatch));
            focusView = confirmPasswordView;
            isValid = false;
        }
        if(!ageCofirmation){
            ageConfirmationView.setError(getString(R.string.error_field_confirm_you_are_18_above));
            focusView = ageConfirmationView;
            isValid = false;
        }
        /*if(!isValid){
            showProgress(false);
            focusView.requestFocus();
        }*/
        return isValid;
    }
}

