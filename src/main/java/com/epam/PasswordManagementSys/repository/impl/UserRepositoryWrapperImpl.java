package com.epam.PasswordManagementSys.repository.impl;

import com.epam.PasswordManagementSys.exceptions.DuplicateUserException;
import com.epam.PasswordManagementSys.model.User;
import com.epam.PasswordManagementSys.repository.UserRepository;
import com.epam.PasswordManagementSys.repository.UserRepositoryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryWrapperImpl implements UserRepositoryWrapper {
    @Autowired
    UserRepository userRepository;

    @Override
    public User saveUser(User user) throws DuplicateUserException {
        if (userRepository.countByUserName(user.getUserName()) > 0) {
            throw new DuplicateUserException("User with this UserName already Exist");
        }
        return userRepository.save(user);
    }

    @Override
    public User getUserByUserName(String userName) {
        List<User> user = userRepository.findByUserName(userName);
        if (user.size() == 0) {
            throw new UsernameNotFoundException("Invalid UserName / Password");
        }
        return user.get(0);
    }
}
