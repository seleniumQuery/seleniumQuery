package testinfrastructure.junitrule.statement;

import io.github.seleniumquery.browser.BrowserFunctions;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import testinfrastructure.junitrule.TestClassSession;
import testinfrastructure.junitrule.annotation.ChromeOnly;
import testinfrastructure.junitrule.annotation.FirefoxOnly;
import testinfrastructure.junitrule.annotation.JavaScriptOnly;
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
        if (description.getAnnotation(JavaScriptOnly.class) != null && testClassSession.driverHasJavaScriptDisabled()) {
            printSkipReason("JavaScript-only", this.description);
            return true;
        }
        if (description.getAnnotation(FirefoxOnly.class) != null && !DriverInTest.isFirefoxDriver(browser.driver().get())) {
            printSkipReason("Firefox-only", description);
            return true;
        }
        if (description.getAnnotation(ChromeOnly.class) != null && !DriverInTest.isChromeDriver(browser.driver().get())) {
            printSkipReason("Chrome-only", description);
            return true;
        }
        return false;
    }

    private void printSkipReason(String reason, Description description) {
        testClassSession.log(String.format("\t@## -> Skipping %s test: %s", reason, description));
    }

}
