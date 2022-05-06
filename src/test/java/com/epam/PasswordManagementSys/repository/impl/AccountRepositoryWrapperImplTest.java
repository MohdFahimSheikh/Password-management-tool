package com.epam.PasswordManagementSys.repository.impl;

import com.epam.PasswordManagementSys.exceptions.AccountDoesNotExistException;
import com.epam.PasswordManagementSys.exceptions.DuplicateAccountException;
import com.epam.PasswordManagementSys.model.Account;
import com.epam.PasswordManagementSys.model.User;
import com.epam.PasswordManagementSys.repository.AccountRepository;
import com.epam.PasswordManagementSys.web.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(value = {MockitoExtension.class})
class AccountRepositoryWrapperImplTest {

    @InjectMocks
    AccountRepositoryWrapperImpl accountRepositoryWrapperImpl;


    @Mock
    AccountRepository accountRepository;

    Account account;
    UserDto userDto;
    User user;

    @BeforeEach
    public void setUp() {
        account = new Account("ghrr", "rge", "er", "erre");
        user = new User("jhghjg", "jhfbjb");
    }


//    @Override
//    public void saveAccount(Account account, User user) throws DuplicateAccountException {
//        checkForDuplicate(account.getAccountName(), user);
//        accountRepository.save(account);
//    }


    @Test
    @DisplayName("save account should check for duplicate account")
    public void saveAccountShouldCheckForDuplicateAccount() {
        when(accountRepository.countByAccountNameAndUser(anyString(), any())).thenReturn(1);
        assertThrows(DuplicateAccountException.class, () -> accountRepositoryWrapperImpl.saveAccount(account, user));
    }


    @Test
    @DisplayName("update account should update Account")
    void updateAccountShouldUpdateTheAccounts() throws DuplicateAccountException {
        accountRepositoryWrapperImpl.updateAccount(account, user);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    @DisplayName("getAccountById should return account by Id ")
    void getAccountByIdShouldReturnAccount() throws AccountDoesNotExistException {
        when(accountRepository.findByAccountIdAndUser(anyInt(), any())).thenReturn(List.of(account));
        assertEquals(List.of(account).get(0), accountRepositoryWrapperImpl.getAccountById(1, user));
    }


    @Test
    @DisplayName("getAccountById should return account if found")
    public void getAccountByIdShouldReturnAccountIfFound() {
        when(accountRepository.findByAccountIdAndUser(anyInt(), any())).thenReturn(List.of(account));
        Assertions.assertDoesNotThrow(() -> accountRepositoryWrapperImpl.getAccountById(1, user));
    }


    @Test
    @DisplayName("getAllAccount should return All accounts")
    void getAllAccountsShouldReturnAllAccounts() {
        when(accountRepository.findByUser(any())).thenReturn(List.of(account));
        assertEquals(List.of(account), accountRepositoryWrapperImpl.getAllAccounts(any()));
    }

    @Test
    @DisplayName("deleteAccountById should delete account if exist")
    public void deleteByIdShouldDeleteAccountIfExist() {
        when(accountRepository.findByAccountIdAndUser(anyInt(), any())).thenReturn(List.of(account));
        Assertions.assertDoesNotThrow(() -> accountRepositoryWrapperImpl.removeAccountById(1, user));
        verify(accountRepository, times(1)).delete(account);
    }

}