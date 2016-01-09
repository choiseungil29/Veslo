package com.clogic.veslo.Fragment;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by clogic on 2016. 1. 9..
 */
public interface SelectableMapsFragmentListener {

    public void onDestroy(LatLng location);
}
