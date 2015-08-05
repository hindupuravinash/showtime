package in.nash.cram.ui.view.impl;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import in.nash.cram.R;

/**
 * Created by Avinash Hindupur on 06/07/15.
 */
public class LoginActivity extends AppCompatActivity implements TextView.OnEditorActionListener,
        View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private Button mSubmitButton;
    private EditText mEmail;
    private TextInputLayout mEmailInputLayout;
    private EditText mUserName;
    private EditText mPassword;
    private RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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

                //TODO: login(username, password);
                break;
            case R.id.radio_register:
                final String email = mEmail.getText().toString().trim();
                if (email.isEmpty()) {
                    mEmail.setError("Email cannot be empty");
                    return;
                }
                mEmail.setError(null);

                //TODO: register(username, password, email);
                break;
        }
    }
}
