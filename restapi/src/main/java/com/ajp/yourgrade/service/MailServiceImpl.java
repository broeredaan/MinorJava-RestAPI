package com.ajp.yourgrade.service;


import com.ajp.yourgrade.model.MailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailServiceImpl implements MailService {

    @Autowired
    public JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
        } catch (MailException exception) {
            exception.printStackTrace();
        }

    }

    public void sendRequest(String to, String name) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);

            //Prepare the message template
            MailTemplate template = new MailTemplate();

            //For testing fill the template (wil be retrieved from database)
            template.setSubject("Rating request");
            template.setToken("<b>RANDOM</b>");
            template.setLink("<a href='https://google.com' /a>");
            template.setFromName("Teacher");

            message.setSubject(template.getSubject());

            //Prepare message using template data
            message.setText("Dear " + name
                    + "You have been asked to rate your group."
                    + "Please use this token: " + template.getToken()
                    + " and go to " + template.getLink()
                    + "Kind regards,"
                    + template.getFromName());

            emailSender.send(message);
        } catch (MailException exception) {
            exception.printStackTrace();
        }
    }

}

