package com.ramesh.userservice.service;

import com.ramesh.userservice.model.Group;

import java.util.List;

public interface GroupService {
    Group save(Group group);
    List<Group> getAll();
}
