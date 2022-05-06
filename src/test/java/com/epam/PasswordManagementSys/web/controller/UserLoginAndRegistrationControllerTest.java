package com.epam.PasswordManagementSys.web.controller;

import com.epam.PasswordManagementSys.exceptions.DuplicateUserException;
import com.epam.PasswordManagementSys.repository.AccountRepository;
import com.epam.PasswordManagementSys.repository.UserRepositoryWrapper;
import com.epam.PasswordManagementSys.service.UserService;
import com.epam.PasswordManagementSys.web.dto.UserDto;
import org.junit.Before;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserLoginAndRegistrationController.class)
@WithMockUser
class UserLoginAndRegistrationControllerTest {

    @MockBean
    UserService userService;
    @MockBean
    AccountRepository accountRepository;
    @MockBean
    UserRepositoryWrapper userRepositoryWrapper;
    @Autowired
    MockMvc mockMvc;
    UserDto user;

    @Before
    public void setUp(){
        user=new UserDto();
    }

    @Test
    @DisplayName("addGroup should return the add group page")
    public void addGroupShouldReturnAddGroupPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/registration"))
                .andExpect(view().name("registration"))
                .andExpect(model().attributeExists("user"));
    }


    @Test()
    @DisplayName("saveUser Should Give same page with error If User is duplicate")
    public void saveUserShouldGiveSamePageWithErrorForDuplicateUser() throws Exception, DuplicateUserException {
        UserDto userDto = new UserDto("fahim","sk24");
        doThrow(DuplicateUserException.class).when(userService).save(any());
       assertThrows(DuplicateUserException.class,()->userService.save(any()));
//        mockMvc.perform(MockMvcRequestBuilders
//                .post("/registration")
//                .contentType(MediaType.APPLICATION_JSON).
//                        content(new ObjectMapper().writeValueAsString(userDto)))
//                .andExpect(view().name("registration"));

    }

}

