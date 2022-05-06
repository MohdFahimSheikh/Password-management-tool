package com.epam.PasswordManagementSys.web.controller;

import com.epam.PasswordManagementSys.exceptions.AccountDoesNotExistException;
import com.epam.PasswordManagementSys.exceptions.DuplicateAccountException;
import com.epam.PasswordManagementSys.model.Account;
import com.epam.PasswordManagementSys.service.AccountService;
import com.epam.PasswordManagementSys.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private GroupService groupService;


    //This is handler method to handle list of accounts and return the model and view
    @GetMapping("/accounts")
    public String listOfAccounts(Model model) {
        model.addAttribute("accounts", accountService.getAllAccounts());
        return "accountList";
    }

    //This method is to handle new account Data and return the model and view
    @GetMapping("/accounts/addNewAccount")
    public ModelAndView addNewAccount(ModelAndView model) {
        Account account = new Account();
        model.addObject("groups", groupService.getAllGroups());
        model.addObject("account", account);
        model.setViewName("addNewAccount");
        return model;
    }

    @PostMapping("/saveAccount")
    public String saveAccountDetails(@Valid @ModelAttribute("account") Account account, BindingResult result, Model model) throws DuplicateAccountException {
        model.addAttribute("groups", groupService.getAllGroups());
        if (result.hasErrors()) {
            return "addNewAccount";
        }
        accountService.saveAccount(account);
        return "redirect:/accounts";
    }

    @GetMapping("/accounts/accountIdForUpdate/{id}")
    public String getAccountDetailsById(@PathVariable int id, Model model) throws AccountDoesNotExistException {
        model.addAttribute("account", accountService.getAccountById(id));
        return "updateAccount";
    }

    @PostMapping("/updateAccount/{id}")
    public String updateAccountDetails(@PathVariable int id, @ModelAttribute("account") Account account
    ) throws AccountDoesNotExistException, DuplicateAccountException {
        Account account1 = accountService.getAccountById(id);
        account1.setAccountId(id);
        account1.setAccountName(account.getAccountName());
        account1.setUserName(account.getUserName());
        account1.setUrl(account.getUrl());
        account1.setPassword(account.getPassword());
        accountService.updateAccount(account1);
        return "redirect:/accounts";
    }

    @GetMapping("/accounts/deleteAccount/{id}")
    public String deleteAccount(@PathVariable int id) throws AccountDoesNotExistException {
        accountService.deleteAccountById(id);
        return "redirect:/accounts";
    }

    @ExceptionHandler(value = Exception.class)
    public String accountExceptionHandler(Exception e){
        return "redirect:/accounts/addNewAccount?duplicate";
    }

}
