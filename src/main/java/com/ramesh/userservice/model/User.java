package com.ramesh.userservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class User {
    @Id
    @Column(name = "profile_id")
    private long profileId;
    private String userName;
    private String displayName;
    private String gitHubUserName;
    private String email;
    private String phoneNo;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE  , CascadeType.PERSIST , CascadeType.REFRESH,CascadeType.DETACH})
    @JoinTable(name = "user_group",
               joinColumns = @JoinColumn(name = "profile_id"),
               inverseJoinColumns = @JoinColumn(name = "group_id"))
    @JsonIgnore
    private List<Group> groups;
    public User() {
    }
    public User(long profileId,String userName, String displayName, String gitHubUserName, String email, String phoneNo) {
        this.profileId = profileId;
        this.userName = userName;
        this.displayName = displayName;
        this.gitHubUserName = gitHubUserName;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    public User(long profileId, String userName, String displayName, String gitHubUserName, String email, String phoneNo, List<Group> groups) {
        this.profileId = profileId;
        this.userName = userName;
        this.displayName = displayName;
        this.gitHubUserName = gitHubUserName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.groups = groups;
    }

    public long getProfileId() {
        return profileId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getGitHubUserName() {
        return gitHubUserName;
    }

    public void setGitHubUserName(String gitHubUserName) {
        this.gitHubUserName = gitHubUserName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }


    public List<Group> getGroups() {
        return groups;
    }

    public void addGroups(Group group) {
        if (groups == null){
            System.out.println("group is create");
            groups = new ArrayList<>();
            System.out.println("group was created");
        }
        groups.add(group);
        System.out.println(groups.get(0));
    }

    @Override
    public String toString() {
        return "User{" +
                "profileId=" + profileId +
                ", userName='" + userName + '\'' +
                ", displayName='" + displayName + '\'' +
                ", gitHubUserName='" + gitHubUserName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", groups=" + groups +
                '}';
    }
}
