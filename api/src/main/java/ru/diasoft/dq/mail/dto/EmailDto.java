package ru.diasoft.dq.mail.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class EmailDto {
    private String from;
    private String to;
    private String cc;
    private String bcc;
    private String subject;
    private String email;
    private String attachment;
    private String fromDisplayName;
    private String emailLanguage;
    private String displayName;
    private String templateLocation;
    private Map<String, Object> context;
}
