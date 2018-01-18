package com.ajp.yourgrade.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "projectGroup")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "projectGroupId")
    private int id;
    private String name;
    private Date creationDate;
    private Date deadline;
    private double groupGrade;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "templateId")
    private Template template;

    @JsonIgnore
    @OneToMany(mappedBy = "group", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<GroupMember> groupMembers;

    protected Group(){}

    public Group(String name, Date creationDate, Date deadline, double groupGrade, Template template) {
        this.name = name;
        this.creationDate = creationDate;
        this.deadline = deadline;
        this.groupGrade = groupGrade;
        this.template = template;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationData=" + creationDate +
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationData) {
        this.creationDate = creationData;
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

    public Set<GroupMember> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(Set<GroupMember> groupMembers) {
        this.groupMembers = groupMembers;
    }
}
