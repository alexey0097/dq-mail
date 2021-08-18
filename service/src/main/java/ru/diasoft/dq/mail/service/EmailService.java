package ru.diasoft.dq.mail.service;

import ru.diasoft.dq.mail.dto.EmailDto;

import javax.mail.MessagingException;

public interface EmailService {

    EmailDto send(EmailDto email) throws MessagingException;

}
