package ru.diasoft.dq.mail.service;

import com.google.common.base.Strings;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ru.diasoft.dq.mail.config.MailConfig;
import ru.diasoft.dq.mail.dto.MailDto;
import ru.diasoft.dq.mail.exception.InternalServerSystemApiException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@Log4j2
public class MailServiceImpl implements MailService {

    private static final String ERROR_REQUIRED_PARAMETER_CODE = "error.find.required.parameter";

    private final MailConfig mailConfig;

    private final TemplateService templateService;
    private final MessageBundleService messageBundleService;
    private final JavaMailSender javaMailSender;

    @Override
    public MailDto send(MailDto email) throws MessagingException {
        this.checkRequiredParameters(email);
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
            message,
            MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
            mailConfig.getDefaultEncoding()
        );
        mimeMessageHelper.setFrom(this.getEmailFrom(email));
        mimeMessageHelper.setTo(this.parseInternetAddress(email.getTo()));
        mimeMessageHelper.setCc(this.parseInternetAddress(email.getCc()));
        mimeMessageHelper.setBcc(this.parseInternetAddress(email.getBcc()));
        mimeMessageHelper.setSubject(email.getSubject());
        mimeMessageHelper.setText(this.getEmailContent(email), true);
        javaMailSender.send(message);
        return email;
    }

    private void checkRequiredParameters(MailDto email) {
        if (Strings.isNullOrEmpty(email.getSubject())) {
            final String msg = messageBundleService.getMessage(ERROR_REQUIRED_PARAMETER_CODE, "subject");
            throw new InternalServerSystemApiException(msg);
        }
        if (Strings.isNullOrEmpty(email.getTo())) {
            final String msg = messageBundleService.getMessage(ERROR_REQUIRED_PARAMETER_CODE, "to");
            throw new InternalServerSystemApiException(msg);
        }
        if (Strings.isNullOrEmpty(email.getTemplateLocation())) {
            final String msg = messageBundleService.getMessage(ERROR_REQUIRED_PARAMETER_CODE, "templateLocation");
            throw new InternalServerSystemApiException(msg);
        }
    }

    private InternetAddress[] parseInternetAddress(String recipients) throws AddressException {
        return Strings.isNullOrEmpty(recipients) ? new InternetAddress[] {} : InternetAddress.parse(recipients);
    }

    private String getEmailFrom(MailDto email) {
        return Strings.isNullOrEmpty(email.getFrom()) ? mailConfig.getUsername() : email.getFrom();
    }

    private String getEmailContent(MailDto email) {
        String emailContent;
        try {
            emailContent = templateService.createHtml(email.getTemplateLocation(), email.getContext());
        } catch (IOException | TemplateException e) {
            emailContent = e.getMessage();
            log.error(e);
        }
        return emailContent;
    }

}
