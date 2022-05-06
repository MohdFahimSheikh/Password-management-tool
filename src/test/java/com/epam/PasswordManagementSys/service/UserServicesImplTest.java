package com.epam.PasswordManagementSys.service;

import com.epam.PasswordManagementSys.repository.UserRepository;
import com.epam.PasswordManagementSys.repository.UserRepositoryWrapper;
import com.epam.PasswordManagementSys.service.Impl.UserServicesImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServicesImplTest {

    @InjectMocks
    UserServicesImpl userService;

    @Mock
    UserRepositoryWrapper userRepositoryWrapper;
    @Mock
    UserRepository userRepository;

//    @Test
//    @DisplayName("User Service should save user with unique username")
//    public void userServiceShouldSaveUserWithUniqueUserName() throws DuplicateUserException {
//        UserDto user = new UserDto();
//        doThrow(DuplicateUserException.class).when(userRepositoryWrapper).saveUser(any());
//        Assertions.assertThrows(DuplicateUserException.class , () -> userService.save(user));
//    }

    @Test
    @DisplayName("User Service should return user by user name")
    public void userServiceShouldReturnUserByUserName()    {
        doThrow(UsernameNotFoundException.class).when(userRepositoryWrapper).getUserByUserName(any());
        Assertions.assertThrows(UsernameNotFoundException.class , () -> userService.getUserByUserName("user"));
    }
    @Test
    @DisplayName("User Service should return user by user name")
    public void userServiceShouldReturnUserByUserName1()    {
        Assertions.assertDoesNotThrow(() -> userService.getUserByUserName("user"));
    }

    @Test
    @DisplayName("User Service should return UserDetails by user name")
    public void userServiceShouldReturnUserDetails1()    {
        doThrow(UsernameNotFoundException.class).when(userRepositoryWrapper).getUserByUserName(any());
        Assertions.assertThrows(UsernameNotFoundException.class , () -> userService.loadUserByUsername("user"));
    }

}