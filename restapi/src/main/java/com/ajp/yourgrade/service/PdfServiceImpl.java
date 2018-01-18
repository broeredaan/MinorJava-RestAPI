package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.Group;
import com.ajp.yourgrade.model.GroupMember;
import com.ajp.yourgrade.model.Pdf;
import com.ajp.yourgrade.model.Rating;

import java.io.File;
import java.io.IOException;

public class PdfServiceImpl implements PdfService {
    private Group group;
    private Pdf pdf;
    public PdfServiceImpl(Group group) throws IOException {
      pdf = new Pdf();
      this.group = group;
    }

    @Override
    public double calculateGrade(GroupMember groupMember) {
        double total = 0;
        int count  = 0;
        for (GroupMember member:group.getGroupMembers()){
            for(Rating rating:member.getRatings()){
                if(rating.getRatedMember().getId() == groupMember.getId()){
                    count ++;
                    total += rating.getGrade();
                }
            }
        }
        return total / count;
    }

    @Override
    public void print() throws IOException {
        String text;
        for (GroupMember member:group.getGroupMembers()){
            text = member.getName() + " " + member.getEmail() + " " + calculateGrade(member);
            pdf.print(text);
        }
        pdf.saveFile(group.getId());
    }

    @Override
    public void createDirectory() {
        new File(String.valueOf(group.getId())).mkdir();
    }

}
