package io.github.seleniumquery.globalfunctions;

import org.openqa.selenium.WebDriver;

public class SeleniumQueryGlobalDriver {

    private SeleniumQueryBrowser seleniumQueryBrowser;

    public SeleniumQueryGlobalDriver(SeleniumQueryBrowser seleniumQueryBrowser) {
        this.seleniumQueryBrowser = seleniumQueryBrowser;
    }

    public void useHtmlUnit() {
        seleniumQueryBrowser.setDefaultDriver(seleniumQueryBrowser.driverInstantiationUtils.instantiateHtmlUnitDriverWithoutPath());
    }

    public void useFirefox() {
        seleniumQueryBrowser.setDefaultDriver(seleniumQueryBrowser.driverInstantiationUtils.instantiateFirefoxDriverWithoutPath());
    }

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
     * <p><b> This method looks for the chromedriver.exe at the CLASSPATH.</b> If you wish to directly specify a path,
     * use {@link #useChrome(String)}</p>
     * <p>
     * For more info, see <a href="https://code.google.com/p/selenium/wiki/ChromeDriver">ChromeDriver's official wiki</a>.
     * </p>
     */
    public void useChrome() {
        seleniumQueryBrowser.setDefaultDriver(seleniumQueryBrowser.driverInstantiationUtils.instantiateChromeDriverWithoutPath());
    }

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
    public void useChrome(String pathToChromeDriverExe) {
        seleniumQueryBrowser.setDefaultDriver(seleniumQueryBrowser.driverInstantiationUtils.instantiateChromeDriverWithPath(pathToChromeDriverExe));
    }

    /**
     * This method looks for the IEDriverServer.exe at the CLASSPATH. (Tipically at a resources/ folder of a
     * maven project.)
     */
    public void useInternetExplorer() {
        seleniumQueryBrowser.setDefaultDriver(seleniumQueryBrowser.driverInstantiationUtils.instantiateIeDriverWithoutPath());
    }

    /**
     * Sets IE as the default driver for seleniumQuery.
     * <p>
     * Note that, as IE needs a "server" to bridge selenium to the browser, you have
     * to point the path to it. It is a file usually named "IEDriverServer.exe" and its latest
     * version can be downloaded from http://selenium-release.storage.googleapis.com/index.html.
     * </p>
     * <p>
     * For more info, check https://code.google.com/p/selenium/wiki/InternetExplorerDriver
     * </p>
     *
     * @param pathToIEDriverServerExe The full path to the IEDriverServer.exe file.
     */
    public void useInternetExplorer(String pathToIEDriverServerExe) {
        seleniumQueryBrowser.setDefaultDriver(seleniumQueryBrowser.driverInstantiationUtils.instantiateIeDriverWithPath(pathToIEDriverServerExe));
    }

    public void usePhantomJS() {
        seleniumQueryBrowser.setDefaultDriver(seleniumQueryBrowser.driverInstantiationUtils.instantiatePhantomJsDriverWithoutPath());
    }

    public void usePhantomJS(String pathToPhantomJs) {
        seleniumQueryBrowser.setDefaultDriver(seleniumQueryBrowser.driverInstantiationUtils.instantiatePhantomJsDriverWithPath(pathToPhantomJs));
    }

}