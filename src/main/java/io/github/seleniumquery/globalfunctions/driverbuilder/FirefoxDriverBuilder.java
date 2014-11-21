package io.github.seleniumquery.globalfunctions.driverbuilder;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import static io.github.seleniumquery.SeleniumQuery.$;

public class FirefoxDriverBuilder {

    private FirefoxProfile firefoxProfile;
    private Boolean javaScriptEnabled;

    public FirefoxDriverBuilder withoutJavaScript() {
        this.javaScriptEnabled = false;
        return this;
    }

    public FirefoxDriverBuilder withJavaScript() {
        this.javaScriptEnabled = true;
        return this;
    }

    public FirefoxDriverBuilder withProfile(FirefoxProfile firefoxProfile) {
        this.firefoxProfile = firefoxProfile;
        return this;
    }

    protected FirefoxDriver instantiate() {
        FirefoxProfile effectiveFirefoxProfile = this.firefoxProfile != null ? this.firefoxProfile : new FirefoxProfile();
        if (this.javaScriptEnabled != null) {
            effectiveFirefoxProfile.setPreference("javascript.enabled", this.javaScriptEnabled);
        }
        return new FirefoxDriver(effectiveFirefoxProfile);
    }

}