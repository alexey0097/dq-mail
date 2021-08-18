package ru.diasoft.dq.mail.interceptor;

import com.google.common.base.Strings;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;
import ru.diasoft.dq.mail.exception.InternalServerSystemApiException;

import java.util.Objects;

@Component
@Log4j2
@RequiredArgsConstructor
public class FeignClientInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String ACCEPT_LANGUAGE = "Accept-Language";
    private static final String TOKEN_TYPE = "Bearer";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        if (!requestTemplate.url().contains("/oauth/token")) {
            log.debug("begin add OAuth2AuthenticationDetails to request feign ");
            requestTemplate.header(AUTHORIZATION_HEADER, getAuthHeader());
        }
        if (requestTemplate.headers().containsKey(ACCEPT_LANGUAGE)) {
            log.debug("The Accept-Language token has been already set");
        } else {
            requestTemplate.header(ACCEPT_LANGUAGE, LocaleContextHolder.getLocale().toLanguageTag());
        }

    }

    private String getAuthHeader() {
        String existsAuthHeader = getExistsAuthHeader();
        if (!Strings.isNullOrEmpty(existsAuthHeader)) {
            return existsAuthHeader;
        } else {
            throw new InternalServerSystemApiException("token JWT not found...");
        }
    }

    private String getExistsAuthHeader() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (Objects.nonNull(context)) {
            Authentication authentication = context.getAuthentication();
            if (authentication != null && authentication.getDetails() instanceof OAuth2AuthenticationDetails) {
                log.debug("Add OAuth2AuthenticationDetails to request feign ");
                OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
                return String.format("%s %s", TOKEN_TYPE, details.getTokenValue());
            }
        } else {
            log.warn("SecurityContext is null");
        }
        return null;
    }
}
