package com.clogic.veslo.Model.Server;

import com.google.gson.annotations.SerializedName;

import retrofit.http.Part;

/**
 * Created by clogic on 2016. 1. 9..
 */
public class Info {

    @SerializedName("requestCode")
    public String requestCode;

    @SerializedName("requestMessage")
    public String requestMessage;

    @SerializedName("user")
    public User user;
}
