package ru.diasoft.dq.mail.exception;

public class ApiException extends RuntimeException {
    private static final long serialVersionUID = 4513903531769568181L;
    public static final Integer STATUS_DEFAULT = 422;
    private Integer status;
    private Integer code;
    private Object details;

    public ApiException(Integer status, String message, Integer code, Object details) {
        super(message);
        this.status = status;
        this.code = code;
        this.details = details;
    }

    public ApiException(Integer status, String message) {
        this(status, message, (Integer)null, (Object)null);
    }

    public ApiException(Integer status, String message, Integer code) {
        this(status, message, code, (Object)null);
    }

    public ApiException(String message, Integer code) {
        this(STATUS_DEFAULT, message, code, (Object)null);
    }

    public ApiException(String message) {
        this(STATUS_DEFAULT, message, (Integer)null, (Object)null);
    }

    public ApiException(Integer status, String message, Object details) {
        this(status, message, (Integer)null, details);
    }

    public ApiException(String message, Integer code, Object details) {
        this(STATUS_DEFAULT, message, code, details);
    }

    public ApiException(String message, Object details) {
        this(STATUS_DEFAULT, message, (Integer)null, details);
    }

    public Integer getStatus() {
        return this.status;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return super.getMessage();
    }

    public Object getDetails() {
        return this.details;
    }

}
