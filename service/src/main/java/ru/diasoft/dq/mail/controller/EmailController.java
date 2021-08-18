package ru.diasoft.dq.mail.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.diasoft.dq.mail.dto.EmailDto;
import ru.diasoft.dq.mail.service.EmailService;

import javax.mail.MessagingException;

@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping(value = "/api/v1/email/html", produces = "application/json;charset=UTF-8")
    public EmailDto sendHtmlMail(@RequestBody EmailDto emailDto) throws MessagingException {
        return emailService.send(emailDto);
    }

}
