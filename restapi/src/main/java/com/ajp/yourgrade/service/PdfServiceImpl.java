package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.Group;
import com.ajp.yourgrade.model.GroupMember;
import com.ajp.yourgrade.model.Pdf;
import com.ajp.yourgrade.model.Rating;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class PdfServiceImpl implements PdfService {
    private Pdf pdf;

    public PdfServiceImpl() throws IOException {
        pdf = new Pdf();
    }

    @Override
    public void print(Group group) throws IOException {
        String text;
        pdf.print("The following Grades have been filled in", 24);
        pdf.print("", 24);
        for (GroupMember member : group.getGroupMembers()) {
            pdf.print("Member " + member.getName() + "Is given the following rates", 20);
            for (GroupMember ratedMember : group.getGroupMembers()) {
                Set<Rating> ratings = ratedMember.getRatings();
                Iterator iterator = ratings.iterator();
                while (iterator.hasNext()) {
                    Rating rating = (Rating) iterator.next();
                    if (rating.getRatedMember().getId() == member.getId()) {
                        pdf.print("", 12);
                        pdf.print("Member " + rating.getGroupMember().getName() + " rated a " + rating.getGrade(), 12);
                        pdf.print("Given comment:", 15);
                        pdf.print(rating.getComment(), 12);
                        pdf.print("", 12);
                    }
                }
            }
            text = "Final grade:  " + member.getName() + " " + member.getEmail() + " " + member.getFinalGrade();
            pdf.print(text, 13);
            pdf.addPage();
        }
        pdf.saveFile(group.getId());
    }

//    @Override
//    public void createDirectory(Group group) {
//        new File(String.valueOf(group.getId())).mkdir();
//    }

}
