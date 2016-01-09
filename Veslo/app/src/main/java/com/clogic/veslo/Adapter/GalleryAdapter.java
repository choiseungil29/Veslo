package com.clogic.veslo.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.clogic.veslo.Model.Feed;
import com.clogic.veslo.R;
import com.clogic.veslo.Util.Logger;
import com.clogic.veslo.Util.Provider;
import com.clogic.veslo.View.GalleryView;
import com.clogic.veslo.View.SimpleCalendarView;

import java.util.List;

/**
 * Created by clogic on 2016. 1. 2..
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    List<Feed> feeds;

    public GalleryAdapter() {
        feeds = Provider.getInstance().getAllNewsfeed();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GalleryView view = new GalleryView(parent.getContext());

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.view.setImage(feeds.get(position).getDescribeResId());
    }

    @Override
    public int getItemCount() {
        Logger.i("Feeds Size : " + feeds.size());
        return feeds.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        GalleryView view;

        public ViewHolder(GalleryView v) {
            super(v);
            view = v;
        }
    }
}
