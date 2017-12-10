/*
 * Copyright (c) 2017 seleniumQuery authors
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

package io.github.seleniumquery.browser.driver.builders;

import static io.github.seleniumquery.browser.driver.builders.DriverInstantiationUtils
    .customPathWasProvidedAndExecutableExistsThere;
import static io.github.seleniumquery.browser.driver.builders.DriverInstantiationUtils.executableExistsInClasspath;
import static io.github.seleniumquery.browser.driver.builders.DriverInstantiationUtils.getFullPath;
import static io.github.seleniumquery.browser.driver.builders.DriverInstantiationUtils.getFullPathForFileInClasspath;
import static java.lang.String.format;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.seleniumquery.SeleniumQueryException;
import io.github.seleniumquery.browser.driver.DriverBuilder;

/**
 * Builds {@link ChromeDriver} instances for {@link io.github.seleniumquery.browser.driver.SeleniumQueryDriver}.
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class ChromeDriverBuilder extends DriverBuilder<ChromeDriverBuilder> {

    private static final Log LOGGER = LogFactory.getLog(ChromeDriverBuilder.class);

    private static final String CHROME_DRIVER_EXECUTABLE_SYSTEM_PROPERTY = "webdriver.chrome.driver";

    private static final String EXCEPTION_MESSAGE = " \nDownload the latest release at http://chromedriver.storage.googleapis.com/index.html and place it: \n" +
            "(1) on the classpath of this project; or\n" +
            "(2) on the path specified by the \"" + CHROME_DRIVER_EXECUTABLE_SYSTEM_PROPERTY + "\" system property; or\n" +
            "(3) on a folder in the system's PATH variable; or\n" +
            "(4) wherever and set the path via $.driver().useChrome().withPathToChromeDriver(\"other/path/to/chromedriver<.exe>\").\n" +
            "For more information, see https://github.com/seleniumQuery/seleniumQuery/wiki/seleniumQuery-and-Chrome-Driver";

    private static final String BAD_PATH_PROVIDED_EXCEPTION_MESSAGE = "The ChromeDriver Server executable file was not found (or is a directory) at \"%s\"." + EXCEPTION_MESSAGE;

    private static final String CHROMEDRIVER_EXECUTABLE_LINUX = "chromedriver";
    private static final String CHROMEDRIVER_EXECUTABLE_WINDOWS = "chromedriver.exe";


    private String customPathToChromeDriver;
    private ChromeOptions chromeOptions;

    /**
     * Sets the {@link DesiredCapabilities} to the driver being built.
     *
     * @param desiredCapabilities The desired capabilities object. See https://sites.google.com/a/chromium.org/chromedriver/capabilities
     * @return The current builder instance, for additional configuration, if needed.
     *
     * @deprecated Prefer using {@link ChromeOptions} and {@link ChromeDriverBuilder#withOptions(ChromeOptions)} instead.
     */
    @Override
    @Deprecated
    public ChromeDriverBuilder withCapabilities(DesiredCapabilities desiredCapabilities) {
        return super.withCapabilities(desiredCapabilities);
    }

    /**
     * Configures {@link ChromeDriver} to run in headless mode.
     *
     * @return A self reference, allowing further configuration.
     * @since 0.17.0
     */
    public ChromeDriverBuilder headless() {
        getInitializedChromeOptions().addArguments("headless").addArguments("disable-gpu");
        return this;
    }

    /**
     * Sets specific {@link ChromeOptions} options to be used in the {@link ChromeDriver}.
     *
     * @param chromeOptions Options to be used.
     * @return A self reference, allowing further configuration.
     *
     * @since 0.9.0
     */
    public ChromeDriverBuilder withOptions(ChromeOptions chromeOptions) {
        this.chromeOptions = chromeOptions;
        return this;
    }

    /**
     * Configures the builder to look for the ChromeDriver executable (<code>chromedriver.exe</code>/<code>chromedriver</code>) at
     * the specified path.
     *
     * @param pathToChromeDriver The path to the executable server file. Examples:
     *     <code>"C:\myFiles\chromedriver.exe"</code>; can be relative, as in <code>"..\stuff\chromedriver"</code>;
     *     does not matter if the executable was renamed, such as <code>"wherever/myself/drivers/chromedriver_v12345.exe"</code>.
     *
     * @return A self reference, allowing further configuration.
     *
     * @since 0.9.0
     */
    public ChromeDriverBuilder withPathToChromeDriver(String pathToChromeDriver) {
        this.customPathToChromeDriver = pathToChromeDriver;
        return this;
    }

    @Override
    protected WebDriver build() {
        autoDownloadDriverIfAskedFor(ChromeDriver.class);
        WebDriver webDriver = buildChrome();
        autoQuitDriverIfAskedFor(webDriver);
        return webDriver;
    }

    private WebDriver buildChrome() {
        if (isCapabilitiesManuallySet()) {
            LOGGER.warn("Prefer using ChromeOptions and .withOptions() instead of DesiredCapabilities and .withCapabilities().");
            return buildUsingCapabilities();
        } else {
            return buildUsingChromeOptions();
        }
    }

    private WebDriver buildUsingCapabilities() {
        DesiredCapabilities capabilities = capabilities(DesiredCapabilities.chrome());
        overwriteCapabilityIfValueNotNull(capabilities, ChromeOptions.CAPABILITY, this.chromeOptions);

        configureChromeServerExecutablePath();
        try {
            //noinspection deprecation
            return new ChromeDriver(capabilities);
        } catch (IllegalStateException e) {
            throwCustomExceptionIfExecutableWasNotFound(e);
            throw e;
        }
    }

    private WebDriver buildUsingChromeOptions() {
        configureChromeServerExecutablePath();
        try {
            return new ChromeDriver(getInitializedChromeOptions());
        } catch (IllegalStateException e) {
            throwCustomExceptionIfExecutableWasNotFound(e);
            throw e;
        }
    }

    private ChromeOptions getInitializedChromeOptions() {
        if (this.chromeOptions == null) {
           this.chromeOptions = new ChromeOptions();
        }
        return this.chromeOptions;
    }

    private void configureChromeServerExecutablePath() {
        if (customPathWasProvidedAndExecutableExistsThere(this.customPathToChromeDriver, BAD_PATH_PROVIDED_EXCEPTION_MESSAGE)) {
            setExecutableSystemProperty(getFullPath(this.customPathToChromeDriver));
        } else if (executableExistsInClasspath(CHROMEDRIVER_EXECUTABLE_WINDOWS)) {
            setExecutableSystemProperty(getFullPathForFileInClasspath(CHROMEDRIVER_EXECUTABLE_WINDOWS));
        } else if (executableExistsInClasspath(CHROMEDRIVER_EXECUTABLE_LINUX)) {
            setExecutableSystemProperty(getFullPathForFileInClasspath(CHROMEDRIVER_EXECUTABLE_LINUX));
        }
    }

    private void setExecutableSystemProperty(String executableFullPath) {
        LOGGER.debug("Loading ChromeDriver executable from "+executableFullPath);
        System.setProperty(CHROME_DRIVER_EXECUTABLE_SYSTEM_PROPERTY, executableFullPath);
    }

    private void throwCustomExceptionIfExecutableWasNotFound(IllegalStateException e) {
        if (e.getMessage().contains("path to the driver executable must be set")) {
            throw new SeleniumQueryException(
                format(
                        "The ChromeDriver server executable (%s/%s) was not found in the classpath, in the \"%s\" system property or in the system's PATH variable. %s",
                    CHROMEDRIVER_EXECUTABLE_WINDOWS, CHROMEDRIVER_EXECUTABLE_LINUX, CHROME_DRIVER_EXECUTABLE_SYSTEM_PROPERTY, EXCEPTION_MESSAGE
                ), e);
        }
    }

}
