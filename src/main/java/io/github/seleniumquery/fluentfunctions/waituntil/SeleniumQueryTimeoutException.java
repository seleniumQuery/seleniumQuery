package io.github.seleniumquery.fluentfunctions.waituntil;

import org.openqa.selenium.TimeoutException;

/**
 * Indicates that a timeout happened during an operation handled by SeleniumQuery.
 * @since 0.18.0
 */
@SuppressWarnings("deprecation")
public class SeleniumQueryTimeoutException extends io.github.seleniumquery.wait.SeleniumQueryTimeoutException {

    public SeleniumQueryTimeoutException(String message, TimeoutException sourceException) {
        super(message, sourceException);
    }

}
