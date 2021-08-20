package ru.diasoft.dq.mail.service;

import ru.diasoft.dq.mail.dto.MailDto;

import javax.mail.MessagingException;

public interface MailService {

    MailDto send(MailDto email) throws MessagingException;

}
