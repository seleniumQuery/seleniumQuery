package io.github.seleniumquery.globalfunctions.driver;

import io.github.seleniumquery.SeleniumQueryConfig;
import io.github.seleniumquery.SeleniumQueryException;
import io.github.seleniumquery.globalfunctions.driver.builders.*;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class SeleniumQueryDriver {

    private static final DriverBuilder DEFAULT_DRIVER_BUILDER = new HtmlUnitDriverBuilder();

    private DriverBuilder driverBuilder = DEFAULT_DRIVER_BUILDER;

    private WebDriver webDriver;

    /**
     * <p>
     *     Sets the argument as the current WebDriver instance, <b>quitting the current one, if exists.</b>
     * </p>
     *
     * @param driver The WebDriver instante to be set as current.
     */
    public void use(WebDriver driver) {
        quitAndClearCurrentWebDriver();
        this.webDriver = driver;
    }

    /**
     * <p>Returns the {@link WebDriver} currently set.</p>
     * <ul>
     *     <li>
     *         <b>If no driver has been set, it builds an instance based on previously set options
     *     through the <code>.use[SomeDriver]()</code> methods.</b>
     *          <ul>
     *              <li><b>If no option has been set before (no <code>.use[SomeDriver]()</code> method was called),
     *              it builds and assigns a <code>HtmlUnitDriver</code> instance as driver and returns it.</b></li>
     *          </ul>
     *     </li>
     * </ul>
     *
     * @return the currently set default {@link WebDriver};
     *
     * @since 0.9.0
     */
    public WebDriver get() {
        if (webDriver == null) {
            webDriver = this.driverBuilder.build();
            this.setDriverTimeout(); // TODO unit test
        }
        return webDriver;
    }

    private void setDriverTimeout() {  // TODO unit test
        this.webDriver.manage().timeouts().implicitlyWait(SeleniumQueryConfig.getGlobalTimeout(), TimeUnit.MILLISECONDS);
    }

    /**
     * Quits the WebDriver in use by this seleniumQuery browser.
     *
     * @since 0.9.0
     */
    public SeleniumQueryDriver quit() {
        if (webDriver == null) { // TODO unit test
            throw new SeleniumQueryException("WebDriver was not initialized, you can't .quit() it.");
        }
        webDriver.quit();
        webDriver = null;
        return this;
    }

    public HtmlUnitDriverBuilder useHtmlUnit() {
        return clearCurrentDriverAndAssignNewBuilder(new HtmlUnitDriverBuilder());
    }

    private <T extends DriverBuilder> T clearCurrentDriverAndAssignNewBuilder(T htmlUnitDriverBuilder) {
        quitAndClearCurrentWebDriver();
        this.driverBuilder = htmlUnitDriverBuilder;
        return htmlUnitDriverBuilder;
    }

    private void quitAndClearCurrentWebDriver() {
        if (this.webDriver != null) {
            this.webDriver.quit();
            this.webDriver = null;
        }
    }

    public FirefoxDriverBuilder useFirefox() {
        return clearCurrentDriverAndAssignNewBuilder(new FirefoxDriverBuilder());
    }

    /**
     * Sets <b>Chrome</b> as the default {@link WebDriver} for seleniumQuery.
     * <p>
     * Note that the Chrome driver needs a <i>server executable</i> to bridge Selenium to the browser and as such
     * Selenium must have the path to it. It is a file usually named <code>chromedriver.exe</code> and its latest
     * version can be downloaded from <a href="http://chromedriver.storage.googleapis.com/index.html">ChromeDriver's
     * download page</a> -- or check <a
     * href="https://github.com/seleniumQuery/seleniumQuery/wiki/seleniumQuery-and-Chrome-as-WebDriver-Browser">
     * seleniumQuery and Chrome as WebDriver/Browser wiki page</a> for the latest info.
     * </p>
     * <p><b> This method looks for the chromedriver.exe at the CLASSPATH.</b> If you wish to directly specify a path,
     * use {@link #useChrome()}.withPathToChromeDriverExe()</p>
     * <p>
     * For more info, see <a href="https://code.google.com/p/selenium/wiki/ChromeDriver">ChromeDriver's official wiki</a>.
     * </p>
     */
    public ChromeDriverBuilder useChrome() {
        return clearCurrentDriverAndAssignNewBuilder(new ChromeDriverBuilder());
    }

    /**
     * This method looks for the IEDriverServer.exe at the CLASSPATH. (Tipically at a resources/ folder of a
     * maven project.)
     *
     *
     * Sets IE as the default driver for seleniumQuery.
     * <p>
     * Note that, as IE needs a "server" to bridge selenium to the browser, you have
     * to point the path to it. It is a file usually named "IEDriverServer.exe" and its latest
     * version can be downloaded from http://selenium-release.storage.googleapis.com/index.html.
     * </p>
     * <p>
     * For more info, check https://code.google.com/p/selenium/wiki/InternetExplorerDriver
     * </p>
     *
     * @param pathToIEDriverServerExe The full path to the IEDriverServer.exe file.
     */
    public InternetExplorerDriverBuilder useInternetExplorer() {
        return clearCurrentDriverAndAssignNewBuilder(new InternetExplorerDriverBuilder());
    }

    public PhantomJSDriverBuilder usePhantomJS() {
        return clearCurrentDriverAndAssignNewBuilder(new PhantomJSDriverBuilder());
    }

}