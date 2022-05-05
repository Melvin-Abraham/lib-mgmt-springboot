package com.example.library;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidDateValidator implements ConstraintValidator<ValidDate, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            simpleDateFormat.setLenient(false);

            Date targetDate = simpleDateFormat.parse(value.toString());
            Date currentDate = new Date();

            boolean isCurrentDateOrBefore = !targetDate.after(currentDate);
            return isCurrentDateOrBefore;
        } catch (ParseException e) {
            return false;
        }
    }
}
