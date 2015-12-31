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

import com.google.common.annotations.VisibleForTesting;
import io.github.seleniumquery.SeleniumQueryException;
import io.github.seleniumquery.browser.driver.DriverBuilder;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import static io.github.seleniumquery.browser.driver.builders.DriverInstantiationUtils.*;
import static java.lang.String.format;

/**
 * Builds {@link PhantomJSDriver} instances for SeleniumQueryDriver.
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class PhantomJSDriverBuilder extends DriverBuilder<PhantomJSDriverBuilder> {

    private static final Log LOGGER = LogFactory.getLog(PhantomJSDriverBuilder.class);

    public static final String PHANTOMJS_EXECUTABLE_SYSTEM_PROPERTY = "phantomjs.binary.path";

    private static final String EXCEPTION_MESSAGE = " \nDownload the latest release at http://phantomjs.org/download.html and place it: \n" +
            "(1) on the classpath of this project; or\n" +
            "(2) on the path specified by the \"" + PHANTOMJS_EXECUTABLE_SYSTEM_PROPERTY + "\" system property; or\n" +
            "(3) on a folder in the system's PATH variable; or\n" +
            "(4) on the path set in the \""+PHANTOMJS_EXECUTABLE_SYSTEM_PROPERTY+"\" capability; or\n" +
            "(5) wherever and set the path via $.driver.usePhantomJS().withPathToPhantomJS(\"other/path/to/phantomjs<.exe>\").\n" +
            "For more information, see https://github.com/seleniumQuery/seleniumQuery/wiki/seleniumQuery-and-PhantomJS-Driver";

    private static final String BAD_PATH_PROVIDED_EXCEPTION_MESSAGE = "The PhantomJS executable file was not found (or is a directory) at \"%s\"." + EXCEPTION_MESSAGE;

    private static final String PHANTOMJS_EXECUTABLE_WINDOWS = "phantomjs.exe";
    private static final String PHANTOMJS_EXECUTABLE_LINUX = "phantomjs";

    private String customPathToPhantomJs;

    private final boolean isWindowsOS;
    private final String phantomjsExecutableWindows;
    private final String phantomjsExecutableLinux;

    public PhantomJSDriverBuilder() {
        this(SystemUtils.IS_OS_WINDOWS, PHANTOMJS_EXECUTABLE_WINDOWS, PHANTOMJS_EXECUTABLE_LINUX);
    }

    public PhantomJSDriverBuilder(boolean isWindowsOS, String phantomjsExecutableWindows, String phantomjsExecutableLinux) {
        this.isWindowsOS = isWindowsOS;
        this.phantomjsExecutableWindows = phantomjsExecutableWindows;
        this.phantomjsExecutableLinux = phantomjsExecutableLinux;
    }

    /**
     * Sets the path used by the PhantomJSDriver to find the PhantomJS executable.
     * @param pathToPhantomJs Path to PhantomJS executable (<code>phantomjs.exe</code>/<code>phantomjs.exe</code>).
     * @return A self reference.
     */
    public PhantomJSDriverBuilder withPathToPhantomJS(String pathToPhantomJs) {
        this.customPathToPhantomJs = pathToPhantomJs;
        return this;
    }

    @Override
    protected WebDriver build() {
        DesiredCapabilities capabilities = capabilities(new DesiredCapabilities());

        configurePhantomJsExecutablePath();
        try {
            return new PhantomJSDriver(capabilities);
        } catch (IllegalStateException e) {
            throwCustomExceptionIfExecutableWasNotFound(e);
            throw e;
        }
    }

    @VisibleForTesting
    protected void configurePhantomJsExecutablePath() {
        if (customPathWasProvidedAndExecutableExistsThere(this.customPathToPhantomJs, BAD_PATH_PROVIDED_EXCEPTION_MESSAGE)) {
            setExecutableSystemProperty(getFullPath(this.customPathToPhantomJs));
        } else if (this.isWindowsOS && executableExistsInClasspath(phantomjsExecutableWindows)) {
            setExecutableSystemProperty(getFullPathForFileInClasspath(phantomjsExecutableWindows));
        } else if (!this.isWindowsOS && executableExistsInClasspath(phantomjsExecutableLinux)) {
            setExecutableSystemProperty(getFullPathForFileInClasspath(phantomjsExecutableLinux));
        }
    }

    private void setExecutableSystemProperty(String executableFullPath) {
        LOGGER.debug("Loading PhantomJS executable from "+executableFullPath);
        System.setProperty(PHANTOMJS_EXECUTABLE_SYSTEM_PROPERTY, executableFullPath);
    }

    private void throwCustomExceptionIfExecutableWasNotFound(IllegalStateException e) {
        if (e.getMessage().contains("path to the driver executable must be set")) {
            throw new SeleniumQueryException(
                    format(
                            "The PhantomJS executable (%s/%s) was not found in the classpath, in the \"%s\" system property or in the system's PATH variable. %s",
                            phantomjsExecutableWindows, phantomjsExecutableLinux, PHANTOMJS_EXECUTABLE_SYSTEM_PROPERTY, EXCEPTION_MESSAGE
                    ), e);
        }
    }

}