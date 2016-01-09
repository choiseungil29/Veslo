package com.clogic.veslo.Util;

import com.clogic.veslo.Model.Feed;
import com.clogic.veslo.Model.Server.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clogic on 2015. 11. 18..
 */
public class Provider {
    List<Feed> feeds;
    //List<Gallery> galleryList;
    User user;

    public void addNewsfeed(Feed feed) {
        feeds.add(feed);
    }
    public List<Feed> getAllNewsfeed() {
        return feeds;
    }

    public User getUserProfile() {
        return user;
    }
    public void setUserProfile(User user) {
        this.user = user;
    }

    public static Provider getInstance() {
        if(instance == null) {
            instance = new Provider();
        }
        return instance;
    }

    private static Provider instance = null;
    private Provider() {
        feeds = new ArrayList<>();
    }
}
