package com.clogic.veslo.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clogic.veslo.Adapter.FeedAdapter;
import com.clogic.veslo.R;
import com.clogic.veslo.Model.Feed;
import com.clogic.veslo.Util.Provider;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by clogic on 2015. 11. 18..
 */
public class NewsfeedFragment extends Fragment {

    @Bind(R.id.rv_card) RecyclerView rv_card;

    public NewsfeedFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newsfeed, container, false);
        ButterKnife.bind(this, view);

        FeedAdapter adapter = new FeedAdapter();
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());

        rv_card.setLayoutManager(manager);
        rv_card.setAdapter(adapter);

        return view;
    }
}
