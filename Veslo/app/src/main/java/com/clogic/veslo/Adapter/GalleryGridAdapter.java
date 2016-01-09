package com.clogic.veslo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.clogic.veslo.R;

import java.util.ArrayList;

/**
 * Created by clogic on 2016. 1. 2..
 */
public class GalleryGridAdapter extends BaseAdapter {

    LayoutInflater inflater;

    public GalleryGridAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 9;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.view_gallery, parent, false);
        }


        return convertView;
    }
}
