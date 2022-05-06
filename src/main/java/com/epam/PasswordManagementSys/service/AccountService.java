package com.epam.PasswordManagementSys.service;

import com.epam.PasswordManagementSys.exceptions.AccountDoesNotExistException;
import com.epam.PasswordManagementSys.exceptions.DuplicateAccountException;
import com.epam.PasswordManagementSys.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AccountService {
    List<Account> getAllAccounts();

    void saveAccount(Account account)throws DuplicateAccountException;;

    void updateAccount(Account account) throws DuplicateAccountException;

    Account getAccountById(int id) throws AccountDoesNotExistException;

    void deleteAccountById(int id) throws AccountDoesNotExistException;
}
