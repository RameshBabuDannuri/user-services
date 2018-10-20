package com.ramesh.userservice.controller;

import com.ramesh.userservice.exception.UserNotCreateException;
import com.ramesh.userservice.exception.UserNotFoundException;
import com.ramesh.userservice.model.Group;
import com.ramesh.userservice.model.User;
import com.ramesh.userservice.service.GroupService;
import com.ramesh.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<User> getUser(@PathVariable Long profileId) throws UserNotFoundException{
        User user = userService.getUser(profileId);
        if (user == null){
            throw new UserNotFoundException("user with id "+profileId+ " not found");
        }
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody  User user)throws UserNotCreateException{
        User u =  userService.createUser(user);
        if (u==null){
            throw  new UserNotCreateException("user is not created with "+user.getProfileId());
        }
        return new ResponseEntity<User>(u , HttpStatus.OK);
    }

    @PutMapping("/user/{profileId}")
    public ResponseEntity<User> updateUser(@RequestBody User user , @PathVariable Long profileId)
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

            User u = userService.updateUser(user1);
            return new ResponseEntity<User>(u,HttpStatus.OK);
        }else {
             User u =  userService.createUser(user);
             return new ResponseEntity<User>(u,HttpStatus.OK);
        }
    }
    @DeleteMapping("user/{profileId}")
    public ResponseEntity<User> deleteUser(@PathVariable long profileId) throws UserNotFoundException{

        User user = userService.getUser(profileId);

            if (user == null){
             throw new UserNotFoundException("user with id "+profileId+ " not found");
           }
         userService.deteleUser(profileId);
        return new ResponseEntity<User>(HttpStatus.OK);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public void handleUserNotFoundException(UserNotFoundException ex, HttpServletResponse response) throws IOException{
        response.sendError(HttpStatus.NOT_FOUND.value() , ex.getMessage());
    }
    @ExceptionHandler(UserNotCreateException.class)
    public void handleUserNotCreateException(UserNotCreateException ex , HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
    }



}
