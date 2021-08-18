package ru.diasoft.dq.mail.service;

import freemarker.template.TemplateException;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import ru.diasoft.dq.mail.config.EmailConfig;
import ru.diasoft.dq.mail.dto.EmailDto;
import ru.diasoft.dq.mail.exception.ApiException;
import ru.diasoft.dq.mail.exception.InternalServerSystemApiException;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeMessage;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class EmailServiceImplTest {

    @Mock private TemplateService templateService;
    @Mock private MessageBundleService messageBundleService;
    @Mock private JavaMailSender javaMailSender;
    @Mock private EmailConfig emailConfig;

    @InjectMocks
    private EmailServiceImpl emailService;

    @Test
    @DisplayName("формирует письмо и отправляет его")
    void sendMail() throws MessagingException, IOException, TemplateException {
        when(emailConfig.getDefaultEncoding())
                .thenReturn("UTF-8");
        when(emailConfig.getUsername())
                .thenReturn("iworking.ru@mail.ru");

        when(javaMailSender.createMimeMessage())
                .thenReturn(new MimeMessage((Session)null));

        when(templateService.createHtml(anyString(), any()))
                .thenReturn("<h3>Im testing send a HTML email</h3>");

        EmailDto emailDto = new EmailDto();
        emailDto.setSubject("Заголовок");
        emailDto.setTemplateLocation("mail1.ftl");
        emailDto.setTo("zemtsov1997@yandex.ru");

        Map<String, Object> props = new HashMap<>();
        props.put("test", "Hello world!");
        emailDto.setContext(props);

        Assertions.assertNotNull(emailService.send(emailDto));
    }

    @Test
    @DisplayName("выбрасывает ошибку при пустом Subject")
    void blockedMailWithSubjectNull() {
        when(messageBundleService.getMessage(anyString(), any()))
            .thenReturn("тестовая ошибка");

        EmailDto emailDto = new EmailDto();
        emailDto.setSubject(null);
        emailDto.setTemplateLocation("mail1.ftl");
        emailDto.setTo("zemtsov1997@yandex.ru");

        Map<String, Object> props = new HashMap<>();
        props.put("test", "Hello world!");
        emailDto.setContext(props);

        Assertions.assertThrows(ApiException.class, () -> emailService.send(emailDto));
    }

    @Test
    @DisplayName("выбрасывает ошибку при пустом To")
    void blockedMailWithToNull() {
        when(messageBundleService.getMessage(anyString(), any()))
                .thenReturn("тестовая ошибка");

        EmailDto emailDto = new EmailDto();
        emailDto.setSubject("Заголовок");
        emailDto.setTemplateLocation("mail1.ftl");
        emailDto.setTo(null);

        Map<String, Object> props = new HashMap<>();
        props.put("test", "Hello world!");
        emailDto.setContext(props);

        Assertions.assertThrows(ApiException.class, () -> emailService.send(emailDto));
    }

}