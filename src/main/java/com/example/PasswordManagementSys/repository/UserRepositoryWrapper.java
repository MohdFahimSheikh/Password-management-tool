package com.example.PasswordManagementSys.repository;

import com.example.PasswordManagementSys.exceptions.DuplicateUserException;
import com.example.PasswordManagementSys.model.User;

public interface UserRepositoryWrapper {
    User saveUser(User user) throws DuplicateUserException;
    User getUserByUserName(String userName) ;
}
