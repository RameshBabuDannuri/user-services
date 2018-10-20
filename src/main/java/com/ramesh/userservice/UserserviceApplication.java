package com.ramesh.userservice;

import com.ramesh.userservice.model.Group;
import com.ramesh.userservice.model.User;
import com.ramesh.userservice.service.GroupService;
import com.ramesh.userservice.service.Impl.UserServiceImpl;
import com.ramesh.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@Transactional
public class UserserviceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(UserserviceApplication.class, args);
    }

    private static final Logger log = LoggerFactory.getLogger(UserserviceApplication.class);
    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Autowired
    EntityManager entityManager;

    @Override
    public void run(String... args) throws Exception {

        User user1 = new User(1, "Ramesh", "Ramesh1", "Ramesh1", "ramesh1.dannuri@gmail.com", "9581810591");
        User user2 = new User(2, "vinod", "vinod", "vinod", "vinod.dannuri@gmail.com", "9581810592");
        User user3 = new User(3, "sai", "sai", "sai", "sai.dannuri@gmail.com", "9581810591");
        User user4 = new User(4, "shyam", "shyam", "shyam", "ramesh2.dannuri@gmail.com", "9581810592");

        Group mca = new Group(1, "mca", "Study purpose", 1, "admin");
        Group jntu = new Group(2, "jntu", "Study purpose", 2, "admin");
        Group snist = new Group(3, "snist", "Study purpose", 3, "admin");


        mca.addUsers(user1);
        mca.addUsers(user2);
        mca.addUsers(user3);

        entityManager.merge(mca);

        jntu.addUsers(user3);
        jntu.addUsers(user4);

        entityManager.merge(jntu);

        snist.addUsers(user1);
        snist.addUsers(user2);
        snist.addUsers(user3);
        snist.addUsers(user4);

        entityManager.merge(snist);


    }
}
