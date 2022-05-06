package com.epam.PasswordManagementSys.repository;

import com.epam.PasswordManagementSys.exceptions.DuplicateUserException;
import com.epam.PasswordManagementSys.model.User;

public interface UserRepositoryWrapper {
    User saveUser(User user) throws DuplicateUserException;
    User getUserByUserName(String userName) ;
}
