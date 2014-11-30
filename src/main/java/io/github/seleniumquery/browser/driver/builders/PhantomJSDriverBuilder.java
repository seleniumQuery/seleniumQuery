package io.github.seleniumquery.browser.driver.builders;

import io.github.seleniumquery.browser.driver.DriverBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.io.File;

/**
 * Builds PhantomJSDriver instances for SeleniumQueryDriver.
 *
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class PhantomJSDriverBuilder extends DriverBuilder<PhantomJSDriverBuilder> {

    private String customPathToPhantomJs;

    /**
     * Sets the path used by the PhantomJSDriver to find the phantomjs executable.
     * @param pathToPhantomJs Path to phantomjs.exe.
     * @return A self reference.
     */
    public PhantomJSDriverBuilder withPathToPhantomJsExe(String pathToPhantomJs) {
        this.customPathToPhantomJs = pathToPhantomJs;
        return this;
    }

    @Override
    protected WebDriver build() {
        if (this.customPathToPhantomJs != null) {
            return instantiatePhantomJsDriverWithPath(this.customPathToPhantomJs);
        }
        return instantiatePhantomJsDriverWithoutPath();
    }

    private WebDriver instantiatePhantomJsDriverWithPath(String pathToPhantomJs) {
        try {
            File driverServerExecutableFile = new File(pathToPhantomJs);
            String driverServerExecutableFilePath = driverServerExecutableFile.getCanonicalPath();

            if (!driverServerExecutableFile.exists() || driverServerExecutableFile.isDirectory()) {
                throw new RuntimeException("No " + "PhantomJS Executable" + " file was found at '" +
                        driverServerExecutableFilePath + "'. Download the latest release at " + "http://phantomjs.org/download.html"
                        + " and place it there or specify a different path using " + "$.driver.usePhantomJS().withPath(\"other/path/to/PhantomJS.exe\")" + ".");
            }
            System.setProperty("phantomjs.binary.path", driverServerExecutableFilePath);
            return PhantomJSDriver.class.newInstance();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private WebDriver instantiatePhantomJsDriverWithoutPath() {
        return instantiatePhantomJsDriverWithPath(DriverInstantiationUtils.getFullPathForFileInClasspath("phantomjs.exe"));
    }

}