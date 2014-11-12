package io.github.seleniumquery.selector;

/**
 * We still don't support some selectors inside conditions.
 * Example:
 *  The descendant selector "div span" in ":not(div span)" is being used as conditional.
 */
public class UnsupportedConditionalSelector extends RuntimeException {

    public UnsupportedConditionalSelector(String message) {
        super(message);
    }

}