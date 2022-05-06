package com.epam.PasswordManagementSys.repository.impl;

import com.epam.PasswordManagementSys.exceptions.DuplicateUserException;
import com.epam.PasswordManagementSys.model.Account;
import com.epam.PasswordManagementSys.model.User;
import com.epam.PasswordManagementSys.repository.UserRepository;
import com.epam.PasswordManagementSys.web.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(value = {MockitoExtension.class})
class UserRepositoryWrapperImplTest {

    @InjectMocks
    UserRepositoryWrapperImpl userRepositoryWrapperImpl;

    @Mock
    UserRepository userRepository;

    Account account;
    UserDto userDto;
    User user;

    @BeforeEach
    public void setUp() {
        account = new Account("ghrr", "rge", "er", "erre");
        user = new User("jhghjg", "jhfbjb");
    }

    @Test
    @DisplayName("UserDaoWrapper should save unique user")
    public void userDaoWrapperShouldSaveUniqueUser(){
        when(userRepository.countByUserName(anyString())).thenReturn(0);
        Assertions.assertDoesNotThrow(()-> userRepositoryWrapperImpl.saveUser(user));
    }

    @Test
    @DisplayName("UserDaoWrapper should save should throw exception for duplicate user")
    public void userDaoWrapperShouldThrowExceptionForDuplicateUser(){
        when(userRepository.countByUserName(anyString())).thenReturn(1);
        Assertions.assertThrows(DuplicateUserException.class ,()-> userRepositoryWrapperImpl.saveUser(user));
    }


    @Test
    @DisplayName("UserDaoWrapper should return user by user name")
    public void userDaoWrapperShouldReturnUserByUserName(){
        when(userRepository.findByUserName(anyString())).thenReturn(List.of(user));
        Assertions.assertDoesNotThrow(()-> userRepositoryWrapperImpl.getUserByUserName(""));
    }

    @Test
    @DisplayName("UserDaoWrapper should return user by user name")
    public void userDaoWrapperShouldReturnUserByUserName1(){
        when(userRepository.findByUserName(anyString())).thenReturn(List.of());
        Assertions.assertThrows(UsernameNotFoundException.class ,()-> userRepositoryWrapperImpl.getUserByUserName(""));
    }

}