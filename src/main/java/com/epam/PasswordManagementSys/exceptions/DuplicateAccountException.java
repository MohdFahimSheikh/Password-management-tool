package com.epam.PasswordManagementSys.exceptions;

public class DuplicateAccountException extends Exception {
    public DuplicateAccountException(String s) {
        super(s);
    }
}
