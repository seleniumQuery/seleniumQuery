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

import io.github.seleniumquery.SeleniumQuery;
import io.github.seleniumquery.browser.driver.DriverBuilder;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
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
        autoDownloadDriverIfAskedFor(FirefoxDriver.class);
        DesiredCapabilities capabilities = createConfiguredCapabilities();
        FirefoxDriver firefoxDriver = new FirefoxDriver(capabilities);
        disableJavaScriptIfWanted(firefoxDriver);
        return firefoxDriver;
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

    private void disableJavaScriptIfWanted(FirefoxDriver driver) {
        if (shouldDisableJavaScript()) {
            disableJavaScript(driver);
        }
    }

    private boolean shouldDisableJavaScript() {
        return Boolean.FALSE.equals(this.enableJavaScript);
    }

    @SuppressWarnings("deprecation")
    private void disableJavaScript(FirefoxDriver driver) {
        driver.get("about:config");
        Actions act = new Actions(driver);
        act.sendKeys(Keys.RETURN).sendKeys("javascript.enabled").perform();
        SeleniumQuery.$.pause(1000);
        act.sendKeys(Keys.TAB).sendKeys(Keys.RETURN).sendKeys(Keys.F5).perform();
        driver.get("about:blank");
    }

}
