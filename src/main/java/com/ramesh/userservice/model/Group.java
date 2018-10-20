package com.ramesh.userservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ugroup")
public class Group {
    @Id
    @Column(name = "group_id")
    private long groupId;
    private String groupName;
    private String groupDescription;
    private long ownerProfileId;
    public String type;


    @ManyToMany(fetch = FetchType.LAZY,
    cascade = {CascadeType.MERGE  , CascadeType.PERSIST })
    @JoinTable(name = "user_group",
               joinColumns = @JoinColumn(name = "group_id"),
               inverseJoinColumns = @JoinColumn(name = "profile_id"))
    //@JsonManagedReference
    private List<User> users;

    public Group() {
    }

    public Group(long groupId, String groupName, String groupDescription, long ownerProfileId, String type) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupDescription = groupDescription;
        this.ownerProfileId = ownerProfileId;
        this.type = type;
    }

    public Group(long groupId, String groupName, String groupDescription, long ownerProfileId, String type, List<User> users) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupDescription = groupDescription;
        this.ownerProfileId = ownerProfileId;
        this.type = type;
        this.users = users;
    }

    public long getGroupId() {
        return groupId;
    }


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public long getOwnerProfileId() {
        return ownerProfileId;
    }

    public void setOwnerProfileId(long ownerProfileId) {
        this.ownerProfileId = ownerProfileId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public List<User> getUsers() {
        return users;
    }

    public void addUsers(User user) {
        if (users == null){
            users = new ArrayList<>();
        }
        users.add(user);
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", groupDescription='" + groupDescription + '\'' +
                ", ownerProfileId=" + ownerProfileId +
                ", type='" + type + '\'' +
                ", users=" + users +
                '}';
    }
}
