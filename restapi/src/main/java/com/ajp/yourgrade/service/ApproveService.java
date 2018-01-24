package com.ajp.yourgrade.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface ApproveService {
    void approve(int groupId);
    void setFinalGrade(int groupId);
    void createPdf(int groupId) throws IOException;
}
