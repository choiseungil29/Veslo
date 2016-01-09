package com.clogic.veslo.Fragment;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.clogic.veslo.R;
import com.clogic.veslo.Util.Map;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by clogic on 2016. 1. 9..
 */
public class SelectableMapFragment extends Fragment implements View.OnKeyListener {

    static final LatLng SEOUL = new LatLng(37.56641923090, 126.9778741551);

    private GoogleMap map;
    private Geocoder geo = null;
    private SelectableMapsFragmentListener listener;
    private LatLng location;

    private String area;

    @Bind(R.id.btn_select) Button btn_select;
    @Bind(R.id.tv_location) TextView tv_location;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        geo = new Geocoder(getContext(), Locale.KOREA);
        location = SEOUL;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(listener != null) {
            listener.onDestroy(location);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selectable_map, container, false);

        ButterKnife.bind(this, view);

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(this);

        MapView mapView = (MapView) view.findViewById(R.id.maps);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        try {
            MapsInitializer.initialize(getContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        map = mapView.getMap();

        // create marker
        MarkerOptions marker = new MarkerOptions().position(
                SEOUL).title("Hello Maps");

        // Changing marker icon
        marker.icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

        // adding marker
        map.addMarker(marker);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(SEOUL).zoom(15).build();
        map.moveCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));

        map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                Map map = new Map(getContext());
                Address addr = map.latlngToAddress(cameraPosition.target);
                area = addr.getAdminArea() + " " + addr.getLocality() + " " + addr.getThoroughfare();
                tv_location.setText(area);
                location = cameraPosition.target;
            }
        });

        return view;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            getChildFragmentManager().beginTransaction().remove(this).commit();
            return true;
        }
        return false;
    }

    public void setListener(SelectableMapsFragmentListener listener) {
        this.listener = listener;
    }

    @OnClick(R.id.btn_select)
    public void selectLocation() {
        getChildFragmentManager().beginTransaction().remove(this).commit();
        if(listener != null) {
            listener.onDestroy(location);
        }
    }
}
