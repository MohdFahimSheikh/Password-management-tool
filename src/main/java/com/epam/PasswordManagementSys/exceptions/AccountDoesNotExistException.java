package com.epam.PasswordManagementSys.exceptions;

public class AccountDoesNotExistException extends Exception {
    public AccountDoesNotExistException(String s) {
        super(s);
    }
}
