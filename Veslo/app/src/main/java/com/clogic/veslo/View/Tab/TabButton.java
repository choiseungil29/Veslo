package com.clogic.veslo.View.Tab;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.clogic.veslo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by clogic on 2015. 11. 20..
 */
public class TabButton extends RelativeLayout implements View.OnClickListener {

    private boolean isActive;

    private int position;

    private TabListener listener;

    @Bind(R.id.iv_icon) ImageView iv_icon;
    @Bind(R.id.iv_selector) ImageView iv_selector;

    public TabButton(Context context) {
        this(context, null);
    }

    public TabButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();

        if(attrs == null) {
            return;
        }

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TabButton, 0, 0);

        iv_icon.setImageResource(array.getResourceId(R.styleable.TabButton_iconSrc, R.mipmap.ic_launcher));

        array.recycle();
    }

    public void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_tab, this, true);
        ButterKnife.bind(this, view);
        isActive = false;

        this.setOnClickListener(this);
    }

    public void setOnTabListener(TabListener listener) {
        this.listener = listener;
    }

    public void activate() {
        iv_selector.setVisibility(View.VISIBLE);
        isActive = true;
    }

    public void deactivate() {
        if(listener != null &&
                isActive) {
            listener.onTabUnselected(this);
        }
        iv_selector.setVisibility(View.GONE);
        isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public void onClick(View v) {
        if(listener == null) {
            return;
        }

        if(isActive) {
            listener.onTabReselected(this);
        } else {
            listener.onTabSelected(this);
            activate();
        }
    }
}
