package com.altarix.artifacttest2.exceptions;

public class InvalidPhoneNumberException extends InvalidDataException {
    @Override
    public String getLogMessage() {
        return "com.altarix.artifacttest2.exceptions.InvalidPhoneNumberException: incorrect phoneNumber.";
    }
}
