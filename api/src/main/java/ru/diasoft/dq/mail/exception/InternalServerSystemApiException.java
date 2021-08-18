package ru.diasoft.dq.mail.exception;

public class InternalServerSystemApiException extends SystemApiException {
    public InternalServerSystemApiException(String message) {
        super(500, message);
    }
}
