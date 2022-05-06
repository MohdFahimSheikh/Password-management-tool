package com.epam.PasswordManagementSys.service.Impl;

import com.epam.PasswordManagementSys.config.CustomUserDetails;
import com.epam.PasswordManagementSys.exceptions.DuplicateUserException;
import com.epam.PasswordManagementSys.model.User;
import com.epam.PasswordManagementSys.repository.UserRepositoryWrapper;
import com.epam.PasswordManagementSys.service.UserService;
import com.epam.PasswordManagementSys.web.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserService {
    @Autowired
    private UserRepositoryWrapper userRepositoryWrapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServicesImpl() {
    }

    private static final Logger logger = LoggerFactory.getLogger(UserServicesImpl.class);

    @Override
    public void save(UserDto userDto) throws DuplicateUserException {
        logger.info("saving user bean");
        User user = new User(userDto.getUserName(),
                passwordEncoder.encode(userDto.getPassword()));
        userRepositoryWrapper.saveUser(user);
        logger.info("User Bean saved Successfully");
    }

    @Override
    public User getUserByUserName(String userName) {
        logger.info("getting user by name");
        User user = userRepositoryWrapper.getUserByUserName(userName);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = getUserByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid user or Password");
        }
        return new CustomUserDetails(user);
    }

}
