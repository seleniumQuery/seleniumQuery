package io.github.seleniumquery.browser.driver.builders;

import io.github.seleniumquery.SeleniumQueryException;
import io.github.seleniumquery.browser.driver.DriverBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.lang.IllegalStateException;

import static io.github.seleniumquery.browser.driver.builders.DriverInstantiationUtils.*;
import static java.lang.String.format;

/**
 * Builds ChromeDriver instances for SeleniumQueryDriver.
 *
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class ChromeDriverBuilder extends DriverBuilder<ChromeDriverBuilder> {

    private static final String CHROME_DRIVER_EXECUTABLE_SYSTEM_PROPERTY = "webdriver.chrome.driver";

    private static final String EXCEPTION_MESSAGE = " \nDownload the latest release at http://chromedriver.storage.googleapis.com/index.html and place it: \n" +
            "(1) on the classpath of this project; or\n" +
            "(2) on the path specified by the \"" + CHROME_DRIVER_EXECUTABLE_SYSTEM_PROPERTY + "\" system property; or\n" +
            "(3) on a folder in the system's PATH variable; or\n" +
            "(4) wherever and set the path via $.driver().useChrome().withPathToChromeDriverExe(\"other/path/to/chromedriver.exe\").\n" +
            "For more information, see https://github.com/seleniumQuery/seleniumQuery/wiki/seleniumQuery-and-Chrome-Driver";

    private static final String BAD_PATH_PROVIDED_EXCEPTION_MESSAGE = "The ChromeDriver Server executable file was not found (or is a directory) at \"%s\"." + EXCEPTION_MESSAGE;

    static String CHROMEDRIVER_EXE = "chromedriver.exe"; // package visibility so it can be changed during test


    private String customPathToChromeDriverExe;
    private ChromeOptions chromeOptions;

    /**
     * Sets specific {@link ChromeOptions} options to be used in the {@link ChromeDriver}.
     * @param chromeOptions Options to be used.
     * @return A self reference, allowing further configuration.
     *
     * @since 0.9.0
     */
    public ChromeDriverBuilder withOptions(ChromeOptions chromeOptions) {
        this.chromeOptions = chromeOptions;
        return this;
    }

    /**
     * Configures the builder to look for the <code>chromedriver.exe</code> at the path specified by the argument.
     *
     * @param pathToChromeDriverExe The path to the executable server file. Examples:
     *     <code>"C:\\myFiles\\chromedriver.exe"</code>; can be relative, as in <code>"..\\stuff\\chromedriver.exe"</code>;
     *     does not matter if the executable was renamed, such as <code>"drivers\\chrome\\chromedriver_v12345.exe"</code>.
     *
     * @return A self reference, allowing further configuration.
     *
     * @since 0.9.0
     */
    public ChromeDriverBuilder withPathToChromeDriverExe(String pathToChromeDriverExe) {
        this.customPathToChromeDriverExe = pathToChromeDriverExe;
        return this;
    }

    @Override
    protected WebDriver build() {
        DesiredCapabilities capabilities = capabilities(DesiredCapabilities.chrome());
        overwriteCapabilityIfValueNotNull(capabilities, ChromeOptions.CAPABILITY, this.chromeOptions);

        if (customPathWasProvidedAndExecutableExistsThere(this.customPathToChromeDriverExe, BAD_PATH_PROVIDED_EXCEPTION_MESSAGE)) {
            System.setProperty(CHROME_DRIVER_EXECUTABLE_SYSTEM_PROPERTY, getFullPath(this.customPathToChromeDriverExe));
        } else if (executableExistsInClasspath(CHROMEDRIVER_EXE)) {
            System.setProperty(CHROME_DRIVER_EXECUTABLE_SYSTEM_PROPERTY, getFullPathForFileInClasspath(CHROMEDRIVER_EXE));
        }
        try {
            return new ChromeDriver(capabilities);
        } catch (IllegalStateException e) {
            throwCustomExceptionIfExecutableWasNotFound(e);
            throw e;
        }
    }

    private void throwCustomExceptionIfExecutableWasNotFound(IllegalStateException e) {
        if (e.getMessage().contains("path to the driver executable must be set")) {
            throw new SeleniumQueryException(
                format(
                        "The ChromeDriver server executable (%s) was not found in the classpath, in the \"%s\" system property or in the system's PATH variable. %s",
                        CHROMEDRIVER_EXE, CHROME_DRIVER_EXECUTABLE_SYSTEM_PROPERTY, EXCEPTION_MESSAGE
                ), e);
        }
    }

}