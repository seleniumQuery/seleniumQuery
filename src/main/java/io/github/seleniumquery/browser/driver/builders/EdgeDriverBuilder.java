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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.seleniumquery.SeleniumQueryException;
import io.github.seleniumquery.browser.driver.DriverBuilder;

/**
 * Builds {@link EdgeDriver} instances for {@link io.github.seleniumquery.browser.driver.SeleniumQueryDriver}.
 *
 * @author acdcjunior
 * @since 0.18.0
 */
public class EdgeDriverBuilder extends DriverBuilder<EdgeDriverBuilder> {

    private static final Log LOGGER = LogFactory.getLog(EdgeDriverBuilder.class);

    private EdgeOptions edgeOptions;

    /**
     * Sets the {@link DesiredCapabilities} to the driver being built.
     *
     * @param desiredCapabilities The desired capabilities object.
     * @return The current builder instance, for additional configuration, if needed.
     * @since 0.18.0
     * @deprecated Prefer using {@link EdgeOptions} and {@link EdgeDriverBuilder#withOptions(EdgeOptions)} instead.
     */
    @Override
    @Deprecated
    public EdgeDriverBuilder withCapabilities(DesiredCapabilities desiredCapabilities) {
        return super.withCapabilities(desiredCapabilities);
    }

    /**
     * Sets specific {@link EdgeOptions} options to be used in the {@link EdgeDriver}.
     *
     * @param edgeOptions Options to be used.
     * @return A self reference, allowing further configuration.
     * @since 0.18.0
     */
    public EdgeDriverBuilder withOptions(EdgeOptions edgeOptions) {
        this.edgeOptions = edgeOptions;
        return this;
    }

    @Override
    protected WebDriver build() {
        autoDownloadDriverIfAskedFor(EdgeDriver.class);
        if (isCapabilitiesManuallySet()) {
            LOGGER.warn("Prefer Prefer using EdgeOptions and .withOptions() instead of DesiredCapabilities and " +
                ".withCapabilities().");
            return buildUsingCapabilities();
        } else {
            return buildUsingEdgeOptions();
        }
    }

    @SuppressWarnings("deprecation")
    private WebDriver buildUsingCapabilities() {
        DesiredCapabilities capabilities = capabilities(DesiredCapabilities.edge());
        overwriteCapabilityIfValueNotNull(capabilities, EdgeOptions.CAPABILITY, this.edgeOptions);

        try {
            return new EdgeDriver(capabilities);
        } catch (IllegalStateException e) {
            throwCustomExceptionIfExecutableWasNotFound(e);
            throw e;
        }
    }

    private WebDriver buildUsingEdgeOptions() {
        try {
            return new EdgeDriver(getInitializedEdgeOptions());
        } catch (IllegalStateException e) {
            throwCustomExceptionIfExecutableWasNotFound(e);
            throw e;
        }
    }

    private EdgeOptions getInitializedEdgeOptions() {
        if (this.edgeOptions == null) {
            this.edgeOptions = new EdgeOptions();
        }
        return this.edgeOptions;
    }

    private void throwCustomExceptionIfExecutableWasNotFound(IllegalStateException e) {
        if (e.getMessage().contains("path to the driver executable must be set")) {
            throw new SeleniumQueryException("The EdgeDriver server executable was not found. Try using:\n\n" +
                "$.driver().useEdge().autoDriverDownload();\n\n" +
                "Or download and configure it manually from the Microsoft WebDriver page: https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/#downloads"
            );
        }
    }

}
