package org.ie.mizdooni.model;

import org.ie.mizdooni.utils.validator.BaseValidator;
import org.ie.mizdooni.utils.validator.ValidatorException;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface FieldValidator {
    Class<? extends BaseValidator> value();
}


public class BaseModel {

    public void _validate() throws ValidatorException {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(FieldValidator.class)) {
                FieldValidator annotation = field.getAnnotation(FieldValidator.class);
                var validatorClass = annotation.value();
                try {
                    field.setAccessible(true);
                    Object value = field.get(this);
                    Method validateMethod = validatorClass.getMethod("validate", String.class);
                    validateMethod.invoke(null, value);
                } catch (InvocationTargetException e) {
                    var targetException = e.getTargetException();
                    if (targetException instanceof ValidatorException){
                        throw (ValidatorException) targetException;
                    }

                    e.printStackTrace();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    public void validate() throws ValidatorException{
        this._validate();
    }

}
