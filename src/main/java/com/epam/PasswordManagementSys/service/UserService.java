package com.epam.PasswordManagementSys.service;

import com.epam.PasswordManagementSys.exceptions.DuplicateUserException;
import com.epam.PasswordManagementSys.model.User;
import com.epam.PasswordManagementSys.web.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {
    void save(UserDto userDto) throws DuplicateUserException;
    User getUserByUserName(String userName);

    @Override
    UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException;
}
