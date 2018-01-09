package com.ajp.yourgrade.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "template")
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "templateId")
    private int id;
    private String name;
    private double gradeDeviation;
    private boolean isCommentNeeded;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Group> groups;

    protected Template(){}

    public Template(String name, double gradeDeviation, boolean isCommentNeeded, User user) {
        this.name = name;
        this.gradeDeviation = gradeDeviation;
        this.isCommentNeeded = isCommentNeeded;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Template{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gradeDeviation=" + gradeDeviation +
                ", isCommentNeeded=" + isCommentNeeded +
                ", user=" + user +
                ", groups=" + groups +
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

    public double getGradeDeviation() {
        return gradeDeviation;
    }

    public void setGradeDeviation(double gradeDeviation) {
        this.gradeDeviation = gradeDeviation;
    }

    public boolean isCommentNeeded() {
        return isCommentNeeded;
    }

    public void setCommentNeeded(boolean commentNeeded) {
        isCommentNeeded = commentNeeded;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }
}
