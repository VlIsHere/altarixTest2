package com.altarix.artifacttest2.exceptions;

public class InvalidDateException extends InvalidDataException {
    @Override
    public String getLogMessage() {
        return "com.altarix.artifacttest2.exceptions.InvalidDateException: Invalid Date.";
    }
}
