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
import com.moringaschool.moversapp.clients.MoversClient;
import com.moringaschool.moversapp.interfaces.MoversApi;
import com.moringaschool.moversapp.models.LoginUser;
import com.moringaschool.moversapp.models.LoginUserResponse;
import com.moringaschool.moversapp.models.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = LoginActivity.class.getSimpleName();

    @BindView(R.id.email) TextInputLayout emailTextInputLayout;
    @BindView(R.id.password) TextInputLayout passwordTextInputLayout;
    @BindView(R.id.login) Button mLoginButton;
    @BindView(R.id.createaccountclick) TextView mCreateAccountClickTextView;

    private String mEmail;
    private String mPassword;

    private MoversApi mMoversApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mLoginButton.setOnClickListener(this);
        mCreateAccountClickTextView.setOnClickListener(this);

        mMoversApi = MoversClient.getClient();

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
        if(view == mLoginButton) {
            showProgressBar();
            loginUser();
            getLoggedInUser();
        }
        if (view == mCreateAccountClickTextView) {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
            finish();

        }

    }

    private void getLoggedInUser() {
        LoginUser loginUser = new LoginUser(mEmail, mPassword);

        Call<LoginUserResponse> call2 = mMoversApi.loginUser(loginUser);

        call2.enqueue(new Callback<LoginUserResponse>() {
            @Override
            public void onResponse(Call<LoginUserResponse> call, Response<LoginUserResponse> response) {
                Log.e(TAG, "Logged In User successfully returned");
                User user = response.body().getUser();
                Log.e(TAG, "User Returned Successfully: " + user.getName());
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<LoginUserResponse> call, Throwable t) {

            }
        });
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
