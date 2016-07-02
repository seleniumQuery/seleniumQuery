package testinfrastructure.junitrule.statement;

import io.github.seleniumquery.browser.BrowserFunctions;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
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
        if (description.getAnnotation(JavaScriptEnabledOnly.class) != null && testClassSession.driverHasJavaScriptDisabled()) {
            printSkipReason("JavaScript Enabled-only", this.description);
            return true;
        }
        if (description.getAnnotation(JavaScriptDisabledOnly.class) != null && !testClassSession.driverHasJavaScriptDisabled()) {
            printSkipReason("JavaScript Disabled-only", this.description);
            return true;
        }
        if (description.getAnnotation(ChromeOnly.class) != null && !DriverInTest.isChromeDriver(browser.driver().get())) {
            printSkipReason("Chrome-only", description);
            return true;
        }
        // TODO edge
        if (description.getAnnotation(FirefoxOnly.class) != null && !DriverInTest.isFirefoxDriver(browser.driver().get())) {
            printSkipReason("Firefox-only", description);
            return true;
        }
        if (description.getAnnotation(HtmlUnitOnly.class) != null && !DriverInTest.isHtmlUnitDriver(browser.driver().get())) {
            printSkipReason("HtmlUnit-only", description);
            return true;
        }
        if (description.getAnnotation(IEOnly.class) != null && !DriverInTest.isIEDriver(browser.driver().get())) {
            printSkipReason("IE-only", description);
            return true;
        }
        // TODO opera
        if (description.getAnnotation(PhantomJSOnly.class) != null && !DriverInTest.isPhantomJSDriver(browser.driver().get())) {
            printSkipReason("PhantomJS-only", description);
            return true;
        }
        // TODO edge
        return false;
    }

    private void printSkipReason(String reason, Description description) {
        testClassSession.log(String.format("\t@## -> Skipping %s test: %s", reason, description));
    }

}
