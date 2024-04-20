package org.ie.mizdooni.utils.validator;
import java.lang.annotation.*;

public class BaseValidator {
    public static void validate(String value) throws ValidatorException {}
}


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface FieldValidator {
    Class<? extends BaseValidator> value();
}
