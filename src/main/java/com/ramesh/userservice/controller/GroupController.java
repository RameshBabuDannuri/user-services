package com.ramesh.userservice.controller;

import com.ramesh.userservice.model.Group;
import com.ramesh.userservice.service.GroupService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/groups")
    public List<Group> getGroups(){
        return groupService.getAll();
    }
}
