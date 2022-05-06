package com.epam.PasswordManagementSys.constants;

final public class Constants {

    private Constants() {
    }

    public static final String PASSWORD_REGEX = "^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])" +
            "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";

    public static final String URL_REGEX = "((http|https)://)(www.)?"
            + "[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]"
            + "{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)";

    public static final String ACCOUNT_REGEX = "[a-z_]*";

}
