package ru.diasoft.dq.mail.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class MailConfig {

    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.default-encoding}")
    private String defaultEncoding;

}
