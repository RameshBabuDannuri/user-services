package com.ramesh.userservice.repository;

import com.ramesh.userservice.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group , Long> {
    Group findByGroupId(long id);

    Group findByGroupName(String groupName);
}
