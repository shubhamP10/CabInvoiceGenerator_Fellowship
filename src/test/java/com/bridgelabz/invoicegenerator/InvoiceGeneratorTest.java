package com.bridgelabz.invoicegenerator;

import com.bridgelabz.invoicegenerator.exception.InvoiceGeneratorException;
import com.bridgelabz.invoicegenerator.model.InvoiceSummary;
import com.bridgelabz.invoicegenerator.model.Ride;
import com.bridgelabz.invoicegenerator.service.InvoiceGenerator;
import com.bridgelabz.invoicegenerator.utility.RideType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class InvoiceGeneratorTest {
    InvoiceGenerator invoiceGenerator;

    @Before
    public void setUp() {
        invoiceGenerator = new InvoiceGenerator();
    }

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        double distance = 3.0;
        int time = 7;
        InvoiceSummary invoiceSummary = invoiceGenerator.calculateFare(new Ride(distance, time, RideType.NORMAL));
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(1, 37);
        Assert.assertEquals(expectedInvoiceSummary, invoiceSummary);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinimumFare() {
        double distance = 0.01;
        int time = 1;
        InvoiceSummary invoiceSummary = invoiceGenerator.calculateFare(new Ride(distance, time, RideType.NORMAL));
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(1, 5);
        Assert.assertEquals(expectedInvoiceSummary, invoiceSummary);
    }

    @Test
    public void givenMultipleRides_ShouldReturnTotalFare() {
        Ride[] rides = {new Ride(3.0, 7, RideType.NORMAL), new Ride(0.01, 1, RideType.NORMAL)};
        InvoiceSummary invoiceSummary = invoiceGenerator.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(rides.length, 42);
        Assert.assertEquals(expectedInvoiceSummary, invoiceSummary);
    }

    @Test
    public void givenUserId_ShouldReturnInvoiceSummary() throws InvoiceGeneratorException {
        String[] userId = {"user1", "user2", "user3"};
        Ride[][] rides = {{new Ride(5.0, 12, RideType.NORMAL), new Ride(2.5, 6, RideType.NORMAL)},
                {new Ride(3.0, 5, RideType.NORMAL), new Ride(0.01, 1, RideType.NORMAL)},
                {new Ride(10.0, 15, RideType.NORMAL), new Ride(2, 30, RideType.NORMAL)}};
        invoiceGenerator.addRideToRepository(userId, rides);
        InvoiceSummary summary = invoiceGenerator.invoiceForUser(userId[2]);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(rides[2].length, 165.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenSameUserId_ShouldThrowException() {
        String[] userId = {"user1", "user1", "user3"};
        Ride[][] rides = {{new Ride(5.0, 12, RideType.NORMAL), new Ride(2.5, 6, RideType.NORMAL)},
                {new Ride(3.0, 5, RideType.NORMAL), new Ride(0.01, 1, RideType.NORMAL)},
                {new Ride(10.0, 15, RideType.NORMAL), new Ride(2, 30, RideType.NORMAL)}};
        ExpectedException exceptionRule = ExpectedException.none();
        exceptionRule.expect(InvoiceGeneratorException.class);
        invoiceGenerator.addRideToRepository(userId, rides);
    }

    @Test
    public void givenPremiumAndNormalRideForUserId_ShouldReturnInvoiceSummary() throws InvoiceGeneratorException {
        String[] userId = {"user1", "user2", "user3"};
        Ride[][] rides = {{new Ride(5.0, 12, RideType.PREMIUM), new Ride(2.5, 6, RideType.NORMAL)},
                {new Ride(3.0, 5, RideType.PREMIUM), new Ride(0.01, 1, RideType.PREMIUM)},
                {new Ride(10.0, 15, RideType.NORMAL), new Ride(2, 30, RideType.PREMIUM)}};
        invoiceGenerator.addRideToRepository(userId, rides);
        InvoiceSummary summary = invoiceGenerator.invoiceForUser(userId[1]);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(rides[1].length, 75.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }
}