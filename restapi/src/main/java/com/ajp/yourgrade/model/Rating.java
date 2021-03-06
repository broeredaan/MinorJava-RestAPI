package com.ajp.yourgrade.model;

import com.ajp.yourgrade.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Entity
@Table(name = "Rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ratingId")

    @JsonView(View.Public.class)
    private int id;

    @JsonView(View.Public.class)
    private double grade;

    @JsonView(View.Public.class)
    private String comment;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "groupMemberId")
    private GroupMember groupMember;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ratedMemberId")
    @JsonView(View.Public.class)
    private GroupMember ratedMember;

    protected Rating(){}

    public Rating(double grade, String comment, GroupMember groupMember, GroupMember ratedMember) {
        this.grade = grade;
        this.comment = comment;
        this.groupMember = groupMember;
        this.ratedMember = ratedMember;
    }

    @Override
    public String toString() {
        return "RatingService{" +
                "id=" + id +
                ", grade=" + grade +
                ", comment='" + comment + '\'' +
                ", groupMember=" + groupMember +
                ", ratedMember=" + ratedMember +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public GroupMember getGroupMember() {
        return groupMember;
    }

    public void setGroupMember(GroupMember groupMember) {
        this.groupMember = groupMember;
    }

    public GroupMember getRatedMember() {
        return ratedMember;
    }

    public void setRatedMember(GroupMember ratedMember) {
        this.ratedMember = ratedMember;
    }
}
