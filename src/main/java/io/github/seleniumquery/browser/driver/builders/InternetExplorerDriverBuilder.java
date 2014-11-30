package io.github.seleniumquery.browser.driver.builders;

import io.github.seleniumquery.SeleniumQueryException;
import io.github.seleniumquery.browser.driver.DriverBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.SessionNotFoundException;

import java.io.File;
import java.lang.IllegalStateException;

import static io.github.seleniumquery.browser.driver.builders.DriverInstantiationUtils.getFullPath;
import static io.github.seleniumquery.browser.driver.builders.DriverInstantiationUtils.getFullPathForFileInClasspath;

/**
 * Builds InternetExplorerDriver instances for SeleniumQueryDriver.
 *
 * @author acdcjunior
 *
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

    static String IEDRIVERSERVER_EXE = "IEDriverServer.exe"; // package visibility so it can be changed during test

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

        if (customPathWasProvidedAndExecutableExistsThere()) {
            System.setProperty(IE_DRIVER_EXECUTABLE_SYSTEM_PROPERTY, getFullPath(this.customPathToIEDriverServerExe));
        } else if (DriverInstantiationUtils.executableExistsInClasspath(IEDRIVERSERVER_EXE)) {
            System.setProperty(IE_DRIVER_EXECUTABLE_SYSTEM_PROPERTY, getFullPathForFileInClasspath(IEDRIVERSERVER_EXE));
        }
        try {
            return new InternetExplorerDriver(capabilities);
        } catch (IllegalStateException e) {
            if (e.getMessage().contains("path to the driver executable must be set")) {
                throw new SeleniumQueryException(
                        "The IEDriverServer executable ("+IEDRIVERSERVER_EXE+") was not found in the classpath," +
                                " in the \""+IE_DRIVER_EXECUTABLE_SYSTEM_PROPERTY+"\" system property or in the system's PATH variable."
                                +EXCEPTION_MESSAGE, e);
            }
            throw e;
        } catch (SessionNotFoundException e) {
            String message = e.getLocalizedMessage();
            if (message != null && message.contains("Protected Mode")) {
                throw new SeleniumQueryException("IE Driver requires Protected Mode settings to be the same for all zones. Go to\n\t\t" +
                        "'Tools' -> 'Internet Options' -> 'Security Tab', and set all zones to the same protected mode," +
                        " be it enabled or disabled, does not matter.\n\t\t" +
                        "If this does not solve the problem, or for more info, check our IE Driver wiki page at: " +
                        "https://github.com/seleniumQuery/seleniumQuery/wiki/seleniumQuery-and-IE-Driver", e);
            }
            throw e;
        }
    }

    private boolean customPathWasProvidedAndExecutableExistsThere() {
        boolean customPathWasProvided = this.customPathToIEDriverServerExe != null;
        if (!customPathWasProvided) {
            return false;
        }
        File driverServerExecutableFile = new File(this.customPathToIEDriverServerExe);
        if (!DriverInstantiationUtils.isValidFile(driverServerExecutableFile)) {
            throw new SeleniumQueryException(
                    "The IEDriverServer executable file was not found (or is a directory) at \"" +
                            getFullPath(this.customPathToIEDriverServerExe) + "\"." + EXCEPTION_MESSAGE);
        }
        return true;
    }

}