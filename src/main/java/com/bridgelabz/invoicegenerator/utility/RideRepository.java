package com.bridgelabz.invoicegenerator.utility;

import com.bridgelabz.invoicegenerator.exception.InvoiceGeneratorException;
import com.bridgelabz.invoicegenerator.model.Ride;

import java.util.HashMap;
import java.util.Map;

public class RideRepository {
    Map<String, Ride[]> userRides = new HashMap<>();

    /**
     * METHOD TO ADD RIDES FOR PARTICULAR USER
     *
     * @param userId provides user id for user
     * @param rides  provides all rides for user
     * @throws InvoiceGeneratorException handles exception for duplicate user id
     */
    public void addRideForUser(String userId, Ride[] rides) throws InvoiceGeneratorException {
        if (userRides.containsKey(userId))
            throw new InvoiceGeneratorException(InvoiceGeneratorException.ExceptionType.USER_ALREADY_EXISTS,
                    "User ID Already Exists!!!");
        else
            userRides.put(userId, rides);
    }

    /**
     * METHOD TO GET RIDE DETAILS FOR PARTICULAR USER
     *
     * @param userId provides user id to get ride details for user
     * @return total rides for user as per user id
     */
    public Ride[] getRidesForUser(String userId) {
        return userRides.get(userId);
    }
}