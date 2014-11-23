package io.github.seleniumquery.globalfunctions.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Builds WebDriver instances for SeleniumQueryDriver.
 *
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public abstract class DriverBuilder<T extends DriverBuilder> {

    protected DesiredCapabilities desiredCapabilities;

    @SuppressWarnings("unchecked")
    public T withCapabilities(DesiredCapabilities desiredCapabilities) {
        this.desiredCapabilities = desiredCapabilities;
        return (T) this;
    }

    protected DesiredCapabilities capabilities(DesiredCapabilities defaultDesiredCapabilities) {
        if (this.desiredCapabilities == null) {
            return defaultDesiredCapabilities;
        }
        return this.desiredCapabilities;
    }

    protected void overwriteCapabilityIfValueNotNull(DesiredCapabilities capabilities, String capabilityName, Object value) {
        if (value != null) {
            capabilities.setCapability(capabilityName, value);
        }
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

}