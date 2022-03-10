package com.moringaschool.moversapp.interfaces;

import com.moringaschool.moversapp.models.LoginUser;
import com.moringaschool.moversapp.models.LoginUserResponse;
import com.moringaschool.moversapp.models.PostUserResponse;
import com.moringaschool.moversapp.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MoversApi {

    @POST("users/new")
    Call<PostUserResponse> signUpUser(@Body User user);

    @POST("users/login")
    Call<LoginUserResponse> loginUser(@Body LoginUser loginUser);
}
