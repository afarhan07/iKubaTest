package com.ikuba.ikubatest.Rest;

import com.ikuba.ikubatest.Model.MUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by AFARHAN-PC on 1/4/2018.
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login")
    Call<MUser> login(@Field("username") String username,
                      @Field("password") String password);

    @FormUrlEncoded
    @POST("qr")
    Call<MUser> qr(@Field("username") String username);

    @FormUrlEncoded
    @POST("register")
    Call<MUser> register(@Field("username") String username,
                         @Field("password") String password,
                         @Field("name") String name,
                         @Field("no_telp") String no_telp,
                         @Field("email") String email
                         /*@Field("user_token") String user_token*/);

    @FormUrlEncoded
    @POST("update")
    Call<MUser> update(@Field("username") String username,
                       @Field("password") String password,
                       @Field("name") String name,
                       @Field("no_telp") String no_telp,
                       @Field("email") String email);

    @FormUrlEncoded
    @POST("delete")
    Call<MUser> delete(@Field("username") String username);

    @FormUrlEncoded
    @POST("joined_event")
    Call<MUser> joined_event(@Field("username") String username);

    @FormUrlEncoded
    @POST("created_event")
    Call<MUser> created_event(@Field("username") String username);


}
