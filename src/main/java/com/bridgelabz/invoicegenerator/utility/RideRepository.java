package com.bridgelabz.invoicegenerator.utility;

import com.bridgelabz.invoicegenerator.exception.InvoiceGeneratorException;

import java.util.HashMap;
import java.util.Map;

public class RideRepository
{
    Map<String, Ride[]> userRides = new HashMap<>();

    public void addRideForUser(String userId, Ride[] rides) throws InvoiceGeneratorException
    {
        if (userRides.containsKey(userId))
            throw new InvoiceGeneratorException(InvoiceGeneratorException.ExceptionType.USER_ALREADY_EXISTS,
                    "User ID Already Exists!!!");
        else
            userRides.put(userId, rides);
    }

    public Ride[] getRidesForUser(String userId)
    {
        return userRides.get(userId);
    }
}
