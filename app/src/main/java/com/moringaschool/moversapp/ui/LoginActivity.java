package com.moringaschool.moversapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.moringaschool.moversapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = LoginActivity.class.getSimpleName();
    @BindView(R.id.email) TextInputLayout email;
    @BindView(R.id.password) TextInputLayout password;
    @BindView(R.id.login) Button mLoginButton;
    @BindView(R.id.createaccountclick) TextView mCreateAccountClickTextView;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private String mEmail;
    private String mPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mLoginButton.setOnClickListener(this);
        mCreateAccountClickTextView.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        createAuthStateListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }

    private void createAuthStateListener() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.setFlags()
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    @Override
    public void onClick(View view) {
        if(view == mLoginButton) {

        }
        if (view == mCreateAccountClickTextView) {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
            finish();

        }

    }
}
