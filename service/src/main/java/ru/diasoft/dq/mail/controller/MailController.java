package ru.diasoft.dq.mail.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.diasoft.dq.mail.dto.MailDto;
import ru.diasoft.dq.mail.service.MailService;

import javax.mail.MessagingException;

@RestController
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @PostMapping(value = "/api/v1/mail", produces = "application/json;charset=UTF-8")
    public MailDto sendHtmlMail(@RequestBody MailDto mailDto) throws MessagingException {
        return mailService.send(mailDto);
    }

}
