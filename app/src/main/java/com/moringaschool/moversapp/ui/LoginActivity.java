package com.moringaschool.moversapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.moringaschool.moversapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = LoginActivity.class.getSimpleName();

    @BindView(R.id.email) TextInputLayout emailTextInputLayout;
    @BindView(R.id.password) TextInputLayout passwordTextInputLayout;
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
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    @Override
    public void onClick(View view) {
        if(view == mLoginButton) {
            showProgressBar();
            loginUser();
        }
        if (view == mCreateAccountClickTextView) {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
            finish();

        }

    }

    private void loginUser() {
        mEmail = emailTextInputLayout.getEditText().getText().toString().trim();
        mPassword = passwordTextInputLayout.getEditText().getText().toString().trim();

        if (confirmEmail() == false) {
            return;
        }
        if (confirmPassword() == false) {
            return;
        }

        mAuth.signInWithEmailAndPassword(mEmail, mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                hideProgressBar();
                if (task.isSuccessful()) {
                    Log.e(TAG, "Authentication Successful");
                } else {
                    Toast.makeText(LoginActivity.this, "Login Failed.", Toast.LENGTH_LONG).show();
                    Log.e(TAG, "Error: ", task.getException());
                }
            }
        });
    }

    private boolean confirmPassword() {
        if (mPassword.equals("")) {
            passwordTextInputLayout.getEditText().setError("Password cannot be empty.");
            return false;
        }
        return true;
    }

    private boolean confirmEmail() {
        if (mEmail.equals("") || !Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()) {
            emailTextInputLayout.getEditText().setError("Email is invalid.");
            return false;
        }
        return true;
    }

    private void showProgressBar() {
    }

    private void hideProgressBar() {
    }
}
