/*
 * Copyright (c) 2015 seleniumQuery authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.seleniumquery.browser.driver;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;

import io.github.seleniumquery.SeleniumQueryConfig;
import io.github.seleniumquery.browser.driver.builders.ChromeDriverBuilder;
import io.github.seleniumquery.browser.driver.builders.FirefoxDriverBuilder;
import io.github.seleniumquery.browser.driver.builders.HtmlUnitDriverBuilder;
import io.github.seleniumquery.browser.driver.builders.InternetExplorerDriverBuilder;
import io.github.seleniumquery.browser.driver.builders.PhantomJSDriverBuilder;

/**
 * Represents and manages the {@link WebDriver} instance used by (within) a specific
 * {@link io.github.seleniumquery.SeleniumQueryBrowser}.
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class SeleniumQueryDriver {

    private static final Log LOGGER = LogFactory.getLog(SeleniumQueryDriver.class);

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
     * @return the currently set {@link WebDriver}.
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
     * Quits, if exists, the WebDriver in use by this seleniumQuery browser.
     *
     * @since 0.9.0
     *
     * @return A self reference.
     */
    public SeleniumQueryDriver quit() {
        if (webDriver == null) { // TODO unit test
            LOGGER.warn("Called .quit() before initializing WebDriver, nothing was done.");
        } else {
            webDriver.quit();
            webDriver = null;
        }
        return this;
    }

    /**
     * <p>Sets {@link org.openqa.selenium.htmlunit.HtmlUnitDriver} as the {@link WebDriver} for this seleniumQuery browser instance.</p>
     *
     * @return A {@link HtmlUnitDriverBuilder}, allowing further configuration of the driver.
     *
     * @since 0.9.0
     */
    public HtmlUnitDriverBuilder useHtmlUnit() {
        return clearCurrentDriverAndAssignNewBuilder(new HtmlUnitDriverBuilder());
    }

    private <T extends DriverBuilder> T clearCurrentDriverAndAssignNewBuilder(T newDriverBuilder) {
        quitAndClearCurrentWebDriver();
        this.driverBuilder = newDriverBuilder;
        return newDriverBuilder;
    }

    private void quitAndClearCurrentWebDriver() {
        if (this.webDriver != null) {
            this.webDriver.quit();
            this.webDriver = null;
        }
    }

    /**
     * <p>Sets {@link org.openqa.selenium.firefox.FirefoxDriver} as the {@link WebDriver} for this seleniumQuery browser instance.</p>
     *
     * This method looks for the Firefox binary at the system's PATH variable.
     *
     * @return A {@link FirefoxDriverBuilder}, allowing further configuration of the driver.
     *
     * @since 0.9.0
     */
    public FirefoxDriverBuilder useFirefox() {
        return clearCurrentDriverAndAssignNewBuilder(new FirefoxDriverBuilder());
    }

    /**
     * Sets {@link org.openqa.selenium.chrome.ChromeDriver} as the {@link WebDriver} for this seleniumQuery browser instance.
     * <p>
     * Note that the Chrome needs a <i>ChromeDriver Server executable</i> to bridge Selenium to the browser and as such
     * Selenium must know the path to it. It is a file usually named <code>chromedriver.exe</code> (windows) or <code>chromedriver</code> (linux)
     * and its latest version can be downloaded from
     * <a href="http://chromedriver.storage.googleapis.com/index.html">ChromeDriver's download page</a>. You can also check
     * <a href="https://github.com/seleniumQuery/seleniumQuery/wiki/seleniumQuery-and-Chrome-Driver">seleniumQuery and Chrome Driver wiki page</a>
     * for the other info.
     * </p>
     * <br>
     * <b> This method looks for the ChromeDriver executable (<code>chromedriver.exe</code>/<code>chromedriver</code>) at the CLASSPATH
     * (tipically at a {@code resources/} folder of a maven project), at the "webdriver.chrome.driver" system property or at the system's PATH variable.</b>
     * If you wish to directly specify a path, use:
     * <pre>
     * $.driver().useChrome().withPathToChromeDriver("other/path/to/chromedriver.exe"); // windows
     * $.driver().useChrome().withPathToChromeDriver("other/path/to/chromedriver"); // linux
     * </pre>
     *
     * @return A {@link ChromeDriverBuilder}, allowing further configuration of the driver.
     *
     * @since 0.9.0
     */
    public ChromeDriverBuilder useChrome() {
        return clearCurrentDriverAndAssignNewBuilder(new ChromeDriverBuilder());
    }

    /**
     * Sets {@link org.openqa.selenium.ie.InternetExplorerDriver} as the {@link WebDriver} for this seleniumQuery browser instance.
     * <p>
     * Note that the {@link org.openqa.selenium.ie.InternetExplorerDriver} needs a <i>server executable</i> to bridge selenium to the browser and,
     * as such, Selenium must know the path to it. It is a file usually named <code>IEDriverServer.exe</code> and its latest
     * version can be downloaded from
     * <a href="http://selenium-release.storage.googleapis.com/index.html">IEDriverServer's download page</a> -- or check
     * <a href="https://github.com/seleniumQuery/seleniumQuery/wiki/seleniumQuery-and-IE-Driverr">seleniumQuery and IE Driver wiki page</a>
     * for the latest info.
     * </p>
     * <br>
     * <b> This method looks for the <code>IEDriverServer.exe</code> at the CLASSPATH (tipically at a {@code resources/} folder of a
     * maven project), at the "webdriver.ie.driver" system property or at the system's PATH variable.</b>
     * If you wish to directly specify a path, use:
     * <pre>
     * $.driver().useChrome().withPathToIEDriverServerExe("other/path/to/IEDriverServer.exe");
     * </pre>
     *
     * @return A {@link InternetExplorerDriverBuilder}, allowing further configuration of the driver.
     *
     * @since 0.9.0
     */
    public InternetExplorerDriverBuilder useInternetExplorer() {
        return clearCurrentDriverAndAssignNewBuilder(new InternetExplorerDriverBuilder());
    }

    /**
     * Sets {@link org.openqa.selenium.phantomjs.PhantomJSDriver} as the {@link WebDriver} for this seleniumQuery browser instance.
     * <p>
     * Note that the PhantomJS Driver needs a <i>PhantomJS executable</i> to act as browser and as such
     * Selenium must know the path to it. It is a file usually named <code>phantomjs.exe</code> (windows) or <code>phantomjs</code> (linux)
     * and its latest version can be downloaded from
     * <a href="http://phantomjs.org/download.html">PhantomJS download page</a>.
     * </p>
     * <br>
     * <b> This method looks for the PhantomJS executable (<code>phantomjs.exe</code>/<code>phantomjs</code>) at the CLASSPATH
     * (tipically at a {@code resources/} folder of a maven project), at the "phantomjs.binary.path" system property or at the system's PATH variable.</b>
     * If you wish to directly specify a path, use:
     * <pre>
     * $.driver().usePhantomJS().withPathToPhantomJS("other/path/to/phantomjs.exe"); // windows
     * $.driver().usePhantomJS().withPathToPhantomJS("other/path/to/phantomjs"); // linux
     * </pre>
     *
     * @return A {@link PhantomJSDriverBuilder}, allowing further configuration of the driver.
     *
     * @since 0.9.0
     */
    public PhantomJSDriverBuilder usePhantomJS() {
        return clearCurrentDriverAndAssignNewBuilder(new PhantomJSDriverBuilder());
    }

}
