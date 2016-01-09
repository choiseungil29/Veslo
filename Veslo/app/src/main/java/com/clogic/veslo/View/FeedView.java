package com.clogic.veslo.View;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.clogic.veslo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by clogic on 2015. 11. 18..
 */
public class FeedView extends LinearLayout {

    @Bind(R.id.iv_profile) ImageView iv_profile;
    @Bind(R.id.iv_describe) ImageView iv_describe;
    @Bind(R.id.tv_love) TextView tv_love;
    @Bind(R.id.tv_describe) TextView tv_desribe;
    @Bind(R.id.tv_id) TextView tv_id;
    @Bind(R.id.tv_time) TextView tv_time;

    public FeedView(Context context) {
        this(context, null);
    }

    public FeedView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FeedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        li.inflate(R.layout.view_feed, this, true);

        ButterKnife.bind(this);
    }

    public void setId(String id) {
        this.tv_id.setText(id);
    }

    public void setTime(String time) {
        this.tv_time.setText(time);
    }

    public void setTextDescribe(String describe) {
        this.tv_desribe.setText(describe);
    }

    public void setLove(String love) throws Exception {
        if(!love.matches("^[0-9]^")) {
            throw new Exception();
        }
        this.tv_love.setText(String.format("%,d", love));
    }

    public void setProfileRes(@DrawableRes int resId) {
        Glide.with(getContext())
                .load(resId)
                .bitmapTransform(new CropCircleTransformation(getContext()))
                .into(iv_profile);
    }

    public void setDescribeRes(@DrawableRes int resId) {
        Glide.with(getContext())
                .load(resId)
                .into(iv_describe);
    }
}
