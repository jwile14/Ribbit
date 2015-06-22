package jwile14.com.github.ribbit.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import jwile14.com.github.ribbit.R;
import jwile14.com.github.ribbit.RibbitApplication;


public class LoginActivity extends ActionBarActivity {

    protected EditText mUsername, mPassword;
    protected Button mLoginButton;
    protected ProgressBar mProgressBar;
    protected TextView mSignUpTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        mProgressBar = (ProgressBar) findViewById(R.id.loginProgressBar);
        mProgressBar.setVisibility(View.INVISIBLE);

        mSignUpTextView = (TextView) findViewById(R.id.signUpText);
        mSignUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        mUsername = (EditText) findViewById(R.id.usernameField);
        mPassword = (EditText) findViewById(R.id.passwordField);
        mLoginButton = (Button) findViewById(R.id.loginButton);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsername.getText().toString();
                String password = mPassword.getText().toString();

                username = username.trim();
                password = password.trim();

                if(username.isEmpty() || password.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setMessage(R.string.login_error_message)
                            .setTitle(R.string.login_error_title)
                            .setPositiveButton(android.R.string.ok, null);
                    builder.create().show();
                } else {
                    // Log in
                    mLoginButton.setText("");
                    mProgressBar.setVisibility(View.VISIBLE);
                    ParseUser.logInInBackground(username, password, new LogInCallback() {
                        public void done(ParseUser user, ParseException e) {
                            mProgressBar.setVisibility(View.INVISIBLE);
                            mLoginButton.setText(R.string.login_button_label);
                            if (e == null) {
                                // Success!
                                RibbitApplication.updateParseInstallation(user);

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage(e.getMessage())
                                        .setTitle(R.string.login_error_title)
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
