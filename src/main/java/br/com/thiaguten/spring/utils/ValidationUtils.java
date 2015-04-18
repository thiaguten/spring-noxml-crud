package br.com.thiaguten.spring.utils;

import org.springframework.util.Assert;
import org.springframework.validation.Errors;

public abstract class ValidationUtils extends org.springframework.validation.ValidationUtils {

    public static void rejectIfEmailIsNotValid(Errors errors, String field, String errorCode) {
        rejectIfEmailIsNotValid(errors, field, errorCode, null, null);
    }

    public static void rejectIfEmailIsNotValid(Errors errors, String field, String errorCode, Object[] errorArgs, String defaultMessage) {
        Assert.notNull(errors, "Errors object must not be null");
        Object value = errors.getFieldValue(field);
        if (value == null || !EmailUtils.isValid(value.toString())) {
            errors.rejectValue(field, errorCode, errorArgs, defaultMessage);
        }
    }

}
