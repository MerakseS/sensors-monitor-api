package com.innowisegroup.sensorsmonitorapi.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = FieldLessThanAnotherValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldLessThanAnother {

    String from();

    String to();

    String message() default "From is not less than to.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
