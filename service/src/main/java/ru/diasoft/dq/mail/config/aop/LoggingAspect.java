package ru.diasoft.dq.mail.config.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
@Aspect
@Log4j2
public class LoggingAspect {

    private final ObjectMapper objectMapper;

    private static final String TEMPLATE_METHOD_EXECUT = "START METHOD: %s.%s()";
    private static final String TEMPLATE_METHOD_ARGS = "ARGS=%s[%s]";
    private static final String TEMPLATE_METHOD_EXECUTED = "FINISH METHOD: %s.%s()";
    private static final String TEMPLATE_RETURNED = "RETURN: %s%s";
    private static final String TEMPLATE_METHOD_EXECUTED_TIME = "method %s.%s() executed for %s ms";
    private static final String TEMPLATE_EXCEPTION_MESSAGE = "RETURN EXCEPTION: %s%s";

    private static final String SEPARATOR = "----------------------------------------------------";

    @Pointcut("within(ru.diasoft.dq.mail.service..*)")
    public void services() {
        // сука ебучий сонар, это не ошибка
    }

    @Before("services()")
    public void beforeCallAtMethod(JoinPoint jp) {
        if (log.isInfoEnabled()) {
            String args = Arrays.stream(jp.getArgs())
                    .filter(Objects::nonNull)
                    .map(obj -> {
                        try {
                            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
                        } catch (JsonProcessingException e) {
                            log.error(e);
                            return obj.toString();
                        }
                    })
                    .collect(Collectors.joining(","));
            String methodName = jp.getSignature().getName();
            String className = jp.getTarget().getClass().getName();
            String msg1 = String.format(TEMPLATE_METHOD_EXECUT, className, methodName);
            String msg2 = String.format(TEMPLATE_METHOD_ARGS, System.lineSeparator(), args);
            log.info(SEPARATOR);
            log.info(msg1);
            log.info(msg2);
        }
    }

    @After("services()")
    public void logMethodCall(JoinPoint jp) {
        if (log.isInfoEnabled()) {
            String methodName = jp.getSignature().getName();
            String className = jp.getTarget().getClass().getName();
            String msg1 = String.format(TEMPLATE_METHOD_EXECUTED, className, methodName);
            log.info(msg1);
        }
    }


    @AfterReturning(pointcut = "services()", returning = "result")
    public void afterDebug(final JoinPoint joinPoint, final Object result) {
        if(log.isInfoEnabled()) {
            String resultArgs = "no have";
            if (result != null) {
                try {
                    resultArgs = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
                } catch (JsonProcessingException e) {
                    log.error(e);
                    resultArgs = result.toString();
                }
            }
            String msg1 = String.format(TEMPLATE_RETURNED, System.lineSeparator(), resultArgs);
            log.info(msg1);
            log.info(SEPARATOR);
        }
    }

    @AfterThrowing(pointcut = "services()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        if (log.isInfoEnabled()) {
            String msg1 = String.format(TEMPLATE_EXCEPTION_MESSAGE, System.lineSeparator(), exception.getCause());
            log.error(msg1);
            log.info(SEPARATOR);
        }
    }

    @Around("@annotation(ru.diasoft.dq.mail.config.aop.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint jp) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = jp.proceed();
        long executionTime = System.currentTimeMillis() - start;
        String methodName = jp.getSignature().getName();
        String className = jp.getTarget().getClass().getName();
        String msg1 = String.format(TEMPLATE_METHOD_EXECUTED_TIME, className, methodName, executionTime);
        if (log.isInfoEnabled()) log.info(msg1);
        return proceed;
    }

}
