package ru.diasoft.dq.mail.exception;

public class InternalServerBusinessApiException extends BusinessApiException {
    public InternalServerBusinessApiException(String message) {
        super(500, message);
    }
}
