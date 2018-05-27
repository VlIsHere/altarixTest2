package com.altarix.artifacttest2.exceptions;

public class NotUniqueException extends InvalidDataException {
    @Override
    public String getLogMessage() {
        return "com.altarix.artifacttest2.exceptions.NotUniqueException: You input not unique field.";
    }
}
