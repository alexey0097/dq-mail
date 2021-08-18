package ru.diasoft.dq.mail.service;

import freemarker.template.TemplateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
@ContextConfiguration(classes = {
        TemplateServiceImpl.class,
        FreeMarkerAutoConfiguration.class
})
@TestPropertySource(properties = {
        "spring.freemarker.template-loader-path=classpath:/templates"
})
class TemplateServiceTest {

    @Autowired
    private TemplateService templateService;

    @Test
    @DisplayName("создает HTML шаблон и заполняет их параметрами")
    void createHtml() throws IOException, TemplateException {
        Map<String, Object> props = new HashMap<>();
        props.put("test", "Hello world!");
        String html = templateService.createHtml("mail1.ftl", props);
        System.out.println(html);
        Assertions.assertNotNull(html);
    }
}