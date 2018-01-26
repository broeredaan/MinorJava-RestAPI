package com.ajp.yourgrade.service;


//import com.ajp.yourgrade.model.MailTemplate;
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

    @Override
    public void sendRequest(String to, String name, String token, String link, String fromName) {
        try {

            String htmlMessage = "Dear " + name
                    + ",<br/><br/>You have been asked to rate your group.<br/>"
                    + "Your token is:<br/><br/><b>" + token + "</b>"
                    + "<br/><br/>Go to <a href='" + link + token + "'a>the rating page</a> to complete your rating."
                    + "<br/><br/>Kind regards,<br/><br/>"
                    + fromName;

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

    @Override
    public void sendFinished(String to, String name , String groupName) {
        try {

            String htmlMessage = "Dear " + name
                    + ",<br/><br/> Group<i> " + groupName + "</i><br/>"
                    + "Has submitted their ratings!<br/><br/>"
                    + "Please login for further information and approval.";

            MimeMessage msg = emailSender.createMimeMessage();
            msg.setContent(htmlMessage, "text/html");

            MimeMessageHelper helper = new MimeMessageHelper(msg, false, "utf-8");

            helper.setTo(to);
            helper.setSubject("Group: " + groupName+ " Status: finished");

            emailSender.send(msg);


        } catch (MessagingException exception) {
            exception.printStackTrace();
        }
    }

}

