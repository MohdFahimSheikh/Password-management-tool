package com.example.PasswordManagementSys.service;

import com.example.PasswordManagementSys.exceptions.DuplicateUserException;
import com.example.PasswordManagementSys.model.User;
import com.example.PasswordManagementSys.web.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
    void save(UserDto userDto) throws DuplicateUserException;
    User getUserByUserName(String userName);

    @Override
    UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException;
}
