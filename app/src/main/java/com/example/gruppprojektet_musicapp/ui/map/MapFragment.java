package com.example.gruppprojektet_musicapp.ui.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.gruppprojektet_musicapp.Database.Database;
import com.example.gruppprojektet_musicapp.R;
import com.example.gruppprojektet_musicapp.ui.ConcertInfo.ConcertInfoFragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Looper;
import android.provider.Settings;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_AZURE;


public class MapFragment extends Fragment implements OnMapReadyCallback {

    private MapViewModel homeViewModel;
    private GoogleMap mMap;
    Database databaseHandler;
    FusedLocationProviderClient mfusedLocationProviderClient;
    LocationRequest locationRequest;
    int PERMISSION_ID = 44;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((MapView)this.getActivity().findViewById(R.id.map_view)).onCreate(savedInstanceState);
        ((MapView)this.getActivity().findViewById(R.id.map_view)).onResume();
        ((MapView)this.getActivity().findViewById(R.id.map_view)).getMapAsync((OnMapReadyCallback)this);
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(MapViewModel.class);
        View root = inflater.inflate(R.layout.fragment_map, container, false);

        return root;
    }


    //https://developers.google.com/maps/documentation/android-sdk/map-with-marker
    @Override
    public void onMapReady(GoogleMap googleMap) {
        databaseHandler = new Database(getActivity());
        mfusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        mMap = googleMap;

        //setLocation();
        getLastLocation();

        mMap.setMinZoomPreference(6.0f);
        mMap.setMaxZoomPreference(25.0f);

        // Add a marker in  lish
        LatLng LidkopingTwo = new LatLng(58.284979, 12.290520);
        mMap.addMarker(new MarkerOptions().position(LidkopingTwo).title("Inflames").snippet("Örthagsparken Metal 21:00"));

        // Add a marker in Trollhättan
        LatLng RockBar = new LatLng(58.285624, 12.291937);
        mMap.addMarker(new MarkerOptions().position(RockBar).title("Red Hot Chili Peppers").snippet("Backstage Rockbar Funk 22:00"));

        // Add a marker in  lish
        LatLng LidkopingTjo = new LatLng(58.498068, 13.158895);
        mMap.addMarker(new MarkerOptions().position(LidkopingTjo).title("Tjo").snippet("Örthagsparken Funk 21:00"));


        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                String markerTitle = marker.getTitle();

                //Skickar över värde till annan fragment
                ConcertInfoFragment sendFragment = new ConcertInfoFragment();

                Bundle bundle = new Bundle();
                bundle.putString("data", markerTitle);
                sendFragment.setArguments(bundle);


                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.nav_host_fragment, sendFragment).commit();

            }
        });

    }

    //////////////////////////////////////////////PERMISSION////////////////////////////////////////////
    //https://developers.google.com/maps/documentation/android-sdk/current-place-tutorial
    @SuppressLint("MissingPermission")
    private void getLastLocation(){
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mfusedLocationProviderClient.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                Location location = task.getResult();
                                if (location == null) {
                                    requestNewLocationData();
                                } else {
                                    // Add a marker in  lish, and move the camera. YOUR POSITION
                                    LatLng Lidkoping = new LatLng(location.getLatitude(), location.getLongitude());
                                    mMap.addMarker(new MarkerOptions().position(Lidkoping).title("Your Position")).setIcon(BitmapDescriptorFactory.defaultMarker(HUE_AZURE));
                                    //change colorfrom Bitmap(HUE_BLUE)
                                    mMap.moveCamera(CameraUpdateFactory.newLatLng(Lidkoping));
                                    mMap.moveCamera(CameraUpdateFactory.zoomTo(15f));

                                }
                            }
                        }
                );
            } else {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData(){

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        mfusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        mfusedLocationProviderClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );
    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();

            // Add a marker in  lish, and move the camera. YOUR POSITION
            LatLng Lidkoping = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            mMap.addMarker(new MarkerOptions().position(Lidkoping).title("Your Position"));
            //change color

            mMap.moveCamera(CameraUpdateFactory.newLatLng(Lidkoping));
            mMap.moveCamera(CameraUpdateFactory.zoomTo(15f));
            //getLocation(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()));
        }
    };

    private boolean checkPermissions(){
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true;
        }
        return false;
    }

    private void requestPermissions(){
        ActivityCompat.requestPermissions(
                getActivity(),
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }
    private boolean isLocationEnabled(){
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // Granted. Start getting the location information
            }
        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////

    private void toast(String message)
    {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

}
