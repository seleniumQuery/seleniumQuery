package endtoend.browser.util;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.junit.Assert.assertEquals;
import static testinfrastructure.EndToEndTestUtils.classNameToTestFileUrl;

import org.openqa.selenium.WebDriver;

public class HeadlessTestUtils {

    public static void assertHeadlessYes(WebDriver driver) {
        assertTitle(driver, "headless:true");
    }

    public static void assertHeadlessNot(WebDriver driver) {
        assertTitle(driver, "headless:false");
    }

    private static void assertTitle(WebDriver driver, String expected) {
        driver.get(classNameToTestFileUrl(HeadlessTestUtils.class));
        $.pause(100);
        assertEquals(expected, $.title());
    }

}
