package com.bridgelabz.invoicegenerator.service;

import com.bridgelabz.invoicegenerator.exception.InvoiceGeneratorException;
import com.bridgelabz.invoicegenerator.model.InvoiceSummary;
import com.bridgelabz.invoicegenerator.model.Ride;
import com.bridgelabz.invoicegenerator.utility.RideRepository;

import java.util.stream.IntStream;

public class InvoiceGenerator {

    RideRepository rideRepository;

    public InvoiceGenerator() {
        rideRepository = new RideRepository();
    }

    /**
     * METHOD TO CALCULATE TOTAL FARE
     *
     * @param rides provides ride details
     * @return Invoice summary
     */
    public InvoiceSummary calculateFare(Ride... rides) {
        double totalFare = 0;
        double rideFare;
        for (Ride ride : rides) {
            rideFare = ride.distance * ride.rideType.COST_PER_KM + ride.time * ride.rideType.COST_PER_MINUTE;
            if (rideFare < ride.rideType.MINIMUM_FARE)
                rideFare = ride.rideType.MINIMUM_FARE;
            totalFare += rideFare;
        }
        return new InvoiceSummary(rides.length, totalFare);
    }

    /**
     * METHOD TO GENERATE INVOICE FOR USER
     *
     * @param userId provides user id to get particular invoice
     * @return Invoice summary
     */
    public InvoiceSummary invoiceForUser(String userId) {
        return calculateFare(rideRepository.getRidesForUser(userId));
    }

    /**
     * METHOD TO ADD RIDE DETAILS ACCORDING TO USER IN LIST
     *
     * @param userId provides user id to add particular user ride details
     * @param rides  provides total rides
     */
    public void addRideToRepository(String[] userId, Ride[][] rides) {
        IntStream.range(0, userId.length).forEach(i -> {
            try {
                rideRepository.addRideForUser(userId[i], rides[i]);
            } catch (InvoiceGeneratorException e) {
                e.printStackTrace();
            }
        });
    }
}