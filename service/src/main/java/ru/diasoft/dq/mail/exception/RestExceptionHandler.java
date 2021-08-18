package ru.diasoft.dq.mail.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public final ResponseEntity<Object> handleAllExceptions(ApiException ex, WebRequest request) {
        HttpStatus status = Objects.nonNull(ex.getStatus()) ?
                HttpStatus.valueOf(ex.getStatus()) :
                HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(ExceptionUtils.buildErrorData(ex), status);
    }


}
