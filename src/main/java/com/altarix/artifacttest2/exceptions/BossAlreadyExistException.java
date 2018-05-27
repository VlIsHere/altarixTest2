package com.altarix.artifacttest2.exceptions;

public class BossAlreadyExistException extends InvalidDataException {
    @Override
    public String getLogMessage() {
        return "com.altarix.artifacttest2.exceptions.BossAlreadyExistException: Boss exist in table.";
    }
}
