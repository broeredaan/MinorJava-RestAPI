package com.ajp.yourgrade.service;

public interface MailService {

    void sendRequest(String to, String name, String token, String link, String fromName);
    void sendFinished(String to, String name , String groupName);

}
