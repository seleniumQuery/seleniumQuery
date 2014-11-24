package io.github.seleniumquery;

import io.github.seleniumquery.globalfunctions.driver.SeleniumQueryDriver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.interactions.PauseAction;

import java.io.File;

import static java.lang.String.format;

/**
 * Set of functionality used both by user-managed browsers and global (the static) browser.
 *
 * @author acdcjunior
 * @author ricardo-sc
 *
 * @since 0.9.0
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
     *
     * @param urlToOpen the URL to be opened. Example: "http://seleniumquery.github.io"
     * @return A self reference.
     *
     * @since 0.9.0
     */
    public SeleniumQueryBrowserFunctions url(String urlToOpen) {
        LOGGER.debug(format("Opening URL: %s", urlToOpen));
        driver().get().get(urlToOpen);
        return this;
    }

    /**
     * Opens the given file as a URL in the browser.
     *
     * @param fileToOpenAsURL the file to be opened as URL.
     * @return A self reference.
     *
     * @since 0.9.0
     */
    public SeleniumQueryBrowserFunctions url(File fileToOpenAsURL) {
        return url(fileToOpenAsURL.toURI().toString());
    }

    /**
     * <p>Performs a pause, instructing the the browser (thread) to wait (sleep) for the time
     * <b>in milliseconds</b> given.</p>
     * <pre>
     * $.pause(200); // pauses for 200 milliseconds
     * $.pause(10 * 1000); // pauses for 10 seconds
     * </pre>
     * @param timeToPauseInMillis pause duration, in milliseconds.
     * @return A self reference.
     *
     * @since 0.9.0
     *
     * @deprecated 'Pause' is considered to be a bad design practice. It is better to write code based on what the user
     *      will expect, for that consider using {@code .waitUntil()} functions, such as
     *      <code>$("#someDivThatShouldComeOut").waitUntil().is(":visible");</code>.
     */
    @Deprecated
    @SuppressWarnings("deprecation")
    public SeleniumQueryBrowserFunctions pause(long timeToPauseInMillis) {
        LOGGER.debug(format("Pausing for %d milliseconds.", timeToPauseInMillis));
        new PauseAction(timeToPauseInMillis).perform();
        return this;
    }

    /**
     * Attempts to maximize the window of the current browser/driver.
     *
     * @return A self reference
     *
     * @since 0.9.0
     */
    public SeleniumQueryBrowserFunctions maximizeWindow() {
        LOGGER.debug("Maximizing window.");
        driver().get().manage().window().maximize();
        return this;
    }

}