package com.ramesh.userservice.service.Impl;

import com.ramesh.userservice.model.Group;
import com.ramesh.userservice.repository.GroupRepository;
import com.ramesh.userservice.service.GroupService;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class GroupServiceimpl implements GroupService {

    @Autowired
    public GroupRepository groupRepository;
    @Override
    @Transactional
    public Group save(Group group) {
        return  groupRepository.save(group);
    }

    @Override
    @Transactional
    @Fetch(FetchMode.JOIN)
    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group getGroup(long groupId) {
        return groupRepository.findByGroupId(groupId);
    }

    @Override
    public Group getGroupByName(String groupName) {
        return groupRepository.findByGroupName(groupName);
    }
}
