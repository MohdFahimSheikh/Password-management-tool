package com.example.PasswordManagementSys.repository;

import com.example.PasswordManagementSys.model.Account;
import com.example.PasswordManagementSys.model.Group;
import com.example.PasswordManagementSys.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface GroupRepository extends JpaRepository<Group,Long> {
   int countByGroupNameAndUser(String group, User user);
}