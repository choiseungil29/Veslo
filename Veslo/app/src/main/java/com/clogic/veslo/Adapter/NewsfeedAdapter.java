package com.clogic.veslo.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.clogic.veslo.Util.Newsfeed;
import com.clogic.veslo.Util.Provider;
import com.clogic.veslo.View.NewsfeedView;

/**
 * Created by clogic on 2015. 11. 18..
 */
public class NewsfeedAdapter extends RecyclerView.Adapter<NewsfeedAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        NewsfeedView view = new NewsfeedView(viewGroup.getContext());

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        NewsfeedView view = viewHolder.view;
        Newsfeed feed = Provider.getInstance().getAllNewsfeed().get(i);

        view.setId(feed.getId());
        view.setTime(feed.getUpdateTime());
        view.setTextDescribe(feed.getTextDescribe());
        try {
            view.setLove(feed.getLove());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return Provider.getInstance().getAllNewsfeed().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public NewsfeedView view;
        public ViewHolder(NewsfeedView v) {
            super(v);
            view = v;
        }
    }
}
