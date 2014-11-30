package io.github.seleniumquery.browser.driver.builders;

import io.github.seleniumquery.browser.driver.DriverBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Builds ChromeDriver instances for SeleniumQueryDriver.
 *
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class ChromeDriverBuilder extends DriverBuilder<ChromeDriverBuilder> {

    private String customPathToChromeDriverExe;

    /**
     * Looks for the chromedriver.exe at the path specified by the <code>pathToChromeDriverExe</code>
     * argument.
     *
     * @param pathToChromeDriverExe The full path to the executable server file. Examples:
     *     <code>"C:\\myFiles\\chromedriver.exe"</code>; can be relative, as in <code>"..\\stuff\\chromedriver.exe"</code>,
     *     does not matter if the executable was renamed, such as <code>"drivers\\chrome\\chromedriver_v12345.exe"</code>.
     */
    public ChromeDriverBuilder withPathToChromeDriverExe(String pathToChromeDriverExe) {
        this.customPathToChromeDriverExe = pathToChromeDriverExe;
        return this;
    }

    @Override
    protected WebDriver build() {
        if (this.customPathToChromeDriverExe != null) {
            return instantiateChromeDriverWithPath(this.customPathToChromeDriverExe);
        }
        return instantiateChromeDriverWithoutPath();
    }

    private WebDriver instantiateChromeDriverWithoutPath() {
        return instantiateChromeDriverWithPath(DriverInstantiationUtils.getFullPathForFileInClasspath("chromedriver.exe"));
    }

    private WebDriver instantiateChromeDriverWithPath(String pathToChromeDriverExe) {
        return DriverInstantiationUtils.instantiateDriverWithPath(pathToChromeDriverExe,
                "Chrome Driver Server",
                "http://chromedriver.storage.googleapis.com/index.html",
                "$.driver().useChrome().withPathToChromeDriverExe(\"other/path/to/chromedriver.exe\")",
                "webdriver.chrome.driver",
                ChromeDriver.class);
    }

}