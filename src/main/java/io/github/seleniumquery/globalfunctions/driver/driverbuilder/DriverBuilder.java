package io.github.seleniumquery.globalfunctions.driver.driverbuilder;

import org.openqa.selenium.WebDriver;

public abstract class DriverBuilder {

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
