package com.epam.PasswordManagementSys.exceptions;

public class DuplicateUserException extends Throwable {
    public DuplicateUserException(String s) {
        super(s);
    }
}
