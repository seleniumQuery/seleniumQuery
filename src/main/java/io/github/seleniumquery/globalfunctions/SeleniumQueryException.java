package io.github.seleniumquery.globalfunctions;

public class SeleniumQueryException extends RuntimeException {

    public SeleniumQueryException(String message) {
        super(message);
    }

    public SeleniumQueryException(String message, Throwable cause) {
        super(message, cause);
    }

}