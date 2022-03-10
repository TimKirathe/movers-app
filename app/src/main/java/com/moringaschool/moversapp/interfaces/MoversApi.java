package com.moringaschool.moversapp.interfaces;

import com.moringaschool.moversapp.models.LoginUserResponse;
import com.moringaschool.moversapp.models.PostUserResponse;

import retrofit2.Call;
import retrofit2.http.POST;

public interface MoversApi {

    @POST("users/new")
    Call<PostUserResponse> signUpUser();

    @POST("users/login")
    Call<LoginUserResponse> loginUser();
}
