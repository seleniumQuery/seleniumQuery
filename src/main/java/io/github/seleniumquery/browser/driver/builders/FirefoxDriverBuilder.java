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
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.seleniumquery.SeleniumQueryException;
import io.github.seleniumquery.browser.driver.DriverBuilder;

/**
 * Builds {@link FirefoxDriver} instances for SeleniumQueryDriver.
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class FirefoxDriverBuilder extends DriverBuilder<FirefoxDriverBuilder> {

    private static final Log LOGGER = LogFactory.getLog(FirefoxDriverBuilder.class);

    private FirefoxOptions firefoxOptions;

    private FirefoxOptions getInitializedFirefoxOptions() {
        if (this.firefoxOptions == null) {
            this.firefoxOptions = new FirefoxOptions();
        }
        return this.firefoxOptions;
    }

    /**
     * Configures the driver with the given capabilities.
     * @param desiredCapabilities The capabilities to be set.
     * @return A self reference for further configuration.
     * @since 0.18.0
     */
    @Override
    public FirefoxDriverBuilder withCapabilities(DesiredCapabilities desiredCapabilities) {
        getInitializedFirefoxOptions().merge(desiredCapabilities);
        return this;
    }

    /**
     * Sets specific {@link FirefoxProfile} to be used in the {@link FirefoxDriver}.
     *
     * @param firefoxProfile Profile to be used.
     * @return A self reference, allowing further configuration.
     * @since 0.9.0
     */
    public FirefoxDriverBuilder withProfile(FirefoxProfile firefoxProfile) {
        getInitializedFirefoxOptions().setProfile(firefoxProfile);
        return this;
    }

    /**
     * <p>Sets specific {@link FirefoxOptions} to be used in the {@link FirefoxDriver}.</p>
     * <br>
     * This overwrites most configuration done by other options of driverbuilder. If you want to use it, it
     * is best to have it as first configuration of the driver builder, e.g.:
     * <pre><code>
     * // instead of
     * $.driver().useFirefox().withBinary(...).withCapabilities(...)<b>.withOptions(yourCustomOptions)</b>;
     * // do
     * $.driver().useFirefox()<b>.withOptions(yourCustomOptions)</b>.withBinary(...).withCapabilities(...);
     * </code></pre>
     *
     * @param firefoxOptions Options to be used.
     * @return A self reference, allowing further configuration.
     * @since 0.18.0
     */
    public FirefoxDriverBuilder withOptions(FirefoxOptions firefoxOptions) {
        if (this.firefoxOptions != null) {
            LOGGER.warn("FirefoxOptions has already been initialized. All previous configurations are being overwritten.");
        }
        this.firefoxOptions = firefoxOptions;
        return this;
    }

    /**
     * Sets specific {@link FirefoxBinary} to be used in the {@link FirefoxDriver}.
     *
     * @param firefoxBinary Binary to be used.
     * @return A self reference, allowing further configuration.
     * @since 0.18.0
     */
    public FirefoxDriverBuilder withBinary(FirefoxBinary firefoxBinary) {
        getInitializedFirefoxOptions().setBinary(firefoxBinary);
        return this;
    }

    /**
     * Configures {@link FirefoxDriver} to run in headless mode.
     *
     * @return A self reference, allowing further configuration.
     * @since 0.18.0
     */
    public FirefoxDriverBuilder headless() {
        getInitializedFirefoxOptions().setHeadless(true);
        return this;
    }

    @Override
    protected WebDriver build() {
        autoDownloadDriverIfAskedFor(FirefoxDriver.class);
        WebDriver webDriver = buildFirefox();
        autoQuitDriverIfAskedFor(webDriver);
        return webDriver;
    }

    private WebDriver buildFirefox() {
        return new FirefoxDriver(getInitializedFirefoxOptions());
    }

    /**
     * @deprecated Firefox (geckodriver) no longer supports disabling JavaScript. Without it, geckodriver simply
     * can't communicate with Firefox.
     *
     * @return A self reference.
     * @since 0.9.0
     */
    public FirefoxDriverBuilder withoutJavaScript() {
        throw new SeleniumQueryException("Firefox no longer supports disabling JavaScript. Without it, " +
            "geckodriver simply can't communicate with Firefox.");
    }

}
