package com.ramesh.userservice.service.Impl;

import com.ramesh.userservice.model.Group;
import com.ramesh.userservice.model.User;
import com.ramesh.userservice.repository.GroupRepository;
import com.ramesh.userservice.service.GroupService;
import com.ramesh.userservice.service.UserService;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ramesh.userservice.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    GroupService groupService;
    @Override
    @Transactional
    public User getUser(long profileId) {

        User user =  userRepository.findByProfileId(profileId);
        if(user == null){
          log.warn("user profile is not available");
          return null;
        }
        else {
            return user;
        }
    }

    @Override
    @Transactional
    public void save(User user) {
         userRepository.save(user);
    }

    @Override
    @Transactional
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User updateUser(User user1) {
        return userRepository.save(user1);
    }

    @Override
    @Transactional
    public User createUser(User user) {
        User user1 = userRepository.findByProfileId(user.getProfileId());
        if (user1 == null){
            return userRepository.save(user);
        }
        else {
            log.warn("this profile is already exist");
        }
        return null;

    }


    @Transactional
    public void deteleUser(long profileId) {
        User user = userRepository.getOne(profileId);
        if (user!=null){
            userRepository.delete(user);
        }
        else {
            log.warn("this user is not available");
        }
    }

    @Override
    @Transactional
    public void addStudentAndCourse(User user, Group group) {

        userRepository.save(user);
        groupService.save(group);

        user.addGroups(group);
        group.addUsers(user);

        userRepository.save(user);
        groupService.save(group);



    }
}
