package testinfrastructure.testutils;

import io.github.seleniumquery.utils.DriverVersionUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * No test code may use {@link DriverVersionUtils} directly to test the browser under test, because it may
 * have had its behavior overridden. Test code should use this class, that ensures {@link DriverVersionUtils}'s
 * behavior is reset before using it.
 */
public class DriverInTest {

    public static void restoreDriverVersionUtilsInstance() {
        // restore an eventually overridden (during other tests) instance
        DriverVersionUtils.setInstance(new DriverVersionUtils());
    }

    public static boolean isHtmlUnitDriverEmulatingIEBelow11(WebDriver driver) {
        restoreDriverVersionUtilsInstance();
        return DriverVersionUtils.isHtmlUnitDriverEmulatingIEBelow11(driver);
    }

    public static boolean isHtmlUnitDriver(WebDriver driver) {
        restoreDriverVersionUtilsInstance();
        return DriverVersionUtils.getInstance().isHtmlUnitDriver(driver);
    }

    public static boolean isNotHtmlUnitDriver(WebDriver driver) {
        return !isHtmlUnitDriver(driver);
    }

    public static boolean isIEDriver(WebDriver driver) {
        return driver instanceof InternetExplorerDriver;
    }

    public static boolean isHtmlUnitDriverEmulatingIE(WebDriver webDriver) {
        restoreDriverVersionUtilsInstance();
        return DriverVersionUtils.isHtmlUnitDriverEmulatingIE(webDriver);
    }

}