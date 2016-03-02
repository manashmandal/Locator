package com.example.siddiquetomal.locator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowId;
import android.widget.TextView;
import android.widget.Toast;

public class LocatorMainActivity extends AppCompatActivity {

    //TAG
    final private static String TAG = LocatorMainActivity.class.getSimpleName();

    private LocationListener locationListener;
    private LocationManager locationManager;

    private Location savedLocation;

    private CustomLocation lastKnownCustomLocation;

    public static String CURRENT_LOCATION = "";
    public static Location _CURRENT_LOCATION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locator_main);


        lastKnownCustomLocation = new CustomLocation();

        //Init locationManager
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        //Init locationListener
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                savedLocation = location;
                _CURRENT_LOCATION = location;
                lastKnownCustomLocation.setLocation(location);
                CURRENT_LOCATION = lastKnownCustomLocation.toString();
                showLocation(lastKnownCustomLocation);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }


        };

        //Setting request update
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        locationManager.requestLocationUpdates(locationManager.NETWORK_PROVIDER, 0, 0, locationListener);




    }



    public void showLocation(CustomLocation customLocation)
    {
        Toast.makeText(getApplicationContext(), customLocation.toString(), Toast.LENGTH_SHORT).show();
        Log.i(TAG, customLocation.toString());
    }





}



