package ru.id20.android;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;


public class TestGoogleActivity extends Activity implements OnMapClickListener
{
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_google);

        if(googleMap == null)
        {
            googleMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
        }

        if(googleMap != null)
        {
            googleMap.setOnMapClickListener(this);
            LatLng moscow = new LatLng(55.748758, 37.6174);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(moscow, (float)12.4));
        }
    }

    @Override
    public void onMapClick(LatLng latLng)
    {
        /*
        tvInfo.setText(point.toString());
        map.animateCamera(CameraUpdateFactory.newLatLng(point));
        */
    }

}
