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
package io.github.seleniumquery.browser.driver;

import java.util.function.Consumer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Builds {@link WebDriver} instances for SeleniumQueryDriver.
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public abstract class DriverBuilder<T extends DriverBuilder<T>> {

    private static final Log LOGGER = LogFactory.getLog(DriverBuilder.class);

    private DesiredCapabilities desiredCapabilities;

    private boolean capabilitiesManuallySet = false;

    private Consumer<WebDriverManager> autoDriverDownloadConfigurer;

    private boolean autoQuitAskedFor = false;

    private WebDriverEventListener webDriverEventListener;

    /**
     * Configures the driver with the given capabilities.
     * @param desiredCapabilities The capabilities to be set.
     * @return A self reference for further configuration.
     * @since 0.9.0
     */
    @SuppressWarnings("unchecked")
    public T withCapabilities(DesiredCapabilities desiredCapabilities) {
        markCapabilitiesWereSet();
        this.desiredCapabilities = desiredCapabilities;
        return (T) this;
    }

    private void markCapabilitiesWereSet() {
        this.capabilitiesManuallySet = true;
    }

    protected DesiredCapabilities capabilities(DesiredCapabilities defaultDesiredCapabilities) {
        if (this.desiredCapabilities == null) {
            return defaultDesiredCapabilities;
        }
        return this.desiredCapabilities;
    }

    protected void overwriteCapabilityIfValueNotNull(DesiredCapabilities capabilities, String capabilityName, Object value) {
        markCapabilitiesWereSet();
        if (value != null) {
            capabilities.setCapability(capabilityName, value);
        }
    }

    protected boolean isCapabilitiesManuallySet() {
        return capabilitiesManuallySet;
    }

    /**
     * <p>Builds a WebDriver instance based on the default configurations plus the settings
     * changed through other method calls.</p>
     *
     * <b>IMPORTANT: This method should <i>not</i> be public, as the end-user shouldn't be able
     * to call "build()" - that'd be confusing.</b>
     *
     * @return A WebDriver instance based on the previous configurations.
     */
    protected abstract WebDriver build();

    protected void autoDownloadDriverIfAskedFor(Class<? extends WebDriver> driverClass) {
        if (this.autoDriverDownloadConfigurer != null) {
            WebDriverManager webDriverManager = WebDriverManager.getInstance(driverClass);
            this.autoDriverDownloadConfigurer.accept(webDriverManager);
            webDriverManager.setup();
        }
    }

    /**
     * Automatically downloads and configures the chromedriver.exe executable using
     * <a href="https://github.com/bonigarcia/webdrivermanager">webdrivermanager</a>.
     * @return A self reference, allowing further configuration of the driver builder.
     * @since 0.18.0
     */
    public T autoDriverDownload() {
        return this.autoDriverDownload(x -> {});
    }

    /**
     * Automatically downloads and configures the chromedriver.exe executable using
     * <a href="https://github.com/bonigarcia/webdrivermanager">webdrivermanager</a>.
     * @param configurer A function that allows <a href="https://github.com/bonigarcia/webdrivermanager#webdrivermanager-api">additional configuration</a> of the {@link WebDriverManager}.
     * @return A self reference, allowing further configuration of the driver builder.
     * @since 0.18.0
     */
    @SuppressWarnings("unchecked")
    public T autoDriverDownload(Consumer<WebDriverManager> configurer) {
        if (this.autoDriverDownloadConfigurer != null) {
            LOGGER.warn(".autoDriverDownload() has already been called. Overwriting previous calls.");
        }
        this.autoDriverDownloadConfigurer = configurer;
        return (T) this;
    }

    /**
     * Configures the WebDriver to automatically close when the JVM shuts down.
     * @return A self reference, allowing further configuration of the driver builder.
     * @since 0.18.0
     */
    @SuppressWarnings("unchecked")
    public T autoQuitDriver() {
        this.autoQuitAskedFor = true;
        return (T) this;
    }

    protected void autoQuitDriverIfAskedFor(WebDriver driver) {
        if (this.autoQuitAskedFor) {
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                synchronized (driver) {
                    LOGGER.warn("Quitting driver automatically now: " + driver);
                    driver.quit();
                }
            }));
        }
    }

    /**
     * Configures the driver with the given {@link WebDriverEventListener}.
     *
     * @param webDriverEventListener The webDriverEventListener to be set.
     * @return A self reference for further configuration.
     * @since 0.19.0
     */
    @SuppressWarnings("unchecked")
    public T withWebDriverEventListener(WebDriverEventListener webDriverEventListener) {
        this.webDriverEventListener = webDriverEventListener;
        return (T) this;
    }

    protected WebDriver attatchEventListner(WebDriver webDriver) {
        if (webDriverEventListener != null) {
            EventFiringWebDriver efwd = new EventFiringWebDriver(webDriver);
            efwd.register(webDriverEventListener);
            return efwd;
        } else {
            return webDriver;
        }
    }

}
