package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("preview_message", "New card created");
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost/crud/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye_message", getGoodbyeMessage());
        context.setVariable("company_details", getCompanyDetails());
        context.setVariable("show_button", true);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildTrelloTasksEmail(Mail mail) {
        Optional<List<String>> tasks = mail.getTasks();
        System.out.println(mail.getTasks().stream().findAny().isPresent());
        System.out.println(tasks);
        Context context = new Context();
        context.setVariable("tasks_user_name", adminConfig.getAdminName());
        context.setVariable("tasks_message", mail.getMessage());
        context.setVariable("show_details", mail.getTasks().stream().findAny().isPresent());
        context.setVariable("tasks_title_list", tasks.orElseGet(Collections::emptyList));
        context.setVariable("tasks_goodbye_message", getGoodbyeMessage());
        context.setVariable("tasks_mail_company_details", getCompanyDetails());


        return templateEngine.process("mail/created-trello-task-mail", context);
    }

    private String getGoodbyeMessage() {
        return "Best regards, " + adminConfig.getInfoCompanyName() + " team.";
    }

    private String getCompanyDetails() {
        return adminConfig.getInfoCompanyName() + " \u25CF " + adminConfig.getInfoCompanyEmail() + " \u25CF " + adminConfig.getInfoCompanyPhone();
    }

}
