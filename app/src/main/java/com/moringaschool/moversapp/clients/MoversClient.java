package com.moringaschool.moversapp.clients;

import com.moringaschool.moversapp.Constants;
import com.moringaschool.moversapp.interfaces.MoversApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoversClient {

    private static Retrofit retrofit = null;

    public static MoversApi getClient() {
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl("http://movers-apii.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit.create(MoversApi.class);
    }
}
