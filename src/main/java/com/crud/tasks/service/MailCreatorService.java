package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("preview_message", "New card created");
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost/crud/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye_message", "Best regards, " + adminConfig.getInfoCompanyName() + " team.");
        context.setVariable("company_details", adminConfig.getInfoCompanyName() + " \u25CF " + adminConfig.getInfoCompanyEmail() + " \u25CF " + adminConfig.getInfoCompanyPhone());
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

}
