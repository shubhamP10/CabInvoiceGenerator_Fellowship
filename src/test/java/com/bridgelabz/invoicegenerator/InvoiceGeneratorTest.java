package com.bridgelabz.invoicegenerator;

import com.bridgelabz.invoicegenerator.service.InvoiceGenerator;
import org.junit.Assert;
import org.junit.Test;

public class InvoiceGeneratorTest {

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        double distance = 3.0;
        int time = 7;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(37, fare, 0.0);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinimumFare()
    {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        double distance = 0.01;
        int time = 1;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(5, fare, 0.0);
    }

}
