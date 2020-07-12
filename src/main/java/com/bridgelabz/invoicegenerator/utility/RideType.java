package com.bridgelabz.invoicegenerator.utility;

public enum RideType {
    NORMAL(10, 1, 5), PREMIUM(15, 2, 20);

    public double COST_PER_KM;
    public int COST_PER_MINUTE;
    public double MINIMUM_FARE;

    RideType(double COST_PER_KM, int COST_PER_MINUTE, double MINIMUM_FARE) {
        this.COST_PER_KM = COST_PER_KM;
        this.COST_PER_MINUTE = COST_PER_MINUTE;
        this.MINIMUM_FARE = MINIMUM_FARE;
    }
}