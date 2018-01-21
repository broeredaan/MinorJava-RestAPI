package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.GroupMember;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface ApproveService {
    public void approve(int groupId);
    public void setFinalGrade(int groupId);
    public void createPdf(int groupId) throws IOException;
}
