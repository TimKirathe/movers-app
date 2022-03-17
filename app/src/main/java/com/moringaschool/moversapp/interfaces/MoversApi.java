package com.moringaschool.moversapp.interfaces;

import com.moringaschool.moversapp.models.Booking;
import com.moringaschool.moversapp.models.Calculate;
import com.moringaschool.moversapp.models.Invoice;
import com.moringaschool.moversapp.models.LoginUser;
import com.moringaschool.moversapp.models.LoginUserResponse;
import com.moringaschool.moversapp.models.PostBookingResponse;
import com.moringaschool.moversapp.models.PostInvoiceResponse;
import com.moringaschool.moversapp.models.PostResponse;
import com.moringaschool.moversapp.models.PostUserResponse;
import com.moringaschool.moversapp.models.ResponseBookingList;
import com.moringaschool.moversapp.models.ResponseDistance;
import com.moringaschool.moversapp.models.ResponseInvoice;
import com.moringaschool.moversapp.models.ResponseList;
import com.moringaschool.moversapp.models.ResponseObject;
import com.moringaschool.moversapp.models.ResponseUser;
import com.moringaschool.moversapp.models.ServiceResponse;
import com.moringaschool.moversapp.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoversApi {

    // POST METHODS

    @POST("users/new")
    Call<PostUserResponse> signUpUser(@Body User user);

    @POST("users/login")
    Call<LoginUserResponse> loginUser(@Body LoginUser loginUser);

    @POST("calculate")
    Call<ResponseDistance> calculateDistance(@Body Calculate calculate);

    @POST("invoice/new")
    Call<PostInvoiceResponse> postInvoice(@Body Invoice invoice);

    @POST("bookings/new")
    Call<PostBookingResponse> postBooking(@Body Booking booking);

    @POST("users/email")
    Call<ResponseUser> getUserByEmail(@Body String email);

    // GET METHODS

    @GET("services")
    Call<ServiceResponse> getServices();

    @GET("invoice/{id}")
    Call<ResponseInvoice> getInvoiceById(@Path("id") String id);

    @GET("bookings/user/{id}")
    Call<ResponseBookingList> getAllBookingsForUser(@Path("id") String id);

    // Mandatory Parameter Request
//    @GET("users/email/{id}")
//    Call<ResponseObject> getUserByEmail(@Path("id") String id);
    
}
