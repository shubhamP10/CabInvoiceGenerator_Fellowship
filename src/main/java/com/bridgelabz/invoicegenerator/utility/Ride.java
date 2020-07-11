package com.bridgelabz.invoicegenerator.utility;

public class Ride {
    public double distance;
    public int time;
    public String rideType;

    public Ride(double distance, int time) {
        this.distance = distance;
        this.time = time;
    }

    public Ride(double distance, int time, String rideType) {
        this.distance = distance;
        this.time = time;
        this.rideType = rideType;
    }
}
