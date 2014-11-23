package io.github.seleniumquery.globalfunctions.driver.builders;

import io.github.seleniumquery.globalfunctions.driver.DriverBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

/**
 * Builds PhantomJSDriver instances for SeleniumQueryDriver.
 *
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class PhantomJSDriverBuilder extends DriverBuilder {

    private String customPathToPhantomJs;

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
        return DriverInstantiationUtils.instantiateDriverWithPath(pathToPhantomJs,
                "PhantomJS Executable",
                "http://phantomjs.org/download.html",
                "$.driver.usePhantomJS().withPath(\"other/path/to/PhantomJS.exe\")",
                "phantomjs.binary.path",
                PhantomJSDriver.class);
    }

    private WebDriver instantiatePhantomJsDriverWithoutPath() {
        return instantiatePhantomJsDriverWithPath(DriverInstantiationUtils.getFullPathForFileInClassPath("phantomjs.exe"));
    }

}