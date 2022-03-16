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
import com.moringaschool.moversapp.clients.MoversClient;
import com.moringaschool.moversapp.interfaces.MoversApi;
import com.moringaschool.moversapp.models.PostUserResponse;
import com.moringaschool.moversapp.models.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = SignupActivity.class.getSimpleName();

    @BindView(R.id.name) TextInputLayout nameTextInputLayout;
    @BindView(R.id.email) TextInputLayout emailTextInputLayout;
    @BindView(R.id.password) TextInputLayout passwordTextInputLayout;
    @BindView(R.id.confirmpassword) TextInputLayout confirmPasswordTextInputLayout;
    @BindView(R.id.signup) Button signUpButton;
    @BindView(R.id.loginClick) TextView loginClickTextView;

    private String mName;
    private String mEmail;
    private String mPassword;
    private String mConfirmPassword;
    private String mUserId;

    private MoversApi mMoversApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        mMoversApi = MoversClient.getClient();

        signUpButton.setOnClickListener(this);
        loginClickTextView.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
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
            saveSignedUpUser();
        }

    }

    private void createNewUser() {
        mName = nameTextInputLayout.getEditText().getText().toString().trim();
        mEmail = emailTextInputLayout.getEditText().getText().toString().trim();
        mPassword = passwordTextInputLayout.getEditText().getText().toString().trim();
        mConfirmPassword = confirmPasswordTextInputLayout.getEditText().getText().toString().trim();
        mUserId = "12sdkjfh45kadfjkhkj67";

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
    }

    private void saveSignedUpUser() {
        User user = new User(mUserId, mName, mEmail, mPassword);
        Log.e(TAG, user.getName());

        Call<PostUserResponse> call = mMoversApi.signUpUser(user);

        call.enqueue(new Callback<PostUserResponse>() {
            @Override
            public void onResponse(Call<PostUserResponse> call, Response<PostUserResponse> response) {
                Log.e(TAG, response.raw().toString());
                Log.e(TAG, "User successfully added to the database, userId: " + response.body().getUser().getUserId());
                Toast.makeText(SignupActivity.this, "Saved to database", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<PostUserResponse> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "Saving to database failed", Toast.LENGTH_LONG).show();
                Log.e(TAG, "Error: " + t.getMessage());
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
