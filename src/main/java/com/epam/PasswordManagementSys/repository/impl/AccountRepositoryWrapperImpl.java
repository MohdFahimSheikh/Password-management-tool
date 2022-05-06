package com.epam.PasswordManagementSys.repository.impl;

import com.epam.PasswordManagementSys.exceptions.AccountDoesNotExistException;
import com.epam.PasswordManagementSys.exceptions.DuplicateAccountException;
import com.epam.PasswordManagementSys.model.Account;
import com.epam.PasswordManagementSys.model.User;
import com.epam.PasswordManagementSys.repository.AccountRepository;
import com.epam.PasswordManagementSys.repository.AccountRepositoryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
public class AccountRepositoryWrapperImpl implements AccountRepositoryWrapper {
    @Autowired
    AccountRepository accountRepository;
    @Override
    public void saveAccount(Account account, User user) throws DuplicateAccountException {
        checkForDuplicate(account.getAccountName(), user);
        accountRepository.save(account);
    }

    @Override
    public void updateAccount(Account account, User user) {
        accountRepository.save(account);
    }


    private void checkForDuplicate(String accountName, User user) throws DuplicateAccountException {
        if(accountRepository.countByAccountNameAndUser(accountName, user)>0){
            throw new DuplicateAccountException("Account Already Exist");
        }
    }

    @Override
    public Account getAccountById(int accountId, User user) throws AccountDoesNotExistException {
        List<Account> getAccountIdAndUser = accountRepository.findByAccountIdAndUser(accountId, user);
        return getAccountIdAndUser.get(0);
    }

    @Override
    public List<Account> getAllAccounts(User user) {
        List<Account> accounts = accountRepository.findByUser(user);
        return accounts;
    }

    @Override
    @Transactional
    public void removeAccountById(int accountId, User user) throws AccountDoesNotExistException {
        Account account = getAccountById(accountId, user);
           accountRepository.delete(account);
    }
}

