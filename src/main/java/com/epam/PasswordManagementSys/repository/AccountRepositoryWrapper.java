package com.epam.PasswordManagementSys.repository;

import com.epam.PasswordManagementSys.exceptions.AccountDoesNotExistException;
import com.epam.PasswordManagementSys.exceptions.DuplicateAccountException;
import com.epam.PasswordManagementSys.model.Account;
import com.epam.PasswordManagementSys.model.User;
import java.util.List;

public interface AccountRepositoryWrapper {
    void saveAccount(Account account, User user) throws DuplicateAccountException;
    Account getAccountById(int accountId, User user) throws AccountDoesNotExistException;
    List<Account> getAllAccounts(User user);
    void updateAccount(Account account,User user);
    void removeAccountById(int accountId ,User userBean) throws AccountDoesNotExistException;
}
