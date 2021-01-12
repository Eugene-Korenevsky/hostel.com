package com.spring.model.entity;

import java.util.ArrayList;

public class ValidError {
    private ArrayList<String> errors;
    private String errorsString;

    public ArrayList<String> getErrors() {
        return errors;
    }

    public String getErrorsString() {
        return errorsString;
    }

    public void setErrors(ArrayList<String> errors) {
        this.errors = errors;
    }

    public void setErrorsString(String errorsString) {
        this.errorsString = errorsString;
    }
}
