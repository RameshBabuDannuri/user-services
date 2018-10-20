package com.ramesh.userservice.controller;

import com.ramesh.userservice.exception.GroupNotFoundException;
import com.ramesh.userservice.model.Group;
import com.ramesh.userservice.model.User;
import com.ramesh.userservice.service.GroupService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/groups")
    public List<Group> getGroups(){
        return groupService.getAll();
    }

    @GetMapping("/groupById/{groupId}")
    public ResponseEntity<List<User>> getGroup(@PathVariable long groupId)throws GroupNotFoundException {
       Group g = groupService.getGroup(groupId);
       if (g==null){
           throw new GroupNotFoundException("group not found with id "+groupId);
       }

        return new ResponseEntity<List<User>>(g.getUsers() ,HttpStatus.OK);

    }
    @GetMapping("/groupByName/{groupName}")
    public ResponseEntity<List<User>> getGroup(@PathVariable String groupName)throws GroupNotFoundException {
        Group g = groupService.getGroupByName(groupName);
        if (g==null){
            throw new GroupNotFoundException("group not found with Name "+groupName);
        }

        return new ResponseEntity<List<User>>(g.getUsers() ,HttpStatus.OK);

    }

    @ExceptionHandler(GroupNotFoundException.class)
    public void handleUserNotFoundException(GroupNotFoundException ex, HttpServletResponse response) throws IOException, IOException {
        response.sendError(HttpStatus.NOT_FOUND.value() , ex.getMessage());
    }
}
