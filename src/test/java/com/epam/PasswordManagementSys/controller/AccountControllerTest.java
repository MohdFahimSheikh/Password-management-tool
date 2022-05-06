package com.epam.PasswordManagementSys.controller;

import com.epam.PasswordManagementSys.exceptions.AccountDoesNotExistException;
import com.epam.PasswordManagementSys.model.Account;
import com.epam.PasswordManagementSys.model.User;
import com.epam.PasswordManagementSys.repository.AccountRepository;
import com.epam.PasswordManagementSys.repository.UserRepositoryWrapper;
import com.epam.PasswordManagementSys.service.AccountService;
import com.epam.PasswordManagementSys.service.GroupService;
import com.epam.PasswordManagementSys.service.UserService;
import com.epam.PasswordManagementSys.web.controller.AccountController;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.Model;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AccountController.class)
@WithMockUser
public class AccountControllerTest {
    @InjectMocks
    AccountController accountController;
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
    Account account;
    User user;

    @Before
    public void setUp() {
        account = new Account();
        user=new User();
    }
    @Test
    @DisplayName("getAccountById Should Give alterAccount page If Account Exist")
    public void getAccountByIdShouldGiveAlterAccountPageIfAccountExist() throws Exception {
        when(accountService.getAllAccounts()).thenReturn(List.of(account));
        mockMvc.perform(MockMvcRequestBuilders
                .get("/accounts"))
                .andExpect(view().name("accountList"));
    }

    @Test
    @DisplayName("getAccountById Should Give update Account page If not Exist then return 200 response")
    public void getAccountByIdShouldGiveUpdateAccountPageIfAccountExist() throws Exception {
        when(accountService.getAccountById(anyInt())).thenReturn(account);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/accounts/accountIdForUpdate/2").contentType(MediaType.APPLICATION_JSON)).andExpect(view().name("updateAccount"))
                .andExpect(status().isOk());
       ;
    }

    @Test
    @DisplayName("addAccount Should Give updateAccount page")
    public void addAccountShouldReturnAddNewAccountPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/accounts/addNewAccount"))
                .andExpect(view().name("addNewAccount")).andExpect(
                        model().attributeExists("account")
        );
    }

    @Test
    @DisplayName("delete the account and Should redirect to /accounts page")
    public void deleteAccountAndShouldRedirectToAccountListPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/accounts/deleteAccount/2"))
                .andExpect(redirectedUrlTemplate("/accounts")
        );
    }

    @Test
    @DisplayName("addAccount Should Give updateAccount page")
    public void addAccountShouldReturnAddNewAccountPage1() throws Exception {
        when(accountService.getAccountById(2)).thenReturn(account);
        account.setAccountId(2);
        account.setAccountName("gf");
        account.setUserName("tftyfy");
        account.setUrl("hghgyu");
        account.setPassword("hftfyh");
        doNothing().when(accountService).updateAccount(account);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/updateAccount/2"))
        ;

    }



}
