package ru.diasoft.dq.mail.service;

import freemarker.template.TemplateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import ru.diasoft.dq.mail.config.MailConfig;
import ru.diasoft.dq.mail.dto.MailDto;
import ru.diasoft.dq.mail.exception.ApiException;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class MailServiceImplTest {

    @Mock private TemplateService templateService;
    @Mock private MessageBundleService messageBundleService;
    @Mock private JavaMailSender javaMailSender;
    @Mock private MailConfig mailConfig;

    @InjectMocks
    private MailServiceImpl emailService;

    @Test
    @DisplayName("формирует письмо и отправляет его")
    void sendMail() throws MessagingException, IOException, TemplateException {
        when(mailConfig.getDefaultEncoding())
                .thenReturn("UTF-8");
        when(mailConfig.getUsername())
                .thenReturn("iworking.ru@mail.ru");

        when(javaMailSender.createMimeMessage())
                .thenReturn(new MimeMessage((Session)null));

        when(templateService.createHtml(anyString(), any()))
                .thenReturn("<h3>Im testing send a HTML email</h3>");

        MailDto mailDto = new MailDto();
        mailDto.setSubject("Заголовок");
        mailDto.setTemplateLocation("mail1.ftl");
        mailDto.setTo("zemtsov1997@yandex.ru");

        Map<String, Object> props = new HashMap<>();
        props.put("test", "Hello world!");
        mailDto.setContext(props);

        Assertions.assertNotNull(emailService.send(mailDto));
    }

    @Test
    @DisplayName("выбрасывает ошибку при пустом Subject")
    void blockedMailWithSubjectNull() {
        when(messageBundleService.getMessage(anyString(), any()))
            .thenReturn("тестовая ошибка");

        MailDto mailDto = new MailDto();
        mailDto.setSubject(null);
        mailDto.setTemplateLocation("mail1.ftl");
        mailDto.setTo("zemtsov1997@yandex.ru");

        Map<String, Object> props = new HashMap<>();
        props.put("test", "Hello world!");
        mailDto.setContext(props);

        Assertions.assertThrows(ApiException.class, () -> emailService.send(mailDto));
    }

    @Test
    @DisplayName("выбрасывает ошибку при пустом To")
    void blockedMailWithToNull() {
        when(messageBundleService.getMessage(anyString(), any()))
                .thenReturn("тестовая ошибка");

        MailDto mailDto = new MailDto();
        mailDto.setSubject("Заголовок");
        mailDto.setTemplateLocation("mail1.ftl");
        mailDto.setTo(null);

        Map<String, Object> props = new HashMap<>();
        props.put("test", "Hello world!");
        mailDto.setContext(props);

        Assertions.assertThrows(ApiException.class, () -> emailService.send(mailDto));
    }

}