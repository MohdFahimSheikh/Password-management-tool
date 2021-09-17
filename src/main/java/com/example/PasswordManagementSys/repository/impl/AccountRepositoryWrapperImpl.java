package com.example.PasswordManagementSys.repository.impl;

import com.example.PasswordManagementSys.exceptions.AccountDoesNotExistException;
import com.example.PasswordManagementSys.exceptions.DuplicateAccountException;
import com.example.PasswordManagementSys.model.Account;
import com.example.PasswordManagementSys.model.User;
import com.example.PasswordManagementSys.repository.AccountRepository;
import com.example.PasswordManagementSys.repository.AccountRepositoryWrapper;
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
