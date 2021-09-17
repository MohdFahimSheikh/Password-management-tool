package com.example.PasswordManagementSys.service;

import com.example.PasswordManagementSys.exceptions.AccountDoesNotExistException;
import com.example.PasswordManagementSys.exceptions.DuplicateAccountException;
import com.example.PasswordManagementSys.model.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAllAccounts();

    void saveAccount(Account account)throws DuplicateAccountException;;

    Account updateAccount(Account account);

    Account getAccountById(int id) throws AccountDoesNotExistException;

    void deleteAccountById(int id) throws AccountDoesNotExistException;
}
