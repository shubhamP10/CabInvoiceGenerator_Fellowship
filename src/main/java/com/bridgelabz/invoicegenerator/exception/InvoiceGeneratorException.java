package com.bridgelabz.invoicegenerator.exception;

public class InvoiceGeneratorException extends Exception {
    public ExceptionType type;

    public InvoiceGeneratorException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }

    public enum ExceptionType {
        USER_ALREADY_EXISTS
    }
}
