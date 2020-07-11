package com.bridgelabz.invoicegenerator;

import com.bridgelabz.invoicegenerator.service.InvoiceGenerator;
import com.bridgelabz.invoicegenerator.utility.InvoiceSummery;
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
        InvoiceSummery invoiceSummery = invoiceGenerator.calculateFare(new Ride(distance, time));
        InvoiceSummery expectedInvoiceSummery = new InvoiceSummery(1,37);
        Assert.assertEquals(expectedInvoiceSummery, invoiceSummery);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinimumFare() {
        double distance = 0.01;
        int time = 1;
        InvoiceSummery invoiceSummery = invoiceGenerator.calculateFare(new Ride(distance, time));
        InvoiceSummery expectedInvoiceSummery = new InvoiceSummery(1,5);
        Assert.assertEquals(expectedInvoiceSummery, invoiceSummery);
    }

    @Test
    public void givenMultipleRides_ShouldReturnTotalFare() {
        Ride[] rides = {new Ride(3.0, 7), new Ride(0.01, 1)};
        InvoiceSummery invoiceSummery = invoiceGenerator.calculateFare(rides);
        InvoiceSummery expectedInvoiceSummery = new InvoiceSummery(rides.length,38.1);
        Assert.assertEquals(expectedInvoiceSummery, invoiceSummery);
    }

}
