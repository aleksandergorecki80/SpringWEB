package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleEmailService {

  @Autowired
  private MailCreatorService mailCreatorService;

  private final JavaMailSender javaMailSender;

  public void send(final Mail mail, EmailType emailType) {
    log.info("Starting email preparation...");
    try {
      MimeMessagePreparator mailMessage = createMimeMessage(mail, emailType);
      javaMailSender.send(mailMessage);
      log.info("Email has been sent.");
    } catch (MailException e) {
      log.error("Failed to process email sending: " + e.getMessage(), e);
    }
  }

  private MimeMessagePreparator createMimeMessage(final Mail mail, EmailType emailType) {
    return mimeMessage -> {
      MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
      messageHelper.setTo(mail.getMailTo());
      messageHelper.setSubject(mail.getSubject());
      messageHelper.setText(generateEmailBody(mail, emailType), true);
    };
  }

  private String generateEmailBody(Mail mail, EmailType emailType) {
    return switch (emailType) {
      case EmailType.IMMEDIATE -> mailCreatorService.buildTrelloCardEmail(mail.getMessage());
      case EmailType.SCHEDULED -> mailCreatorService.buildTrelloCardEmail(mail.getMessage());
      default -> null;
    };
  }

}