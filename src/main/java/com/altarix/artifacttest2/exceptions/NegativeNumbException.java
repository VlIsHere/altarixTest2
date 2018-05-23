package com.altarix.artifacttest2.exceptions;

public class NegativeNumbException extends InvalidDataException{

    @Override
    public String getLogMessage() {
        return "com.altarix.artifacttest2.exceptions.NegativeNumbException: number in data < 0!";
    }
}
