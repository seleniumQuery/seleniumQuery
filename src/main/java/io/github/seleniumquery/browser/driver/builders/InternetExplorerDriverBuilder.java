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

import io.github.seleniumquery.SeleniumQueryException;
import io.github.seleniumquery.browser.driver.DriverBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.SessionNotFoundException;

import static io.github.seleniumquery.browser.driver.builders.DriverInstantiationUtils.*;
import static java.lang.String.format;

/**
 * Builds {@link InternetExplorerDriver} instances for SeleniumQueryDriver.
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class InternetExplorerDriverBuilder extends DriverBuilder<InternetExplorerDriverBuilder> {

    private static final String IE_DRIVER_EXECUTABLE_SYSTEM_PROPERTY = "webdriver.ie.driver";

    private static final String EXCEPTION_MESSAGE = " \nDownload the latest release at http://selenium-release.storage.googleapis.com/index.html and place it: \n" +
            "(1) on the classpath of this project; or\n" +
            "(2) on the path specified by the \"" + IE_DRIVER_EXECUTABLE_SYSTEM_PROPERTY + "\" system property; or\n" +
            "(3) on a folder in the system's PATH variable; or\n" +
            "(4) wherever and set the path via $.driver().useInternetExplorer().withPathToIEDriverServerExe(\"other/path/to/IEDriverServer.exe\").\n" +
            "For more information, see https://github.com/seleniumQuery/seleniumQuery/wiki/seleniumQuery-and-IE-Driver";

    private static final String BAD_PATH_PROVIDED_EXCEPTION_MESSAGE = "The IEDriverServer executable file was not found (or is a directory) at \"%s\"." + EXCEPTION_MESSAGE;

    public static String IEDRIVERSERVER_EXE = "IEDriverServer.exe"; // not final so it can be changed during test

    private String customPathToIEDriverServerExe;

    /**
     * Configures the builder to look for the <code>IEDriverServer.exe</code> at the path specified by the argument.
     *
     * @param pathToIEDriverServerExe The path to the executable server file. Examples:
     *     <code>"C:\\myFiles\\IEDriverServer.exe"</code>; can be relative, as in <code>"..\\stuff\\IEDriverServer.exe"</code>;
     *     does not matter if the executable was renamed, such as <code>"drivers\\ie\\iedriverserver_v12345.exe"</code>.
     *
     * @return A self reference, allowing further configuration.
     *
     * @since 0.9.0
     */
    public InternetExplorerDriverBuilder withPathToIEDriverServerExe(String pathToIEDriverServerExe) {
        this.customPathToIEDriverServerExe = pathToIEDriverServerExe;
        return this;
    }

    @Override
    protected WebDriver build() {
        DesiredCapabilities capabilities = capabilities(DesiredCapabilities.chrome());

        configureIEServerExecutablePath();
        try {
            return new InternetExplorerDriver(capabilities);
        } catch (IllegalStateException e) {
            throwCustomExceptionIfExecutableNotFound(e);
            throw e;
        } catch (SessionNotFoundException e) {
            throwCustomExceptionIfProtectedModeMustBeTheSame(e);
            throw e;
        }
    }

    private void configureIEServerExecutablePath() {
        if (customPathWasProvidedAndExecutableExistsThere(this.customPathToIEDriverServerExe , BAD_PATH_PROVIDED_EXCEPTION_MESSAGE)) {
            System.setProperty(IE_DRIVER_EXECUTABLE_SYSTEM_PROPERTY, getFullPath(this.customPathToIEDriverServerExe));
        } else if (DriverInstantiationUtils.executableExistsInClasspath(IEDRIVERSERVER_EXE)) {
            System.setProperty(IE_DRIVER_EXECUTABLE_SYSTEM_PROPERTY, getFullPathForFileInClasspath(IEDRIVERSERVER_EXE));
        }
    }

    private void throwCustomExceptionIfExecutableNotFound(IllegalStateException e) {
        if (e.getMessage().contains("path to the driver executable must be set")) {
            throw new SeleniumQueryException(
                    format(
                            "The IEDriverServer executable (%s) was not found in the classpath, in the \"%s\" system property or in the system's PATH variable.%s",
                            IEDRIVERSERVER_EXE, IE_DRIVER_EXECUTABLE_SYSTEM_PROPERTY, EXCEPTION_MESSAGE
                    ), e);
        }
    }

    private void throwCustomExceptionIfProtectedModeMustBeTheSame(SessionNotFoundException e) {
        String message = e.getLocalizedMessage();
        if (message != null && message.contains("Protected Mode")) {
            throw new SeleniumQueryException("IE Driver requires Protected Mode settings to be the same for all zones. Go to\n\t\t" +
                    "'Tools' -> 'Internet Options' -> 'Security Tab', and set all zones to the same protected mode," +
                    " be it enabled or disabled, does not matter.\n\t\t" +
                    "If this does not solve the problem, or for more info, check our IE Driver wiki page at: " +
                    "https://github.com/seleniumQuery/seleniumQuery/wiki/seleniumQuery-and-IE-Driver", e);
        }
    }

}