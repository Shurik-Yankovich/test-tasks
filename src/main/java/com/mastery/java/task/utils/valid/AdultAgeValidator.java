package com.mastery.java.task.utils.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class AdultAgeValidator implements ConstraintValidator<AdultAge, LocalDate> {
    @Override
    public boolean isValid(LocalDate age, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate legalAge = LocalDate.now().minusYears(18);
        return (age != null) && age.isBefore(legalAge);
    }
}
