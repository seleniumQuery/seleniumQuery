package io.github.seleniumquery;

import io.github.seleniumquery.globalfunctions.driver.SeleniumQueryDriver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;

/**
 * Set of functionality used both by user-managed browsers and global (the static) browser.
 */
public class SeleniumQueryBrowserFunctions {

    private static final Log LOGGER = LogFactory.getLog(SeleniumQueryBrowserFunctions.class);

    private SeleniumQueryDriver globalDriver = new SeleniumQueryDriver();

    /**
     * Obtains the seleniumQuery's driver tool instance. Through it you can:
     * <ul>
     *     <li>.get() the current WebDriver instance;</li>
     *     <li>call .use*() methods to change the WebDriver currently used</li>
     * </ul>
     *
     * @return the seleniumQuery's driver tool instance
     */
    public SeleniumQueryDriver driver() {
        return globalDriver;
    }

    /**
     * Returns the current URL in the browser.
     *
     * @return the currently loaded URL.
     *
     * @since 0.9.0
     */
    public String url() {
        return driver().get().getCurrentUrl();
    }

    /**
     * Opens the given URL in the default browser.
     * @param urlToOpen The URL to be opened. Example: "http://seleniumquery.github.io"
     *
     * @since 0.9.0
     */
    public SeleniumQueryBrowserFunctions url(String urlToOpen) {
        LOGGER.debug("Opening URL: "+urlToOpen);
        driver().get().get(urlToOpen);
        return this;
    }

    /**
     * Opens the given file as a URL in the browser.
     * @param fileToOpenAsURL the file to be opened.
     *
     * @since 0.9.0
     */
    public SeleniumQueryBrowserFunctions url(File fileToOpenAsURL) {
        return url(fileToOpenAsURL.toURI().toString());
    }

}