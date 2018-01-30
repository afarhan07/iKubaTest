package com.ikuba.ikubatest.Rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ikuba.ikubatest.Constants.Constants.BASE_URL;

/**
 * Created by AFARHAN-PC on 1/4/2018.
 */

public class ApiClient {
    private static Retrofit mRetrofit = null;
    public static ApiInterface getClient(){
        if(mRetrofit == null){
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit.create(ApiInterface.class);

    }
}
