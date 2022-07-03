package com.triple.milegeservice.annotation;


import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TypeValidator implements ConstraintValidator<Type, String> {


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(StringUtils.hasText(value)&&!"REVIEW".equals(value)){
            return false;
        }

        return true;
    }
}
