package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.GroupMember;

import java.io.IOException;

public interface PdfService {
    double calculateGrade(GroupMember groupMember);
    void print() throws IOException;
    void createDirectory();
}
