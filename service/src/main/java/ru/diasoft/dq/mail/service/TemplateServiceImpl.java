package ru.diasoft.dq.mail.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TemplateServiceImpl implements TemplateService {

    private final Configuration configuration;

    @Override
    public String createHtml(String fileTemplate, Map<String, Object> props) throws IOException, TemplateException {
        try(Writer writer = new StringWriter()) {
            Template template = configuration.getTemplate(fileTemplate);
            template.process(props, writer);
            return writer.toString();
        }
    }

}
