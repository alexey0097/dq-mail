package ru.diasoft.dq.mail;

import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.diasoft.dq.mail.service.TemplateService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TemplateService templateService;

    @GetMapping("/test")
    public String test(@RequestParam(name = "test") String test) throws IOException, TemplateException {
        Map<String, Object> props = new HashMap<>();
        props.put("test", test);
        return templateService.createHtml("mail1.ftl", props);
    }

}
