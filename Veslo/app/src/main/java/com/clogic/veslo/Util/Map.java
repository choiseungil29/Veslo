package com.clogic.veslo.Util;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;
import java.util.Locale;

/**
 * Created by clogic on 2016. 1. 9..
 */
public class Map {

    Geocoder geo = null;

    public Map(Context context) {
        geo = new Geocoder(context, Locale.KOREA);
    }

    public Address latlngToAddress(LatLng location) {
        List<Address> addrList;
        Address addr = null;
        try {
            addrList = geo.getFromLocation(location.latitude, location.longitude, 1);
            addr = addrList.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return addr;
    }
}
