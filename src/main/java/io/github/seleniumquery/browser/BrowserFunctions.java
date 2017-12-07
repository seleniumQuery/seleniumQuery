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
     * Executes JavaScript in the context of the driver (and currently selected frame or window). The script
     * fragment provided will be executed as the body of an anonymous function.
     * <br>
     * Example of using arguments:
     * <pre style="font-weight: bold">
     *     // this appends the div arg to the body, notice arguments[0] is used to access the first passed arg
     *     $.eval("document.body.innerHTML += arguments[0]", "&lt;div id='new'>&lt;/div>");
     * </pre>
     * Example of returning values:
     * <pre style="font-weight: bold">
     *     // simply use return in the expression
     *     long bodyCharCount = $.eval("return document.body.innerHTML.length");
     * </pre>
     * Naturally, {@code return} and {@code arguments} can be used in a single expression.
     * <br>
     * <p>
     * Within the script, use <code>document</code> to refer to the current document. Note that local
     * variables will not be available once the script has finished executing, though global variables
     * will persist.
     *
     * <p>
     * If the script has a return value (i.e. if the script contains a <code>return</code> statement),
     * then the following steps will be taken:
     *
     * <ul>
     * <li>For an HTML element, this method returns a WebElement</li>
     * <li>For a decimal, a Double is returned</li>
     * <li>For a non-decimal number, a Long is returned</li>
     * <li>For a boolean, a Boolean is returned</li>
     * <li>For all other cases, a String is returned.</li>
     * <li>For an array, return a List&lt;Object&gt; with each object following the rules above. We
     * support nested lists.</li>
     * <li>For a map, return a Map&lt;String, Object&gt; with values following the rules above.</li>
     * <li>Unless the value is null or there is no return value, in which null is returned</li>
     * </ul>
     *
     * <p>
     * Arguments must be a number, a boolean, a String, WebElement, or a List of any combination of
     * the above. An exception will be thrown if the arguments do not meet these criteria. The
     * arguments will be made available to the JavaScript via the "arguments" magic variable, as if
     * the function were called via "Function.apply"
     *
     * @param script The JavaScript to execute
     * @param args The arguments to the script. May be empty
     * @return One of Boolean, Long, Double, String, List, Map or WebElement. Or null.
     *
     * @since 0.18.0
     */
    <T> T eval(String script, Object... args);

    /**
     * Quits the WebDriver in use by this seleniumQuery browser.
     *
     * @return A self reference.
     * @since 0.9.0
     */
    BrowserFunctions quit();

}
