package testinfrastructure.junitrule;

import io.github.seleniumquery.browser.BrowserFunctions;
import testinfrastructure.junitrule.config.DriverToRunTestsIn;
import testinfrastructure.junitrule.statement.TestClassInConfiguredDriversStatement;

import java.util.ArrayList;
import java.util.List;

public class TestClassSession {

    private final DriverToRunTestsIn driverToRunTestsIn;

    private List<String> failedDrivers = new ArrayList<>();
    private Throwable firstFailure;
    private String currentDriverDescription;
    private TestClassInConfiguredDriversStatement.DriverHasJavaScriptEnabled currentDriverHasJavaScriptEnabled;
    private BrowserFunctions browser;
    private Class<?> testClassReportedByClassRule;
    private String url;

    TestClassSession(DriverToRunTestsIn driverToRunTestsIn) {
        this.driverToRunTestsIn = driverToRunTestsIn;
    }

    void initiateClassRule(Class<?> testClass, String url) {
        this.testClassReportedByClassRule = testClass;
        this.url = url;
    }

    boolean classIsNotAnnotatedWithClassRule() {
        return testClassReportedByClassRule == null;
    }

    public void log(String msg) {
        synchronized (TestClassSession.class) {
            System.out.println(msg);
        }
    }

    public void recordFailure(Throwable t) {
        recordFailureReason(t);
        failedDrivers.add(this.currentDriverDescription);
        log("@######### %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% FAILED on "+this.currentDriverDescription +"! -> "+t.getMessage());
    }

    private void recordFailureReason(Throwable t) {
        // we only record the first failure (we can only report one failure...)
        if (this.firstFailure == null) {
            this.firstFailure = t;
        }
    }

    public void reportSummaryOfAllFailures() throws Throwable {
        // at this point the driver already quit
        if (!this.failedDrivers.isEmpty()) {
            throw new AssertionError("There are test failures in some drivers: " + this.failedDrivers, this.firstFailure);
        }
    }

    public void eraseCurrentDriver() {
        this.currentDriverDescription = null;
        this.currentDriverHasJavaScriptEnabled = null;
    }

    public void reportCurrentBrowser(String driverDescription,
                                     TestClassInConfiguredDriversStatement.DriverHasJavaScriptEnabled driverHasJavaScriptEnabled,
                                     BrowserFunctions browser) {
        this.currentDriverDescription = driverDescription;
        this.currentDriverHasJavaScriptEnabled = driverHasJavaScriptEnabled;
        this.browser = browser;
    }

    public boolean driverHasJavaScriptDisabled() {
        return currentDriverHasJavaScriptEnabled.equals(TestClassInConfiguredDriversStatement.DriverHasJavaScriptEnabled.NO);
    }

    public DriverToRunTestsIn getDriverToRunTestsIn() {
        return driverToRunTestsIn;
    }

    public BrowserFunctions getBrowser() {
        return browser;
    }

    public Class<?> getTestClass() {
        return testClassReportedByClassRule;
    }

    public String getUrl() {
        return url;
    }

}
