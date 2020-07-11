package com.bridgelabz.invoicegenerator;

import com.bridgelabz.invoicegenerator.service.InvoiceGenerator;
import com.bridgelabz.invoicegenerator.utility.Ride;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(37, fare, 0.0);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinimumFare() {
        double distance = 0.01;
        int time = 1;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(5, fare, 0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturnTotalFare() {
        Ride[] rides = {new Ride(3.0, 7), new Ride(0.01, 1)};
        double fare = invoiceGenerator.calculateFare(rides);
        Assert.assertEquals(38.1, fare, 0.0);
    }

}
