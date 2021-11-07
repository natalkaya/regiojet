package org.example.qa.util;

public class ConfigReaderException extends Exception {

    public ConfigReaderException(String parameterName) {
        super("Could not read parameter: " + parameterName);
    }

    public ConfigReaderException(Throwable parameterName) {
        super("Could not read parameter: " + parameterName);
    }
}
