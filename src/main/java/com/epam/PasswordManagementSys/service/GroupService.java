package com.epam.PasswordManagementSys.service;

import com.epam.PasswordManagementSys.exceptions.DuplicateAccountException;
import com.epam.PasswordManagementSys.exceptions.DuplicateGroupException;
import com.epam.PasswordManagementSys.model.Account;
import com.epam.PasswordManagementSys.model.Group;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public interface GroupService {
    void saveGroup(Group group) throws DuplicateGroupException, DuplicateAccountException;

    List<Group> getAllGroups();

    List<Account> getAllAccountsByIdAndUser(Long groupId);

    Group getGroupById(Long id);

    void updateGroupName(Group group1);

    void deleteGroupById(Long id);
}
