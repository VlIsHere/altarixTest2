package com.altarix.artifacttest2.exceptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.logging.Logger;

public class InvalidDataException extends Exception {
    private Logger myLogger;

    public InvalidDataException() {
        File file = new File("warning.log");
        try (PrintWriter writer = new PrintWriter(file)) {
            myLogger = Logger.getLogger(InvalidDataException.class.getName()+"Logger");
            logging(writer);
        } catch (FileNotFoundException e) {
            try {
                file.createNewFile();
            } catch (IOException e1) {
            }
        }

    }

    public String getLogMessage(){
        return "com.altarix.artifacttest2.exceptions.InvalidDataException: Incorrect Data.";
    }

    private void logging(PrintWriter writer) {
        myLogger.info(getLogMessage());
        writer.printf("\n%s: %s\n", LocalDateTime.now(), getLogMessage());
        writer.flush();
    }
}
