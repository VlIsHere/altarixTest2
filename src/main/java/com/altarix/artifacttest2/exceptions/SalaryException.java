package com.altarix.artifacttest2.exceptions;

public class SalaryException extends InvalidDataException {
    @Override
    public String getLogMessage() {
        return "com.altarix.artifacttest2.exceptions.SalaryException: wrong salary.";
    }
}
