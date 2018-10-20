package com.ramesh.userservice.controller;

import com.ramesh.userservice.exception.UserNotFoundException;
import com.ramesh.userservice.model.Group;
import com.ramesh.userservice.model.User;
import com.ramesh.userservice.service.GroupService;
import com.ramesh.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getAll();
    }

    @GetMapping("/user/{profileId}")
    public User getUser(@PathVariable Long profileId) throws UserNotFoundException{
        User user = userService.getUser(profileId);
        if (user == null){
            throw new UserNotFoundException("user with id "+profileId+ " not found");
        }

        return user;
    }

    @PostMapping("/user")
    public User createUser(@RequestBody  User user){
        return userService.createUser(user);
    }

    @PutMapping("/user/{profileId}")
    public User updateUser(@RequestBody User user , @PathVariable Long profileId)
            throws UserNotFoundException{
        User user1 = userService.getUser(profileId);
        if (user1==null){
            throw new UserNotFoundException("user with id "+profileId+ " not found");
        }
       else if (user1!=null){

            user1.setUserName(user.getUserName());
            user1.setDisplayName(user.getDisplayName());
            user1.setEmail(user.getEmail());
            user1.setGitHubUserName(user.getGitHubUserName());
            user1.setPhoneNo(user.getPhoneNo());

            return userService.updateUser(user1);
        }else {
             return userService.createUser(user);
        }
    }
    @DeleteMapping("user/{profileId}")
    public void deleteUser(@PathVariable long profileId){

         User user = userService.getUser(profileId);
         if (user == null){
             throw new UserNotFoundException("user with id "+profileId+ " not found");
         }
        userService.deteleUser(profileId);
    }

    @GetMapping("/group/{profileId}")
    public List<List<Group>> getGroup(@PathVariable long profileId){
        User user = userService.getUser(profileId);
        if (user!=null){
            List<List<Group>> groups = new ArrayList<>();
            groups.add(user.getGroups());
            return groups;
        }
        return null;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public void handleUserNotFoundException(UserNotFoundException ex, HttpServletResponse response) throws IOException{
        response.sendError(HttpStatus.NOT_FOUND.value() , ex.getMessage());
    }



}
