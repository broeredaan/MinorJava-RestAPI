package com.ajp.yourgrade.model;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "groupId")
    private int id;
    private String name;
    private Date creationData;
    private Date deadline;
    private double groupGrade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "templateId")
    private Template template;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<GroupMember> groupMembers;

    protected Group(){}

    public Group(String name, Date creationData, Date deadline, double groupGrade, Template template) {
        this.name = name;
        this.creationData = creationData;
        this.deadline = deadline;
        this.groupGrade = groupGrade;
        this.template = template;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationData=" + creationData +
                ", deadline=" + deadline +
                ", groupGrade=" + groupGrade +
                ", template=" + template +
                ", groupMembers=" + groupMembers +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationData() {
        return creationData;
    }

    public void setCreationData(Date creationData) {
        this.creationData = creationData;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public double getGroupGrade() {
        return groupGrade;
    }

    public void setGroupGrade(double groupGrade) {
        this.groupGrade = groupGrade;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }
}
