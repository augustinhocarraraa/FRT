package com.frt.mobile.shared.utils;

import retrofit2.Retrofit;

public class RetrofitClient {

    private static final String BASE_URL = "https://frt-nvs9.onrender.com/";

    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
