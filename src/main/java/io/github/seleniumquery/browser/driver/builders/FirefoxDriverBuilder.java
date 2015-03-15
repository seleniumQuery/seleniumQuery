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

package io.github.seleniumquery.browser.driver.builders;

import io.github.seleniumquery.browser.driver.DriverBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Builds {@link FirefoxDriver} instances for SeleniumQueryDriver.
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class FirefoxDriverBuilder extends DriverBuilder<FirefoxDriverBuilder> {

    private Boolean enableJavaScript;
    private FirefoxProfile firefoxProfile;

    /**
     * Configures Firefox to have JavaScript disabled.
     *
     * @return A self reference.
     *
     * @since 0.9.0
     */
    public FirefoxDriverBuilder withoutJavaScript() {
        this.enableJavaScript = false;
        return this;
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
        DesiredCapabilities capabilities = capabilities(DesiredCapabilities.firefox());

        FirefoxProfile profile = this.firefoxProfile != null ? this.firefoxProfile : new FirefoxProfile();
        if (enableJavaScript != null) {
            profile.setPreference("javascript.enabled", this.enableJavaScript);
        }
        capabilities.setCapability(FirefoxDriver.PROFILE, profile);

        return new FirefoxDriver(capabilities);
    }

}