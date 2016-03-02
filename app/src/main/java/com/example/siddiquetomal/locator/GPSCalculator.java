package com.example.siddiquetomal.locator;

/**
 * Created by Siddique Tomal on 3/2/2016.
 */
public class GPSCalculator {

    //Source and Destination LatLng
    private  CustomLocation source;
    private CustomLocation destination;

    //distance
    private double distance;

    //Radius of earth
    public static final double R = 6371000;


    //Ctor
    public GPSCalculator(CustomLocation source, CustomLocation destination){
        this.source = source;
        this.destination = destination;
    }

    //Get Haversine distance
    public double getDistanceInKiloMeter(){
        return GPSCalculator.GET_DISTANCE_KM(this.source, this.destination);
    }

    //Get distance in meter
    public double getDistanceInMeter(){
        return GPSCalculator.GET_DISTANCE_M(this.source, this.destination);
    }

    //Get distance in kilometer string
    public String getDistance(char unit){
        switch (unit){
            case 'm':
                return String.valueOf(getDistanceInMeter());
            default:
                return String.valueOf(getDistanceInKiloMeter());
        }
    }



    //Static method for calculating distance
    public static double GET_DISTANCE_KM(CustomLocation source, CustomLocation destination){
        return HaversineInKM(source.latitude, source.longitude, destination.latitude, destination
                .longitude);
    }

    public static String GET_DISTANCE_KM_IN_STRING(CustomLocation source, CustomLocation destination){
        return String.valueOf(HaversineInKM(source.latitude, source.longitude, destination
                .latitude, destination.longitude));
    }

    public static double GET_DISTANCE_M(CustomLocation source, CustomLocation destination){
        return HaversineInM(source.latitude, source.longitude, destination.latitude, destination
                .longitude);
    }

    public static String GET_DISTANCE_M_IN_STRING(CustomLocation source, CustomLocation destination){
        return String.valueOf(HaversineInM(source.latitude, source.longitude, destination
                .latitude, destination.longitude));
    }


    static final double _eQuatorialEarthRadius = 6378.1370D;
    static final double _d2r = (Math.PI / 180D);

    public static int HaversineInM(double lat1, double long1, double lat2, double long2) {
        return (int) (1000D * HaversineInKM(lat1, long1, lat2, long2));
    }

    public static double HaversineInKM(double lat1, double long1, double lat2, double long2) {
        double dlong = (long2 - long1) * _d2r;
        double dlat = (lat2 - lat1) * _d2r;
        double a = Math.pow(Math.sin(dlat / 2D), 2D) + Math.cos(lat1 * _d2r) * Math.cos(lat2 * _d2r)
                * Math.pow(Math.sin(dlong / 2D), 2D);
        double c = 2D * Math.atan2(Math.sqrt(a), Math.sqrt(1D - a));
        double d = _eQuatorialEarthRadius * c;

        return d;
    }

}
