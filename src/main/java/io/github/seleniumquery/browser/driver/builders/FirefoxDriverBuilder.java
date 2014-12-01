package io.github.seleniumquery.browser.driver.builders;

import io.github.seleniumquery.browser.driver.DriverBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Builds FirefoxDriver instances for SeleniumQueryDriver.
 *
 * @author acdcjunior
 *
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
     * Sets specific {@link org.openqa.selenium.firefox.FirefoxProfile} to be used in the {@link org.openqa.selenium.firefox.FirefoxDriver}.
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
        capabilities.setCapability("firefox_profile", profile);

        return new FirefoxDriver(capabilities);
    }

}