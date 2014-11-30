package io.github.seleniumquery.globalfunctions.driver.builders;

import io.github.seleniumquery.globalfunctions.driver.DriverBuilder;
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

    private String custoPathToChromeDriverExe;

    /**
     * Sets <b>Chrome</b> as the default {@link WebDriver} for seleniumQuery.
     * <p>
     * Note that the Chrome driver needs a <i>server executable</i> to bridge Selenium to the browser and as such
     * Selenium must have the path to it. It is a file usually named <code>chromedriver.exe</code> and its latest
     * version can be downloaded from <a href="http://chromedriver.storage.googleapis.com/index.html">ChromeDriver's
     * download page</a> -- or check <a
     * href="https://github.com/seleniumQuery/seleniumQuery/wiki/seleniumQuery-and-Chrome-as-WebDriver-Browser">
     * seleniumQuery and Chrome as WebDriver/Browser wiki page</a> for the latest info.
     * </p>
     * <p><b> This method looks for the chromedriver.exe at the path specified by the <code>pathToChromeDriverExe</code>
     * argument.</b></p>
     * <p>
     * For more info, see <a href="https://code.google.com/p/selenium/wiki/ChromeDriver">ChromeDriver's official wiki</a>.
     * </p>
     *
     * @param pathToChromeDriverExe The full path to the executable server file. Examples:
     *     <code>"C:\\myFiles\\chromedriver.exe"</code>; can be relative, as in <code>"..\\stuff\\chromedriver.exe"</code>,
     *     does not matter if the .exe was renamed, such as <code>"drivers\\chrome\\chromedriver_v12345.exe"</code>.
     */
    public ChromeDriverBuilder withPathToChromeDriverExe(String pathToChromeDriverExe) {
        this.custoPathToChromeDriverExe = pathToChromeDriverExe;
        return this;
    }

    @Override
    protected WebDriver build() {
        if (this.custoPathToChromeDriverExe != null) {
            return instantiateChromeDriverWithPath(this.custoPathToChromeDriverExe);
        }
        return instantiateChromeDriverWithoutPath();
    }

    private WebDriver instantiateChromeDriverWithoutPath() {
        return instantiateChromeDriverWithPath(DriverInstantiationUtils.getFullPathForFileInClassPath("chromedriver.exe"));
    }

    private WebDriver instantiateChromeDriverWithPath(String pathToChromeDriverExe) {
        return DriverInstantiationUtils.instantiateDriverWithPath(pathToChromeDriverExe,
                "Chrome Driver Server",
                "http://chromedriver.storage.googleapis.com/index.html",
                "$.driver().useChrome().withPath(\"other/path/to/chromedriver.exe\")",
                "webdriver.chrome.driver",
                ChromeDriver.class);
    }

}