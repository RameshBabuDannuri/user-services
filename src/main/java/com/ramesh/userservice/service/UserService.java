package com.ramesh.userservice.service;

import com.ramesh.userservice.model.Group;
import com.ramesh.userservice.model.User;

import java.util.List;

public interface UserService  {
    User getUser(long profileId);
    void save(User user);

    List<User> getAll();

    User updateUser(User user1);

    User createUser(User user);

    void deteleUser(long profileId);

    void addStudentAndCourse(User user1, Group group1);
}
