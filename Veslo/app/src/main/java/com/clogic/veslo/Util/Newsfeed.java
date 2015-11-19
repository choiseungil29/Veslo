package com.clogic.veslo.Util;

/**
 * Created by clogic on 2015. 11. 18..
 */
public class Newsfeed {
    private String id;
    private String updateTime;
    private String textDescribe;
    private String love;

    public Newsfeed(String id, String updateTime, String textDescribe, String love) {
        this.id = id;
        this.updateTime = updateTime;
        this.textDescribe = textDescribe;
        this.love = love;
    }

    public String getId() {
        return id;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public String getTextDescribe() {
        return textDescribe;
    }

    public String getLove() {
        return love;
    }
}
