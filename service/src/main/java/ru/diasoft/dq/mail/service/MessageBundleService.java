package ru.diasoft.dq.mail.service;

public interface MessageBundleService {
  String getMessage(String messageCode);
  String getMessage(String messageCode, Object... params);
}
