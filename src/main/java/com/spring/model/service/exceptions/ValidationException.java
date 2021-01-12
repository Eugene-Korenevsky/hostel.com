package com.spring.model.service.exceptions;

import com.spring.model.entity.ValidError;

public class ValidationException extends Exception {
    private ValidError validError;
    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message,ValidError validError){
        super(message);
        this.validError = validError;
    }



    public ValidError getValidError() {
        return validError;
    }

    public void setValidError(ValidError validError) {
        this.validError = validError;
    }
}
