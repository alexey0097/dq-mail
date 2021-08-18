package ru.diasoft.dq.mail.exception;

public class SystemApiException extends ApiException {
    public SystemApiException(int status, String message) {
        super(status, message, 0);
    }
}
