package com.clogic.veslo.View;

import android.content.Context;
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
public class NewsfeedView extends LinearLayout {

    @Bind(R.id.iv_profile) ImageView iv_profile;
    @Bind(R.id.iv_describe) ImageView iv_describe;
    @Bind(R.id.tv_love) TextView tv_love;
    @Bind(R.id.tv_describe) TextView tv_desribe;
    @Bind(R.id.tv_id) TextView tv_id;
    @Bind(R.id.tv_time) TextView tv_time;

    public NewsfeedView(Context context) {
        this(context, null);
    }

    public NewsfeedView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NewsfeedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        li.inflate(R.layout.view_newsfeed, this, true);

        ButterKnife.bind(this);

        Glide.with(context)
                .load(R.mipmap.profile)
                .bitmapTransform(new CropCircleTransformation(context))
                .into(iv_profile);
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
}
