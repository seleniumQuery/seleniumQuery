package io.github.seleniumquery.globalfunctions.driver.builders;

import io.github.seleniumquery.globalfunctions.driver.DriverBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.lang.reflect.Constructor;

/**
 * Builds HtmlUnitDriver instances for SeleniumQueryDriver.
 *
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class HtmlUnitDriverBuilder extends DriverBuilder {

    @Override
    protected WebDriver build() {
        return new HtmlUnitDriver();
    }

}