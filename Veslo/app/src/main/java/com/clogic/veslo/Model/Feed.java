package com.clogic.veslo.Model;

import android.support.annotation.DrawableRes;

/**
 * Created by clogic on 2015. 11. 18..
 */
public class Feed {
    private String id;
    private String updateTime;
    private String textDescribe;
    private String love;
    private @DrawableRes int desResId;

    public Feed(String id, String updateTime, String textDescribe, String love, @DrawableRes int resId) {
        this.id = id;
        this.updateTime = updateTime;
        this.textDescribe = textDescribe;
        this.love = love;
        this.desResId = resId;
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

    public int getDescribeResId() { return desResId; }
}
