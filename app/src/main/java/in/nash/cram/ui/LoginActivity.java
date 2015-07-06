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
        View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private Button mSubmitButton;
    private EditText mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText username = (EditText) findViewById(R.id.input_username);
        username.setOnEditorActionListener(this);
        EditText password = (EditText) findViewById(R.id.input_password);
        password.setOnEditorActionListener(this);

        mSubmitButton = (Button) findViewById(R.id.button_submit);
        mSubmitButton.setOnClickListener(this);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(this);

        mEmail = (EditText) findViewById(R.id.input_email);

        RadioButton loginRadio = (RadioButton) findViewById(R.id.radio_login);
        RadioButton registerRadio = (RadioButton) findViewById(R.id.radio_register);

        loginRadio.setChecked(true);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radio_login:
                mSubmitButton.setText("Login");
                mEmail.setVisibility(View.GONE);
                break;
            case R.id.radio_register:
                mSubmitButton.setText("Register");
                mEmail.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        return false;
    }

    @Override
    public void onClick(View v) {

    }
}
