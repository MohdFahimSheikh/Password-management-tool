package com.epam.PasswordManagementSys.web.controller;

import com.epam.PasswordManagementSys.repository.AccountRepository;
import com.epam.PasswordManagementSys.repository.UserRepositoryWrapper;
import com.epam.PasswordManagementSys.service.AccountService;
import com.epam.PasswordManagementSys.service.GroupService;
import com.epam.PasswordManagementSys.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringRunner.class)
@WebMvcTest(value = MainController.class)
@WithMockUser
class MainControllerTest {
    @MockBean
    AccountService accountService;
    @MockBean
    UserService userService;
    @MockBean
    GroupService groupService;
    @MockBean
    AccountRepository accountRepository;
    @MockBean
    UserRepositoryWrapper userRepositoryWrapper;
    @Autowired
    MockMvc mockMvc;
    @Test
    @DisplayName("login should return the login page")
    void loginShouldReturnLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/login"))
                .andExpect(view().name("login")
        );
    }


    @Test
    @DisplayName("/ http request should return the home page")
    void homeShouldReturnIndexPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/"))
                .andExpect(view().name("index")
                );
    }
}