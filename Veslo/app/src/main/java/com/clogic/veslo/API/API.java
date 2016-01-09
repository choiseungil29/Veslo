package com.clogic.veslo.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.parsers.FactoryConfigurationError;

import retrofit.Converter;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by clogic on 2015. 11. 14..
 */
public class API {

    public static final String BASE_URL = "http://veslo.herokuapp.com";

    private static Retrofit adapter;
    private static Retrofit.Builder builder;
    private static API instance = null;

    public API() {
        builder = new Retrofit.Builder();
        builder.baseUrl(BASE_URL);
        builder.addConverterFactory(GsonConverterFactory.create());
        adapter = builder.build();
    }

    private static API getInstance() {
        if(instance == null) {
            instance = new API();
        }
        return instance;
    }

    private static void settingEndpoint(String PORT) {
        getInstance();
        builder.baseUrl(BASE_URL);
        adapter = builder.build();
    }

    public static UserAPI getUserAPI() {
        return getInstance().adapter.create(UserAPI.class);
    }
    public static PostAPI getPostAPI() { return getInstance().adapter.create(PostAPI.class); }
}
