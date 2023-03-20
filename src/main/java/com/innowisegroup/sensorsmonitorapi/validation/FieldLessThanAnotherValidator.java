package com.innowisegroup.sensorsmonitorapi.validation;

import java.lang.reflect.Field;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ValidationException;

public class FieldLessThanAnotherValidator implements ConstraintValidator<FieldLessThanAnother, Object> {

    private String fromFieldName;

    private String toFieldName;

    @Override
    public void initialize(FieldLessThanAnother constraintAnnotation) {
        this.fromFieldName = constraintAnnotation.from();
        this.toFieldName = constraintAnnotation.to();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        int fromValue = getIntFromField(fromFieldName, value);
        int toValue = getIntFromField(toFieldName, value);

        return fromValue < toValue;
    }

    private int getIntFromField(String fieldName, Object object) {
        try {
            Class<?> aClass = object.getClass();
            Field field = aClass.getDeclaredField(fieldName);
            field.setAccessible(true);

            return field.getInt(object);
        }
        catch (NoSuchFieldException | IllegalAccessException e) {
            throw new ValidationException(String.format(
                "Can't get field value. Field: %s, Object: %s", fieldName, object), e);
        }
    }
}
