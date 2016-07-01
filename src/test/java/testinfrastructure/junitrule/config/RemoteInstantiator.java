package testinfrastructure.junitrule.config;

import io.github.seleniumquery.browser.BrowserFunctions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.String.format;

public class RemoteInstantiator extends DriverInstantiator {

    public static RemoteInstantiator REMOTE_CHROME = new RemoteInstantiator("Chrome", DesiredCapabilities.chrome());
    public static RemoteInstantiator REMOTE_FIREFOX = new RemoteInstantiator("Firefox", DesiredCapabilities.firefox());
    public static RemoteInstantiator REMOTE_IE_10 = ie("10", "Windows 7");
    public static RemoteInstantiator REMOTE_IE_11 = ie("11", "Windows 8.1");
    public static RemoteInstantiator REMOTE_SAFARI = new RemoteInstantiator("Safari", DesiredCapabilities.safari());
    public static RemoteInstantiator REMOTE_EDGE = new RemoteInstantiator("Edge", DesiredCapabilities.edge(), "Windows 10");

    @SuppressWarnings("deprecation")
    public static RemoteInstantiator REMOTE_OPERA = new RemoteInstantiator("Opera", DesiredCapabilities.opera(), "Windows 7");

    private static RemoteInstantiator ie(String version, String platform) {
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.internetExplorer();
        desiredCapabilities.setCapability("platform", platform);
        desiredCapabilities.setCapability("version", version);
        return new RemoteInstantiator("IE " + version + " on " + platform, desiredCapabilities);
    }

    private final DesiredCapabilities capabilities;

    private RemoteInstantiator(String driverDescription, DesiredCapabilities desiredCapabilities) {
        super("Remote " + driverDescription);
        capabilities = desiredCapabilities;
    }

    private RemoteInstantiator(String driverDescription, DesiredCapabilities desiredCapabilities, String platform) {
        super("Remote " + driverDescription + " on " + platform);
        capabilities = desiredCapabilities;
        capabilities.setCapability("platform", platform);
    }

    @Override public void instantiateDriver(BrowserFunctions $) {
        try {
            String sauceUser = System.getenv("SAUCE_USERNAME");
            String sauceKey = System.getenv("SAUCE_ACCESS_KEY");

            RemoteWebDriver remoteChrome = new RemoteWebDriver(new URL(format("http://%s:%s@ondemand.saucelabs.com:80/wd/hub", sauceUser, sauceKey)), capabilities);
            $.driver().use(remoteChrome);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
