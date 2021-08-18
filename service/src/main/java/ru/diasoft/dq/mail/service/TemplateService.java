package ru.diasoft.dq.mail.service;

import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.Map;

public interface TemplateService {

    String createHtml(String fileTemplate, Map<String, Object> props) throws
            IOException, TemplateException;

}
