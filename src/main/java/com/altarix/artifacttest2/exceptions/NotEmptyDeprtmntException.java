package com.altarix.artifacttest2.exceptions;

public class NotEmptyDeprtmntException extends InvalidDataException {
    @Override
    public String getLogMessage() {
        return "com.altarix.artifacttest2.exceptions.NotEmptyDeprtmntException: Department has employees.";
    }
}
