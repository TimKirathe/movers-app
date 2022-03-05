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
import com.google.firebase.auth.UserProfileChangeRequest;
import com.moringaschool.moversapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = SignupActivity.class.getSimpleName();

    @BindView(R.id.name) TextInputLayout nameTextInputLayout;
    @BindView(R.id.email) TextInputLayout emailTextInputLayout;
    @BindView(R.id.password) TextInputLayout passwordTextInputLayout;
    @BindView(R.id.confirmpassword) TextInputLayout confirmPasswordTextInputLayout;
    @BindView(R.id.signup) Button signUpButton;
    @BindView(R.id.loginClick) TextView loginClickTextView;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private String mName;
    private String mEmail;
    private String mPassword;
    private String mConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        signUpButton.setOnClickListener(this);
        loginClickTextView.setOnClickListener(this);

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
                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    @Override
    public void onClick(View view) {
        if(view == loginClickTextView){
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        if (view == signUpButton){
            showProgressBar();
            createNewUser();
        }

    }

    private void createNewUser() {
        mName = nameTextInputLayout.getEditText().getText().toString().trim();
        mEmail = emailTextInputLayout.getEditText().getText().toString().trim();
        mPassword = passwordTextInputLayout.getEditText().getText().toString().trim();
        mConfirmPassword = confirmPasswordTextInputLayout.getEditText().getText().toString().trim();

        if (confirmName() == false) {
            return;
        }
        if (confirmEmail() == false) {
            return;
        }
        if (confirmPassword() == false) {
            return;
        }
        if (passwordsMatch() == false) {
            return;
        }

        mAuth.createUserWithEmailAndPassword(mEmail, mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.e(TAG, "Authentication Successful");
                    updateFirebaseUserProfile(task.getResult().getUser());
                } else {
                    Toast.makeText(SignupActivity.this, "Signup Failed!", Toast.LENGTH_LONG).show();
                    Log.e(TAG, "Authentication Failed: ", task.getException());
                }
            }
        });

    }

    private void updateFirebaseUserProfile(FirebaseUser user) {

        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                                                    .setDisplayName(mName)
                                                    .build();

        user.updateProfile(request).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                hideProgressBar();
                if(task.isSuccessful()) {
                    Log.e(TAG, user.getDisplayName());
                } else {
                    Log.e(TAG, "Error: ", task.getException());
                }
            }
        });
    }

    private boolean passwordsMatch() {
        if (!mPassword.equals(mConfirmPassword)) {
            passwordTextInputLayout.getEditText().setError("Passwords must match!");
            confirmPasswordTextInputLayout.getEditText().setError("Passwords must match!");
            return false;
        }
        return true;
    }

    private boolean confirmPassword() {
        if (mPassword.length() < 6 || mConfirmPassword.length() < 6) {
            passwordTextInputLayout.getEditText().setError("Password must be more than 6 characters.");
            confirmPasswordTextInputLayout.getEditText().setError("Password must be more than 6 characters.");
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

    private boolean confirmName() {
       if (mName.equals("")) {
           nameTextInputLayout.getEditText().setError("Name cannot be empty.");
           return false;
       }
       return true;
    }

    private void showProgressBar() {

    }

    private void hideProgressBar() {
    }
}
