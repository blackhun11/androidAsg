package com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, Response.ErrorListener, Response.Listener<JSONObject> {

    private GoogleMap mMap;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady ( GoogleMap googleMap ) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        String url = "http://api.myjson.com/bins/g616s";
        markLocation(url);

    }

    private void markLocation ( String url ) {
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url , null , this , this);
        mRequestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse ( VolleyError error ) {

    }

    @Override
    public void onResponse ( JSONObject response ) {
        try {
            JSONArray jsonArray = response.getJSONArray("markers");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                double lat = object.getJSONObject("location").getDouble("lat");
                double lng = object.getJSONObject("location").getDouble("lng");
                LatLng coordinate = new LatLng(lat , lng);
                mMap.addMarker(new MarkerOptions().position(coordinate).title(object.getString("name")));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(coordinate));
            }
        } catch (JSONException e) {
            Toast.makeText(this , e.toString() , Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
