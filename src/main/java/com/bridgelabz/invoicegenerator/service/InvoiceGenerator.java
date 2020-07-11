package com.bridgelabz.invoicegenerator.service;

public class InvoiceGenerator {
    private static final double COST_PER_KM = 10.0;
    private static final int COST_PER_MIN = 1;

    public double calculateFare(double distance, int time) {
        return distance * COST_PER_KM + time * COST_PER_MIN;
    }
}
