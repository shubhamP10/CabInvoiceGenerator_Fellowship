package com.bridgelabz.invoicegenerator.utility;

public class Ride {
    public String rideType;
    public double distance;
    public int time;

    public Ride(double distance, int time, String rideType) {
        this.distance = distance;
        this.time = time;
        this.rideType = rideType;
    }
}
