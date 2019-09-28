package com.ensa.shipy.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
 
 
public class ApiClient {
 
    public static final String BASE_URL = "http://00901c70.ngrok.io/";
    private static Retrofit retrofit = null;
 
 
    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}