package com.clogic.veslo.API;

import com.clogic.veslo.Model.Server.Info;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import retrofit.http.QueryMap;

/**
 * Created by clogic on 2016. 1. 9..
 */
public interface UserAPI {

    public static final String MAIN_TEST = "/signup";
    public static final String KAKAO_SIGNUP_WITH_LOGIN = "/signup_with_login/kakao";

    @GET(KAKAO_SIGNUP_WITH_LOGIN)
    public Call<Info> signupWithLogin(@Query("id") String id, @Query("username") String username);

    @GET(MAIN_TEST)
    public Call<Info> signup();
}
