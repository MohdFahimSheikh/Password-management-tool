package com.epam.PasswordManagementSys.controller;

import com.epam.PasswordManagementSys.model.Account;
import com.epam.PasswordManagementSys.model.Group;
import com.epam.PasswordManagementSys.repository.AccountRepository;
import com.epam.PasswordManagementSys.repository.UserRepositoryWrapper;
import com.epam.PasswordManagementSys.service.GroupService;
import com.epam.PasswordManagementSys.service.UserService;
import com.epam.PasswordManagementSys.web.controller.GroupController;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = GroupController.class)
@WithMockUser
public class GroupControllerTest {
    @MockBean
    GroupService groupService;
    @MockBean
    UserService userService;
    @MockBean
    AccountRepository accountRepository;
    @MockBean
    UserRepositoryWrapper userRepositoryWrapper;
    @Autowired
    MockMvc mockMvc;
    Group group;

    @Before
    public void setUp() {
        group = new Group();
    }

    @Test
    @DisplayName("getGroupById Should Give addGroup page If Group Exist")
    public void getGroupByIdShouldGiveAddGroupPageIfAccountExist() throws Exception {
        when(groupService.getGroupById(anyLong())).thenReturn(group);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/update/2"))
                .andExpect(view().name("updateGroup"));

    }

    @Test
    @DisplayName("get the list of account Should Give addGroup page If Group Exist")
    public void getGroupByIdShouldGiveAddGroupPageIfAccountExist1() throws Exception {
        when(groupService.getGroupById(anyLong())).thenReturn(group);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/update/2"))
                .andExpect(view().name("updateGroup"));

    }

    @Test
    @DisplayName("Get all grouplist which is associate with groups")
    public void listOfGroupsShouldReturnGroupsListPage() throws Exception {
        List<Group> groups = new ArrayList<Group>();
        groups.add(new Group("sk"));
        groups.add(new Group("gddg"));
        when(groupService.getAllGroups()).thenReturn(groups);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/showGroup"))
                .andExpect(view().name("groupList")).andExpect(model()
                .attributeExists("groups"));
    }


    @Test
    @DisplayName("AccountListByGroup should return tha accountList page of accounts according to accountId")
    public void AccountListGroupsShouldReturnTheAccountListPage() throws Exception {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account(1,"sk","fahim","https://www.epam.com","vfgf"));
        accounts.add(new Account(2,"sk","fashim","https://www.epam.com","vfgf"));
        accounts.add(new Account(3,"sk","fahsim","https://www.epam.com","vfgf"));

        when(groupService.getAllAccountsByIdAndUser(anyLong())).thenReturn(accounts);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/groups/2"))
                .andExpect(view().name("accountList")).andExpect(model()
                .attributeExists("accounts"));
    }


    @Test
    @DisplayName("addGroup should return the add group page")
    public void addGroupShouldReturnAddGroupPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/addGroup"))
                .andExpect(view().name("addGroup")).andExpect(model()
                .attributeExists("group"));
    }

}
