package com.clogic.veslo.View;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.clogic.veslo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by clogic on 2016. 1. 2..
 */
public class GalleryView extends LinearLayout {

    @Bind(R.id.iv_preview) ImageView iv_preview;

    public GalleryView(Context context) {
        this(context, null);
    }

    public GalleryView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GalleryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.init(context);
    }

    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_gallery, this, true);

        ButterKnife.bind(this);

        Glide.with(context)
                .load(R.mipmap.ic_launcher)
                .into(iv_preview);
    }

    public void setImage(@DrawableRes int mipmapId) {
        Glide.with(getContext())
                .load(mipmapId)
                .into(iv_preview);
    }
}
