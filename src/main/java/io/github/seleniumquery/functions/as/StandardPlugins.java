package io.github.seleniumquery.functions.as;

import io.github.seleniumquery.SeleniumQueryObject;

/**
 * The out-of-the-box plugins provided by seleniumQuery.
 *
 * @author acdcjunior
 * @author ricardo-sc
 *
 * @since 0.9.0
 */
public class StandardPlugins {

    private SeleniumQueryObject seleniumQueryObject;

    public StandardPlugins(SeleniumQueryObject seleniumQueryObject) {
        this.seleniumQueryObject = seleniumQueryObject;
    }

    public AsSelect select() {
        return new AsSelect(seleniumQueryObject);
    }

}