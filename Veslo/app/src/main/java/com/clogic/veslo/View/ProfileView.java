package com.clogic.veslo.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.clogic.veslo.Model.Server.User;
import com.clogic.veslo.R;
import com.clogic.veslo.Util.Provider;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by clogic on 2015. 11. 22..
 */
public class ProfileView extends LinearLayout {

    @Bind(R.id.iv_profile) ImageView iv_profile;
    @Bind(R.id.tv_feedCount) TextView tv_feedCount;
    @Bind(R.id.tv_nickname) TextView tv_nickname;

    private ProfileViewEventListener listener;

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

        tv_feedCount.setText(String.valueOf(Provider.getInstance().getAllNewsfeed().size()));

        User user = Provider.getInstance().getUserProfile();
        tv_nickname.setText(user.username);

        Glide.with(context)
                .load(user.profilePath)
                .bitmapTransform(new CropCircleTransformation(context))
                .into(iv_profile);
    }

    public void setOnProfileViewListener(ProfileViewEventListener listener) {
        this.listener = listener;
    }

    @OnClick(R.id.ll_maps)
    public void startMaps() {
        this.listener.onClickMapTitle();
    }
}