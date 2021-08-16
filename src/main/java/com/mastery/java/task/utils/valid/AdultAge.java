package com.mastery.java.task.utils.valid;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = AdultAgeValidator.class)
@Documented
public @interface AdultAge {
    String message() default "Must be over 18 years old";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
