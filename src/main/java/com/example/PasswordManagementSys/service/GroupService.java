package com.example.PasswordManagementSys.service;

import com.example.PasswordManagementSys.exceptions.DuplicateAccountException;
import com.example.PasswordManagementSys.exceptions.DuplicateGroupException;
import com.example.PasswordManagementSys.model.Account;
import com.example.PasswordManagementSys.model.Group;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface GroupService {
    void saveGroup(Group group) throws DuplicateGroupException, DuplicateAccountException;
    List<Group> getAllGroups();
    List<Account> getAllAccountsByIdAndUser(Long groupId);
}
