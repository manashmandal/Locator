package com.example.siddiquetomal.locator;

import android.location.Location;

/**
 * Created by Manash Mandal
 */
//Custom Location Class with extra features
public class CustomLocation {
    private Location location;
    public double latitude;
    public double longitude;

    public void setLatitude(double lat) {
        this.latitude = lat;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getLat() {
        return String.valueOf(this.latitude);
    }

    public String getLong() {
        return String.valueOf(this.longitude);
    }

    public String getLongitude() {
        return String.valueOf(location.getLongitude());
    }

    public String getLatitude() {
        return String.valueOf(location.getLatitude());
    }

    public CustomLocation(Location location) {
        this.location = location;
        this.longitude = location.getLongitude();
        this.latitude = location.getLatitude();
    }

    public CustomLocation() {
    }

    public CustomLocation(double lat, double longi) {
        this.latitude = lat;
        this.longitude = longi;
    }

    public void setLocation(Location location) {
        this.location = location;
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }

    @Override
    public String toString() {
        return "Latitiude: " + this.getLatitude() + "\nLongitude: " + this.getLongitude();
    }

    public Location getSimpleLocation() {
        return this.location;
    }

    public String getInformation() {
        return "Latitiude: " + this.getLat() + "\nLongitude: " + this.getLong();
    }

}
