package com.ensa.shipy.retrofit;

import com.ensa.shipy.models.OrderModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
 
 
public interface ApiInterface {
    @GET("api/order")
    Call<List<OrderModel>> getNewOrders();

    @GET("api/order")
    Call<List<OrderModel>> getOrders(@Query("current_status") String status);
}