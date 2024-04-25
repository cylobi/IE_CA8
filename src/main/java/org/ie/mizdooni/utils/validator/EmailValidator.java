package org.ie.mizdooni.utils.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator extends BaseValidator{
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean check(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }
    public static void validate(String value) throws ValidatorException{
        boolean result = check(value);
        if (result == false){
            throw new ValidatorException("Email is invalid!");
        }
    }
}
