package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.Group;

import java.io.IOException;

public interface PdfService {
//    double calculateGrade(GroupMember groupMember);
    void print(Group group) throws IOException;
//    void createDirectory(Group group);
}
