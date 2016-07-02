package testinfrastructure.junitrule.statement;

import io.github.seleniumquery.browser.BrowserFunctions;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;
import testinfrastructure.EndToEndTestUtils;
import testinfrastructure.junitrule.TestClassSession;
import testinfrastructure.junitrule.annotation.*;
import testinfrastructure.testutils.DriverInTest;
import testinfrastructure.testutils.SauceLabsUtils;

public class TestMethodStatement extends Statement {

    private final TestClassSession testClassSession;
    private final Statement base;
    private final Description description;
    private final BrowserFunctions browser;

    public TestMethodStatement(TestClassSession testClassSession,
                               Statement base,
                               Description description) {
        this.testClassSession = testClassSession;
        this.base = base;
        this.description = description;
        this.browser = testClassSession.getBrowser();
    }

    @Override
    public void evaluate() throws Throwable {
        if (shouldSkipTest()) {
            return;
        }
        testClassSession.log("\t@## > Opening URL " + testClassSession.getUrl());
        EndToEndTestUtils.openUrl(testClassSession.getUrl());
        try {
            base.evaluate();
            SauceLabsUtils.reportTestSuccess();
        } catch (Throwable throwable) {
            SauceLabsUtils.reportTestFailure();
            testClassSession.recordFailure(throwable);
            throw throwable;
        }
    }

    private boolean shouldSkipTest() {
        WebDriver currentDriver = browser.driver().get();
        if (description.getAnnotation(JavaScriptEnabledOnly.class) != null && testClassSession.driverHasJavaScriptDisabled()) {
            printSkipReason("JavaScript Enabled-only", this.description, currentDriver);
            return true;
        }
        if (description.getAnnotation(JavaScriptDisabledOnly.class) != null && !testClassSession.driverHasJavaScriptDisabled()) {
            printSkipReason("JavaScript Disabled-only", this.description, currentDriver);
            return true;
        }
        if (description.getAnnotation(ChromeShouldBeSkipped.class) != null && DriverInTest.isChromeDriver(currentDriver)) {
            printSkipReason("Chrome-skipped", description, currentDriver);
            return true;
        }
        if (description.getAnnotation(ChromeOnly.class) != null && !DriverInTest.isChromeDriver(currentDriver)) {
            printSkipReason("Chrome-only", description, currentDriver);
            return true;
        }
        if (description.getAnnotation(EdgeOnly.class) != null && !DriverInTest.isEdgeDriver(currentDriver)) {
            printSkipReason("Edge-only", description, currentDriver);
            return true;
        }
        if (description.getAnnotation(FirefoxOnly.class) != null && !DriverInTest.isFirefoxDriver(currentDriver)) {
            printSkipReason("Firefox-only", description, currentDriver);
            return true;
        }
        if (description.getAnnotation(FirefoxSkip.class) != null && DriverInTest.isFirefoxDriver(currentDriver)) {
            printSkipReason("Firefox-skipped", description, currentDriver);
            return true;
        }
        if (description.getAnnotation(HtmlUnitOnly.class) != null && !DriverInTest.isHtmlUnitDriver(currentDriver)) {
            printSkipReason("HtmlUnit-only", description, currentDriver);
            return true;
        }
        if (description.getAnnotation(IEOnly.class) != null && !DriverInTest.isIEDriver(currentDriver)) {
            printSkipReason("IE-only", description, currentDriver);
            return true;
        }
        if (description.getAnnotation(SafariSkip.class) != null && DriverInTest.isSafariDriver(currentDriver)) {
            printSkipReason("Safari-skipped", description, currentDriver);
            return true;
        }
        if (description.getAnnotation(SafariOnly.class) != null && !DriverInTest.isSafariDriver(currentDriver)) {
            printSkipReason("Safari-only", description, currentDriver);
            return true;
        }
        // TODO opera
        if (description.getAnnotation(PhantomJSOnly.class) != null && !DriverInTest.isPhantomJSDriver(currentDriver)) {
            printSkipReason("PhantomJS-only", description, currentDriver);
            return true;
        }
        return false;
    }

    private void printSkipReason(String reason, Description description, WebDriver driver) {
        testClassSession.log(String.format("\t@## -> Skipping %s test: %s (driver %s)", reason, description, driver));
    }

}
