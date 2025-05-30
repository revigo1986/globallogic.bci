package com.globallogic.bci.util;

import java.util.regex.Pattern;

public class StringUtils {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

    private static final String PASSWORD_REGEX = "^(?=(?:[^A-Z]*[A-Z]){1}[^A-Z]*$)(?=(?:[^0-9]*[0-9]){2}[^0-9]*$)[a-zA-Z0-9]{8,12}$";

    /**
     * Checks for a valid email
     * @param email
     * @return
     */
    public static boolean isValidEmail(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }

    /**
     * Checks for a valid password
     * @param password
     * @return
     */
    public static boolean isValidPassword(String password) {
        return Pattern.matches(PASSWORD_REGEX, password);
    }
}
