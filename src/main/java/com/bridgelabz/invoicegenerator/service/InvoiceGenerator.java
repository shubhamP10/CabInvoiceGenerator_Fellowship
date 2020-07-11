package com.bridgelabz.invoicegenerator.service;

import com.bridgelabz.invoicegenerator.exception.InvoiceGeneratorException;
import com.bridgelabz.invoicegenerator.model.InvoiceSummary;
import com.bridgelabz.invoicegenerator.utility.Ride;
import com.bridgelabz.invoicegenerator.utility.RideRepository;

public class InvoiceGenerator {
    private static final double COST_PER_KM = 10.0;
    private static final int COST_PER_MINUTE = 1;
    private static final double MINIMUM_FARE = 5.0;

    RideRepository rideRepository;

    public InvoiceGenerator() {
        rideRepository = new RideRepository();
    }

    public InvoiceSummary calculateFare(Ride... rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += ride.distance * COST_PER_KM + ride.time * COST_PER_MINUTE;
        }
        if (totalFare < MINIMUM_FARE)
            return new InvoiceSummary(rides.length, MINIMUM_FARE);
        return new InvoiceSummary(rides.length, totalFare);
    }

    public InvoiceSummary invoiceForUser(String userId) {
        return calculateFare(rideRepository.getRidesForUser(userId));
    }

    public void addRideToRepository(String[] userId, Ride[][] rides) throws InvoiceGeneratorException {
        for (int i = 0; i < userId.length; i++) {
            rideRepository.addRideForUser(userId[i], rides[i]);
        }
    }
}
