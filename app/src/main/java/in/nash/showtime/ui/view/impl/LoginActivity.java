package in.nash.showtime.ui.view.impl;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.ArrayList;
import java.util.regex.Pattern;

import in.nash.showtime.R;
import in.nash.showtime.ShowtimeApplication;
import in.nash.showtime.ui.view.ILoginView;
import in.nash.showtime.utils.AppUtils;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

/**
 * Created by Avinash Hindupur on 06/07/15.
 */
public class LoginActivity extends AppCompatActivity implements ILoginView, TextView.OnEditorActionListener,
        View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private Button mSubmitButton;
    private AutoCompleteTextView mEmail;
    private TextInputLayout mEmailInputLayout;
    private EditText mUserName;
    private EditText mPassword;
    private RadioGroup mRadioGroup;
    private ProgressBar mProgressBar;
    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLinearLayout = (LinearLayout) findViewById(R.id.linear_layout);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mUserName = (EditText) findViewById(R.id.input_username);
        mUserName.setOnEditorActionListener(this);
        mPassword = (EditText) findViewById(R.id.input_password);
        mPassword.setOnEditorActionListener(this);

        mSubmitButton = (Button) findViewById(R.id.button_submit);
        mSubmitButton.setOnClickListener(this);

        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
        mRadioGroup.setOnCheckedChangeListener(this);

        mEmailInputLayout = (TextInputLayout) findViewById(R.id.input_layout_email);
        mEmail = (AutoCompleteTextView) findViewById(R.id.input_email);

        fetchEmails();

        RadioButton loginRadio = (RadioButton) findViewById(R.id.radio_login);

        loginRadio.setChecked(true);

    }

    private void fetchEmails() {
        Observable.defer(new Func0<Observable<ArrayList<String>>>() {
            @Override
            public Observable<ArrayList<String>> call() {
                return Observable.just(getEmails());
            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArrayList<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ArrayList<String> emailsList) {

                        populateSuggestions(emailsList);
                    }
                });
    }

    private void populateSuggestions(ArrayList<String> emailList) {
        ArrayAdapter<String> emailsAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, emailList);
        mEmail.setAdapter(emailsAdapter);
    }

    public ArrayList<String> getEmails() {
        Pattern emailPattern = Patterns.EMAIL_ADDRESS;
        Account[] accounts = AccountManager.get(ShowtimeApplication.getAppContext()).getAccounts();
        ArrayList<String> emailList = new ArrayList<>();
        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                String possibleEmail = account.name;
                if (!emailList.contains(possibleEmail)) {
                    emailList.add(possibleEmail);
                }

            }
        }
        return emailList;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radio_login:
                mSubmitButton.setText(R.string.login);
                mEmailInputLayout.setVisibility(View.INVISIBLE);
                break;
            case R.id.radio_register:
                mSubmitButton.setText(R.string.register);
                mEmailInputLayout.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (v == mPassword) {
            switch (actionId) {
                case EditorInfo.IME_ACTION_DONE:
                    submit();
                    return true;
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_submit) {
            submit();
        }

    }

    private void submit() {
        mPassword.setError(null);

        final String username = mUserName.getText().toString().trim();

        if (username.isEmpty()) {
            mUserName.setError(getString(R.string.error_username_empty));
            return;
        }
        mUserName.setError(null);

        final String password = mPassword.getText().toString().trim();

        if (password.isEmpty()) {
            mPassword.setError(getString(R.string.error_password_empty));
            return;
        }
        mPassword.setError(null);

        switch (mRadioGroup.getCheckedRadioButtonId()) {
            case R.id.radio_login:

                showProgressBar();
                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            hideProgressBar();
                            Intent intent = new Intent(LoginActivity.this,
                                    MainActivity.class);
                            startActivity(intent);
                        } else {
                            hideProgressBar();
                            mPassword.setError(getString(R.string.error_username_password_incorrect));
                            Log.e("Error", e.getMessage());
                        }
                    }
                });

                break;
            case R.id.radio_register:
                final String email = mEmail.getText().toString().trim();
                if (email.isEmpty()) {
                    mEmail.setError(getString(R.string.error_email_empty));
                    return;
                }
                mEmail.setError(null);

                ParseUser user = new ParseUser();
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);

                showProgressBar();
                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {

                            hideProgressBar();
                            Intent intent = new Intent(LoginActivity.this,
                                    MainActivity.class);
                            startActivity(intent);

                        } else {
                            hideProgressBar();
                            Log.e(getString(R.string.error), e.getMessage());
                        }
                    }
                });

                break;
        }
    }

    @Override
    public void showProgressBar() {
        mLinearLayout.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mLinearLayout.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }

    public static void navigateTo(Activity fromActivity) {
        AppUtils.navigateToActivitySimple(fromActivity, LoginActivity.class);
    }

}
