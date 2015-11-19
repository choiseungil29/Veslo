package com.clogic.veslo.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clogic on 2015. 11. 18..
 */
public class Provider {
    List<Newsfeed> newsfeeds;

    public void addNewsfeed(Newsfeed newsfeed) {
        newsfeeds.add(newsfeed);
    }

    public List<Newsfeed> getAllNewsfeed() {
        return newsfeeds;
    }

    public static Provider getInstance() {
        if(instance == null) {
            instance = new Provider();
        }
        return instance;
    }

    private static Provider instance = null;
    private Provider() {
        newsfeeds = new ArrayList<>();
    }
}
