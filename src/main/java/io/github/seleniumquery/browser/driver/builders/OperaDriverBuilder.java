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

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.seleniumquery.SeleniumQueryException;
import io.github.seleniumquery.browser.driver.DriverBuilder;

/**
 * Builds {@link OperaDriver} instances for {@link io.github.seleniumquery.browser.driver.SeleniumQueryDriver}.
 *
 * @author acdcjunior
 * @since 0.18.0
 */
public class OperaDriverBuilder extends DriverBuilder<OperaDriverBuilder> {

    private static final Log LOGGER = LogFactory.getLog(OperaDriverBuilder.class);

    private OperaOptions operaOptions;

    /**
     * Sets the {@link DesiredCapabilities} to the driver being built.
     *
     * @param desiredCapabilities The desired capabilities object.
     * @return The current builder instance, for additional configuration, if needed.
     * @since 0.18.0
     * @deprecated Prefer using {@link OperaOptions} and {@link OperaDriverBuilder#withOptions(OperaOptions)} instead.
     */
    @Override
    @Deprecated
    public OperaDriverBuilder withCapabilities(DesiredCapabilities desiredCapabilities) {
        return super.withCapabilities(desiredCapabilities);
    }

    /**
     * Sets specific {@link OperaOptions} options to be used in the {@link OperaDriver}.
     *
     * @param operaOptions Options to be used.
     * @return A self reference, allowing further configuration.
     * @since 0.18.0
     */
    public OperaDriverBuilder withOptions(OperaOptions operaOptions) {
        if (this.operaOptions != null) {
            LOGGER.warn("OperaOptions were already set. They are being overwritten.");
        }
        this.operaOptions = operaOptions;
        return this;
    }

    /**
     * Sets the path to the Opera executable. This path should exist on the
     * machine which will launch Opera. The path should either be absolute or
     * relative to the location of running OperaDriver server.
     *
     * <br>
     * Example:
     * <pre>
     *     $.driver().useOpera().withBinary("C:/Program Files/Opera/49.0.2725.47/opera.exe").autoDriverDownload();
     * </pre>
     * Note: this is an alias to {@link OperaOptions#setBinary(String)} .
     *
     * @param path Path to Opera executable.
     * @return A self reference, allowing further configuration.
     * @since 0.18.0
     */
    public OperaDriverBuilder withBinary(String path) {
        getInitializedOperaOptions().setBinary(path);
        return this;
    }

    /**
     * Sets the path to the Opera executable. This path should exist on the
     * machine which will launch Opera. The path should either be absolute or
     * relative to the location of running OperaDriver server.
     *
     * <br>
     * Example:
     * <pre>
     *     $.driver().useOpera().withBinary(new File("C:/Program Files/Opera/49.0.2725.47/opera.exe")).autoDriverDownload();
     * </pre>
     * Note: this is an alias to {@link OperaOptions#setBinary(File)} .
     *
     * @param path Path to Opera executable.
     * @return A self reference, allowing further configuration.
     * @since 0.18.0
     */
    public OperaDriverBuilder withBinary(File path) {
        getInitializedOperaOptions().setBinary(path);
        return this;
    }

    @Override
    protected WebDriver build() {
        autoDownloadDriverIfAskedFor(OperaDriver.class);
        WebDriver webDriver = buildOpera();
        autoQuitDriverIfAskedFor(webDriver);
        return webDriver;
    }

    private WebDriver buildOpera() {
        if (isCapabilitiesManuallySet()) {
            LOGGER.warn("Prefer using OperaOptions and .withOptions() instead of DesiredCapabilities and " +
                ".withCapabilities().");
            return buildUsingCapabilities();
        } else {
            return buildUsingOperaOptions();
        }
    }

    @SuppressWarnings("deprecation")
    private WebDriver buildUsingCapabilities() {
        DesiredCapabilities capabilities = capabilities(DesiredCapabilities.opera());
        overwriteCapabilityIfValueNotNull(capabilities, OperaOptions.CAPABILITY, this.operaOptions);

        try {
            return new OperaDriver(capabilities);
        } catch (IllegalStateException e) {
            throwCustomExceptionIfExecutableWasNotFound(e);
            throw e;
        } catch (WebDriverException e) {
            throwCustomExceptionIfBinaryWasNotFound(e);
            throw e;
        }
    }

    private WebDriver buildUsingOperaOptions() {
        try {
            return new OperaDriver(getInitializedOperaOptions());
        } catch (IllegalStateException e) {
            throwCustomExceptionIfExecutableWasNotFound(e);
            throw e;
        } catch (WebDriverException e) {
            throwCustomExceptionIfBinaryWasNotFound(e);
            throw e;
        }
    }

    private OperaOptions getInitializedOperaOptions() {
        if (this.operaOptions == null) {
            this.operaOptions = new OperaOptions();
        }
        return this.operaOptions;
    }

    private void throwCustomExceptionIfExecutableWasNotFound(IllegalStateException e) {
        if (e.getMessage().contains("path to the driver executable must be set")) {
            throw new SeleniumQueryException("The OperaDriver server executable was not found. Try using:\n\n" +
                "$.driver().useOpera().autoDriverDownload();\n\n" +
                "Or download and configure it manually from the Microsoft WebDriver page: https://developer.microsoft.com/en-us/microsoft-opera/tools/webdriver/#downloads",
            e);
        }
    }

    private void throwCustomExceptionIfBinaryWasNotFound(WebDriverException e) {
        if (e.getMessage().contains("cannot find Opera binary")) {
            throw new SeleniumQueryException(
                "The Opera browser BINARY was not found. Where is it?\n" +
                "Find out where it is and use .withBinary(<path-to-opera-binary>). Example:\n\n" +
                "$.driver().useOpera().withBinary(\"C:/Program Files/Opera/49.0.2725.47/opera.exe\").autoDriverDownload();\n\n\n" +
                "For more info, check the exception stack below.\n" +
                "\nOriginal error: " + e.getClass() + ": " + e.getMessage(),
            e);
        }
    }

}
