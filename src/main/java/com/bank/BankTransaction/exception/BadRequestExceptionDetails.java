package com.bank.BankTransaction.exception;

import java.time.LocalDateTime;

public class BadRequestExceptionDetails extends  ExceptionDetails{


    public BadRequestExceptionDetails(String title, int status, String details, String developerMessage, LocalDateTime timestamp) {
        super(title, status, details, developerMessage, timestamp);
    }
}
