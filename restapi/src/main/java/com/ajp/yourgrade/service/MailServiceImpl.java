package com.ajp.yourgrade.service;


import com.ajp.yourgrade.model.MailTemplate;
import com.ajp.yourgrade.properties.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private ConfigProperties configProperties;

    public void sendRequest(String to, String name) {
        try {

            //Prepare message template
            MailTemplate template = new MailTemplate();

            //For testing fill the template (wil be retrieved from database)
            template.setSubject("Rating request");
            template.setToken("<b>RANDOM</b>");
            template.setLink(configProperties.getSurveyLink());
            template.setFromName("Grade Systems");

            String htmlMessage = "Dear " + name
                    + ",<br/><br/>You have been asked to rate your group.<br/>"
                    + "Please use this token:<br/><br/>" + template.getToken()
                    + "<br/><br/>Go to <a href='" + template.getLink() + "'a>the rating page</a> to complete your rating."
                    + "<br/><br/>Kind regards,<br/><br/>"
                    + template.getFromName();

            MimeMessage msg = emailSender.createMimeMessage();
            msg.setContent(htmlMessage, "text/html");

            MimeMessageHelper helper = new MimeMessageHelper(msg, false, "utf-8");

            helper.setTo(to);
            helper.setSubject("Rating Request");

            emailSender.send(msg);
        } catch (MessagingException exception) {
            exception.printStackTrace();
        }
    }

}

