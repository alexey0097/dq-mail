package ru.diasoft.dq.mail.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageBundleServiceImpl implements MessageBundleService {

    private final MessageSource messageSource;

    @Override
    public String getMessage(String messageCode) {
        return this.getMessage(messageCode, (Object) null);
    }

    @Override
    public String getMessage(String messageCode, Object... params) {
        return messageSource.getMessage(messageCode, params, LocaleContextHolder.getLocale());
    }

}
