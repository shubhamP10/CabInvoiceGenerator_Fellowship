package com.bridgelabz.invoicegenerator.model;

import com.bridgelabz.invoicegenerator.utility.RideType;

public class Ride {
    public RideType rideType;
    public double distance;
    public int time;

    public Ride(double distance, int time, RideType rideType) {
        this.distance = distance;
        this.time = time;
        this.rideType = rideType;
    }
}