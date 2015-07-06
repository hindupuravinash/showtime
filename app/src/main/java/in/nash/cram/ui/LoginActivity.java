package in.nash.cram.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
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
        View.OnClickListener {

    private Button mSubmitButton;
    private EditText mEmail;
    private EditText mUsername;
    private EditText mPassword;
    private RadioGroup mRadioGroup;
    private RadioButton mLoginRadio;
    private RadioButton mRegisterRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsername = (EditText) findViewById(R.id.input_username);
        mUsername.setOnEditorActionListener(this);
        mPassword = (EditText) findViewById(R.id.input_password);
        mPassword.setOnEditorActionListener(this);

        mSubmitButton = (Button) findViewById(R.id.button_submit);
        mSubmitButton.setOnClickListener(this);

        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
        mRadioGroup.setOnCheckedChangeListener(this);

        mEmail = (AutoCompleteTextView) findViewById(R.id.input_email);

        mLoginRadio = (RadioButton) findViewById(R.id.radio_login);
        mRegisterRadio = (RadioButton) findViewById(R.id.radio_register);

        mLoginRadio.setChecked(true);

    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        return false;
    }

    @Override
    public void onClick(View v) {

    }
}
