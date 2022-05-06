package com.epam.PasswordManagementSys.repository;

import com.epam.PasswordManagementSys.model.Group;
import com.epam.PasswordManagementSys.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface GroupRepository extends JpaRepository<Group,Long> {
   int countByGroupNameAndUser(String group, User user);
   List<Group> findByUser(User user);
   List<Group> findByGroupIdAndUser(int groupId , User user);
}
