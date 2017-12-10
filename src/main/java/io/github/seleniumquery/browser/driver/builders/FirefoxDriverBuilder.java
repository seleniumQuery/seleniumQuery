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

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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

    private FirefoxProfile firefoxProfile;

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

    /**
     * Sets specific {@link FirefoxProfile} to be used in the {@link FirefoxDriver}.
     *
     * @param firefoxProfile Profile to be used.
     * @return A self reference, allowing further configuration.
     *
     * @since 0.9.0
     */
    public FirefoxDriverBuilder withProfile(FirefoxProfile firefoxProfile) {
        this.firefoxProfile = firefoxProfile;
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
        DesiredCapabilities capabilities = createConfiguredCapabilities();
        return new FirefoxDriver(capabilities);
    }

    private DesiredCapabilities createConfiguredCapabilities() {
        DesiredCapabilities capabilities = capabilities(DesiredCapabilities.firefox());
        configureFirefoxProfile(capabilities);
        return capabilities;
    }

    private void configureFirefoxProfile(DesiredCapabilities capabilities) {
        FirefoxProfile profile = this.firefoxProfile != null ? this.firefoxProfile : new FirefoxProfile();
        capabilities.setCapability(FirefoxDriver.PROFILE, profile);
    }

}
