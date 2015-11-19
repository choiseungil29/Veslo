package com.clogic.veslo.View.Tab;

/**
 * Created by clogic on 2015. 11. 20..
 */
public interface TabListener {

    void onTabSelected(TabButton tabButton);
    void onTabReselected(TabButton tabButton);
    void onTabUnselected(TabButton tabButton);
}
