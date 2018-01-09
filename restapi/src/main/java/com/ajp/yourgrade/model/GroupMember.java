package com.ajp.yourgrade.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "groupMember")
public class GroupMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "groupMemberId")
    private int id;
    private String name;
    private String email;
    private String token;
    private boolean hasSubmitted;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "groupId")
    private Group group;

    @OneToMany(mappedBy = "groupMember", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Rating> ratings;

    protected GroupMember(){}

    public GroupMember(String name, String email, String token, boolean hasSubmitted, Group group) {
        this.name = name;
        this.email = email;
        this.token = token;
        this.hasSubmitted = hasSubmitted;
        this.group = group;
    }

    @Override
    public String toString() {
        return "GroupMember{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                ", hasSubmitted=" + hasSubmitted +
                ", group=" + group +
                ", ratings=" + ratings +
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isHasSubmitted() {
        return hasSubmitted;
    }

    public void setHasSubmitted(boolean hasSubmitted) {
        this.hasSubmitted = hasSubmitted;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Set<com.ajp.yourgrade.model.Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<com.ajp.yourgrade.model.Rating> ratings) {
        this.ratings = ratings;
    }
}
