package in.nash.cram.ui.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
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

import in.nash.cram.R;
import in.nash.cram.ui.view.ILoginView;

/**
 * Created by Avinash Hindupur on 06/07/15.
 */
public class LoginActivity extends AppCompatActivity implements ILoginView, TextView.OnEditorActionListener,
        View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private Button mSubmitButton;
    private EditText mEmail;
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

        mLinearLayout = (LinearLayout)findViewById(R.id.linear_layout);
        mProgressBar = (ProgressBar)findViewById(R.id.progress_bar);
        mUserName = (EditText) findViewById(R.id.input_username);
        mUserName.setOnEditorActionListener(this);
        mPassword = (EditText) findViewById(R.id.input_password);
        mPassword.setOnEditorActionListener(this);

        mSubmitButton = (Button) findViewById(R.id.button_submit);
        mSubmitButton.setOnClickListener(this);

        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
        mRadioGroup.setOnCheckedChangeListener(this);

        mEmailInputLayout = (TextInputLayout) findViewById(R.id.input_layout_email);
        mEmail = (EditText) findViewById(R.id.input_email);

        RadioButton loginRadio = (RadioButton) findViewById(R.id.radio_login);

        loginRadio.setChecked(true);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radio_login:
                mSubmitButton.setText("Login");
                mEmailInputLayout.setVisibility(View.INVISIBLE);
                break;
            case R.id.radio_register:
                mSubmitButton.setText("Register");
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
            mUserName.setError("Username cannot be empty");
            return;
        }
        mUserName.setError(null);

        final String password = mPassword.getText().toString().trim();

        if (password.isEmpty()) {
            mPassword.setError("Password cannot be empty");
            return;
        }
        mPassword.setError(null);

        switch (mRadioGroup.getCheckedRadioButtonId()) {
            case R.id.radio_login:

                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            Intent intent = new Intent(LoginActivity.this,
                                    MainActivity.class);
                            startActivity(intent);
                        } else {
                            mPassword.setError("Username or Password is not correct");
                            Log.e("Error", e.getMessage());
                        }
                    }
                });

                break;
            case R.id.radio_register:
                final String email = mEmail.getText().toString().trim();
                if (email.isEmpty()) {
                    mEmail.setError("Email cannot be empty");
                    return;
                }
                mEmail.setError(null);

                ParseUser user = new ParseUser();
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);

                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {

                            Intent intent = new Intent(LoginActivity.this,
                                    MainActivity.class);
                            startActivity(intent);

                        } else {
                            Log.e("Error", e.getMessage());
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

}
