package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.DbService;
import com.crud.tasks.service.EmailType;
import com.crud.tasks.service.SimpleEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

  private static final String SUBJECT = "Tasks: Once a day email";
  private final SimpleEmailService simpleEmailService;
  private final TaskRepository taskRepository;
  private final AdminConfig adminConfig;
  private final DbService dbService;

//  @Scheduled(cron = "0 12 16 * * *")
@Scheduled(fixedDelay = 10000)
  public void sendInformationEmail() {
    long size = taskRepository.count();
  simpleEmailService.send(
        Mail.builder()
                .mailTo(adminConfig.getAdminMail())
                .subject(SUBJECT)
                .message(buildMessage(size))
                .tasks(getFormatedTaskNames())
                .build(),
            EmailType.SCHEDULED
    );
  }


  private String buildMessage(long size) {
    String suffix = size == 1 ? "" : "s";
    return "Currently in database you got: " + size + " task" + suffix;
  }

  private List<String> getFormatedTaskNames() {
    return dbService.getAllTasks()
            .stream()
            .map(Task::getTitle)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
  }
}