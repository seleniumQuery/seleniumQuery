package io.github.seleniumquery.browser;

import java.io.File;

import io.github.seleniumquery.browser.driver.SeleniumQueryDriver;

/**
 * <p>Set of functions used both by user-managed browsers and global (static) browser.</p>
 *
 * They usually take the form <code>$.functionName();</code>
 *
 * @author acdcjunior
 * @author ricardo-sc
 *
 * @since 0.9.0
 */
public interface BrowserFunctions {

    /**
     * Obtains the seleniumQuery's driver tool instance. Through it you can:
     * <ul>
     *     <li><code>.get()</code> the current {@link org.openqa.selenium.WebDriver} instance;</li>
     *     <li>call <code>.use*()</code> methods to change the {@link org.openqa.selenium.WebDriver} currently used</li>
     * </ul>
     *
     * @return The seleniumQuery's driver tool instance.
     */
    SeleniumQueryDriver driver();

    /**
     * Returns the current URL in the browser.
     *
     * @return The currently loaded URL.
     *
     * @since 0.9.0
     */
    String url();

    /**
     * Opens the given URL in the default browser.
     *
     * @param urlToOpen The URL to be opened. Example: <code>$.url("http://seleniumquery.github.io");</code>
     * @return A self reference.
     *
     * @since 0.9.0
     */
    BrowserFunctions url(String urlToOpen);

    /**
     * Opens the given file as a URL in the browser.
     *
     * @param fileToOpenAsURL The file to be opened as URL.
     * @return A self reference.
     * @since 0.9.0
     */
    BrowserFunctions url(File fileToOpenAsURL);

    /**
     * <p>Performs a pause, instructing the the browser (thread) to wait (sleep) for the time
     * <b>in milliseconds</b> given.</p>
     * <pre>
     * $.pause(200); // pauses for 200 milliseconds
     * $.pause(10 * 1000); // pauses for 10 seconds
     * </pre>
     *
     * <strong>IMPORTANT: 'Pause' is considered to be a bad design practice.</strong><br>It will lead to fragile,
     * erratic, possibly non-repeatable tests that can begin failing for no reason.
     * <p>It is better to write code
     * based on what the user will expect, for that consider leveraging the {@code .waitUntil()} functions, such as
     * in <code>$("#someDivThatShouldComeOut").waitUntil().is(":visible");</code>.</p>
     *
     * @param timeToPauseInMillis Pause duration, in milliseconds.
     * @return A self reference.
     * @since 0.9.0
     */
    @SuppressWarnings("deprecation")
    BrowserFunctions pause(long timeToPauseInMillis);

    /**
     * Attempts to maximize the window of the current browser/driver.
     *
     * @return A self reference.
     * @since 0.9.0
     */
    BrowserFunctions maximizeWindow();

    /**
     * Quits the WebDriver in use by this seleniumQuery browser.
     *
     * @return A self reference.
     * @since 0.9.0
     */
    BrowserFunctions quit();

}
