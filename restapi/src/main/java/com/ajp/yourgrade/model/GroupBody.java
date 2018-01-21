package com.ajp.yourgrade.model;

import java.util.List;
import java.util.Map;

public class GroupBody {

    private String token;
    private int templateId;
    private String name;
    private String deadline;
    private double groupGrade;
    private List<GroupMemberBody> members;

    public GroupBody() {
    }

    public GroupBody(String token, int templateId, String name, String deadline, double groupGrade, List<GroupMemberBody> members) {
        this.token = token;
        this.templateId = templateId;
        this.name = name;
        this.deadline = deadline;
        this.groupGrade = groupGrade;
        this.members = members;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public double getGroupGrade() {
        return groupGrade;
    }

    public void setGroupGrade(double groupGrade) {
        this.groupGrade = groupGrade;
    }

    public List<GroupMemberBody> getMembers() {
        return members;
    }

    public void setMembers(List<GroupMemberBody> members) {
        this.members = members;
    }
}
