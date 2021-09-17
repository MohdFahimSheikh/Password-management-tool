package com.example.PasswordManagementSys.repository;

import com.example.PasswordManagementSys.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByUserName(String userName);
    int countByUserName(String userName);
}
