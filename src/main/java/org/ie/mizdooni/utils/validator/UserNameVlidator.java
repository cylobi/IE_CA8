package org.ie.mizdooni.utils.validator;
import java.util.HashSet;
import java.util.Set;
import org.ie.mizdooni.utils.validator.ValidatorException;
public class UserNameVlidator extends BaseValidator{
    public static final Set<Character> SPECIAL_CHARACTERS = new HashSet<>();
    public static final String SPECIAL_CHARACHTERS_STR;

    static {
        String specialChars = "\"!@#$%&*()'+,-./:;<=>?[]^`{|}";
        SPECIAL_CHARACHTERS_STR = specialChars;
        for (char c : specialChars.toCharArray()) {
            SPECIAL_CHARACTERS.add(c);
        }
    }

    public static void validate(String value) throws ValidatorException {
        for (var ch: value.toCharArray()){
            if (SPECIAL_CHARACTERS.contains(ch)){
                throw new ValidatorException("Username cannot have special characters! " +
                        "Special charachters: "+ SPECIAL_CHARACHTERS_STR);
            }
        }
    }
}
