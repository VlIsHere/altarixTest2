package com.altarix.artifacttest2.exceptions;

public class InvalidEmailException extends InvalidDataException {
    @Override
    public String getLogMessage() {
        return "com.altarix.artifacttest2.exceptions.InvalidEmailException: bad email!";
    }
}
