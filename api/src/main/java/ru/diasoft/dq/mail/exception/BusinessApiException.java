package ru.diasoft.dq.mail.exception;

public class BusinessApiException extends ApiException {
    public BusinessApiException(int status, String message) {
        super(status, message, 1);
    }
}

