package jwile14.com.github.ribbit;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class SignUpActivity extends ActionBarActivity {

    protected EditText mUsername, mPassword, mEmail;
    ProgressBar mProgressBar;
    protected Button mSignupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mProgressBar = (ProgressBar) findViewById(R.id.signupProgressBar);
        mProgressBar.setVisibility(View.INVISIBLE);

        mUsername = (EditText) findViewById(R.id.usernameField);
        mPassword = (EditText) findViewById(R.id.passwordField);

        mEmail = (EditText) findViewById(R.id.emailField);

        mSignupButton = (Button) findViewById(R.id.signupButton);
        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsername.getText().toString();
                String password = mPassword.getText().toString();
                String email = mEmail.getText().toString();

                username = username.trim();
                password = password.trim();
                email = email.trim();

                if(username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                    builder.setMessage(R.string.signup_error_message)
                            .setTitle(R.string.signup_error_title)
                            .setPositiveButton(android.R.string.ok, null);
                    builder.create().show();
                } else {
                    // Create the new user!
                    ParseUser newUser = new ParseUser();
                    newUser.setUsername(username);
                    newUser.setPassword(password);
                    newUser.setEmail(email);

                    mSignupButton.setText("");
                    mProgressBar.setVisibility(View.VISIBLE);
                    newUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            mProgressBar.setVisibility(View.INVISIBLE);
                            mSignupButton.setText(R.string.sign_up_button_label);
                            if(e == null) {
                                // Success!
                                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                                builder.setMessage(e.getMessage())
                                        .setTitle(R.string.signup_error_title)
                                        .setPositiveButton(android.R.string.ok, null);
                                builder.create().show();

                            }
                        }
                    });
                }
            }
        });
    }
}
