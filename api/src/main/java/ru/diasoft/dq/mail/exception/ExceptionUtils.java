package ru.diasoft.dq.mail.exception;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ExceptionUtils {

    private ExceptionUtils() { }

    public static Map buildErrorData(Integer status, String errorMessage, Integer errorCode, String errorStack, Object errorDetails) {
        Map<String, Object> errorData = new HashMap();
        if (errorCode != null) {
            errorData.put("errorCode", errorCode);
        }

        if (errorMessage != null) {
            errorData.put("errorMessage", errorMessage);
        }

        if (errorStack != null) {
            errorData.put("errorStack", errorStack);
        }

        if (errorDetails != null) {
            errorData.put("errorDetails", errorDetails);
        }

        Map<String, Object> result = new HashMap();
        if (!errorData.isEmpty()) {
            result.put("errorData", errorData);
        }

        if (status != null) {
            result.put("status", status);
        }

        return result;
    }

    public static Map buildErrorData(Integer status, String errorMessage, Integer errorCode) {
        return buildErrorData(status, errorMessage, errorCode, (String)null, (Object)null);
    }

    public static Map buildListElementErrorData(String errorMessage, Integer errorCode) {
        return buildErrorData((Integer)null, errorMessage, errorCode, (String)null, (Object)null);
    }

    public static Map buildErrorData(ApiException apiException) {
        return buildErrorData(apiException, apiException.getStatus(), apiException.getCode(), apiException.getDetails());
    }

    public static Map buildErrorData(Exception exception, Integer status, Integer code) {
        return buildErrorData(exception, status, code, (Object)null);
    }

    public static Map buildErrorData(Exception exception, Integer status, Integer code, Object errorDetails) {
        return buildErrorData(status, StringUtils.defaultString(exception.getMessage()), code, org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace(exception), errorDetails);
    }
}
