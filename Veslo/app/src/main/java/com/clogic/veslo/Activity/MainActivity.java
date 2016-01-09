package com.clogic.veslo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.clogic.veslo.Fragment.NewsfeedFragment;
import com.clogic.veslo.Fragment.ProfileFragment;
import com.clogic.veslo.Model.Feed;
import com.clogic.veslo.R;
import com.clogic.veslo.Util.Provider;
import com.clogic.veslo.View.Tab.TabButton;
import com.clogic.veslo.View.Tab.CLogicTabHost;
import com.clogic.veslo.View.Tab.TabListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by clogic on 2015. 11. 14..
 */
public class MainActivity extends AppCompatActivity implements TabListener {

    @Bind(R.id.vp_main) ViewPager pager;
    @Bind(R.id.th_host)
    CLogicTabHost tabHost;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Provider.getInstance().addNewsfeed(new Feed("clogic", "2015/11/03", "ㅁㄴㅇㅁㄴㅇㅁㄴㅇ", "1,991", R.mipmap.profile));
        Provider.getInstance().addNewsfeed(new Feed("clogic", "2015/11/03", "ㅁㄴㅇㅁㄴㅇㅁㄴㅇ", "1,991", R.mipmap.profile));
        Provider.getInstance().addNewsfeed(new Feed("clogic", "2015/11/03", "ㅁㄴㅇㅁㄴㅇㅁㄴㅇ", "1,991", R.mipmap.profile));
        Provider.getInstance().addNewsfeed(new Feed("clogic", "2015/11/03", "ㅁㄴㅇㅁㄴㅇㅁㄴㅇ", "1,991", R.mipmap.ic_launcher));

        tabHost.setOnTabListener(this);

        pager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabHost.navigatingToPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.step_in, R.anim.slide_out);
    }

    @Override
    public void onTabSelected(TabButton tabButton) {
        pager.setCurrentItem(tabButton.getPosition());
    }

    @Override
    public void onTabReselected(TabButton tabButton) {

    }

    @Override
    public void onTabUnselected(TabButton tabButton) {

    }

    @OnClick(R.id.btn_create_post)
    public void intentCreatePostActivity() {
        Intent intent = new Intent(this, CreatePostActivity.class);
        startActivity(intent);
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> list;

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            list = new ArrayList<>();
            list.add(new NewsfeedFragment());
            list.add(new NewsfeedFragment());
            list.add(new NewsfeedFragment());
            list.add(new ProfileFragment());
        }

        @Override
        public int getCount() {
            return  list.size();
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }
    }
}
