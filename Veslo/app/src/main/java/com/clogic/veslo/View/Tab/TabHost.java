package com.clogic.veslo.View.Tab;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clogic on 2015. 11. 20..
 */
public class TabHost extends LinearLayout {

    private List<TabButton> tabs;

    public TabHost(Context context) {
        this(context, null);
    }

    public TabHost(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabHost(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    public void onFinishInflate() {
        super.onFinishInflate();

        int position = 0;
        for(int i=0; i<getChildCount(); i++) {
            if(!(getChildAt(i) instanceof TabButton)) {
                continue;
            }

            TabButton tabButton = (TabButton) getChildAt(i);
            tabButton.setPosition(position);
            tabs.add(tabButton);

            position++;
        }
    }

    public void initView() {
        tabs = new ArrayList<>();
    }

    public void setOnTabListener(TabListener listener) {
        for(TabButton tabButton : tabs) {
            tabButton.setOnTabListener(listener);
        }
    }

    public void navigatingToPosition(int position) {
        for(int i=0; i<tabs.size(); i++) {
            if(i == position) {
                tabs.get(i).activate();
            } else {
                tabs.get(i).deactivate();
            }
        }
    }
}
