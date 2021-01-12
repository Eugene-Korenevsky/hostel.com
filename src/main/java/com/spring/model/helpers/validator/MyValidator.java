package com.spring.model.helpers.validator;


import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class MyValidator {
    private static ValidatorFactory validatorFactory;

    private MyValidator() {}

    public void init(){
        validatorFactory = Validation.buildDefaultValidatorFactory();
    }

    public static Validator getValidator(){
        return validatorFactory.getValidator();
    }
}
