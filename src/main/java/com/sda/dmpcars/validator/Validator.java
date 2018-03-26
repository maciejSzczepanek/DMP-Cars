package com.sda.dmpcars.validator;

public interface Validator<T> {
    boolean validate(T t);
}
