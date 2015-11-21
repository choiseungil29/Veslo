package com.clogic.veslo.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.clogic.veslo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by clogic on 2015. 11. 22..
 */
public class ProfileView extends LinearLayout {

    @Bind(R.id.iv_profile) ImageView iv_profile;

    public ProfileView(Context context) {
        this(context, null);
    }

    public ProfileView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProfileView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_profile, this, true);

        ButterKnife.bind(this);

        Glide.with(context)
                .load(R.mipmap.ic_launcher)
                .bitmapTransform(new CropCircleTransformation(context))
                .into(iv_profile);
    }
}
