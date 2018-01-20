package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.Group;
import com.ajp.yourgrade.model.GroupMember;
import com.ajp.yourgrade.model.Rating;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ApproveServiceImpl implements ApproveService {

    private Group group;

    private GroupService groupService;
    private GroupMemberService groupMemberService;
    private PdfService pdfService = new PdfServiceImpl();

    public ApproveServiceImpl(GroupService groupService, GroupMemberService groupMemberService) throws IOException {
        this.groupMemberService = groupMemberService;
        this.groupService = groupService;
    }

    @Override
    public void approve(int groupId) {
        groupService.getById(groupId).setApproved(true);
    }

    @Override
    public void setFinalGrade(int groupId) {
        Group group = groupService.getById(groupId);
        double total = 0;
        int count = 0;
        for (GroupMember groupMember : group.getGroupMembers()) {
            //Loop again
            for (GroupMember member : group.getGroupMembers()) {
                for (Rating rating : member.getRatings()) {
                    if (rating.getRatedMember().getId() == groupMember.getId()) {
                        count++;
                        total += rating.getGrade();
                    }
                }
            }
            groupMemberService.setFinalGrade(groupMember, total / count);
        }
    }

    @Override
    public void createPdf(int groupId) throws IOException {
        pdfService.createDirectory(groupService.getById(groupId));
        pdfService.print(groupService.getById(groupId));
    }

}
