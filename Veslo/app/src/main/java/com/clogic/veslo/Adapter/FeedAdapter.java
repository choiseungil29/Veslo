package com.clogic.veslo.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.clogic.veslo.Model.Feed;
import com.clogic.veslo.Util.Provider;
import com.clogic.veslo.View.FeedView;

/**
 * Created by clogic on 2015. 11. 18..
 */
public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        FeedView view = new FeedView(viewGroup.getContext());

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        FeedView view = viewHolder.view;
        Feed feed = Provider.getInstance().getAllNewsfeed().get(i);

        view.setId(feed.getId());
        view.setTime(feed.getUpdateTime());
        view.setTextDescribe(feed.getTextDescribe());
        try {
            view.setLove(feed.getLove());
        } catch (Exception e) {
            e.printStackTrace();
        }
        view.setProfileRes(feed.getDescribeResId());
        view.setDescribeRes(feed.getDescribeResId());
    }

    @Override
    public int getItemCount() {
        return Provider.getInstance().getAllNewsfeed().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public FeedView view;
        public ViewHolder(FeedView v) {
            super(v);
            view = v;
        }
    }
}
