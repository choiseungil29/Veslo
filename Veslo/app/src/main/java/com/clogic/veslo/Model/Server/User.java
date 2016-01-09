package com.clogic.veslo.Model.Server;

import com.google.gson.annotations.SerializedName;

/**
 * Created by clogic on 2016. 1. 9..
 */
public class User {

    @SerializedName("username")
    public String username;

    @SerializedName("id")
    public String id;

    public String profilePath;
}
