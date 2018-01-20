package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.Group;
import com.ajp.yourgrade.model.GroupMember;
import com.ajp.yourgrade.model.Pdf;
import com.ajp.yourgrade.model.Rating;

import java.io.File;
import java.io.IOException;

public class PdfServiceImpl implements PdfService {
    private Pdf pdf;
    public PdfServiceImpl() throws IOException {
      pdf = new Pdf();
    }

    @Override
    public void print(Group group) throws IOException {
        String text;
        for (GroupMember member:group.getGroupMembers()){
            text = member.getName() + " " + member.getEmail() + " " + member.getFinalGrade();
            pdf.print(text);
        }
        pdf.saveFile(group.getId());
    }

    @Override
    public void createDirectory(Group group) {
        new File(String.valueOf(group.getId())).mkdir();
    }

}
