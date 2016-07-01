/*
 * Copyright (c) 2016 seleniumQuery authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package testinfrastructure.junitrule.statement;

import io.github.seleniumquery.SeleniumQuery;
import io.github.seleniumquery.browser.BrowserFunctions;
import org.junit.runners.model.Statement;
import testinfrastructure.EndToEndTestUtils;
import testinfrastructure.junitrule.TestClassSession;
import testinfrastructure.junitrule.config.DriverInstantiator;
import testinfrastructure.junitrule.config.DriverToRunTestsIn;
import testinfrastructure.junitrule.config.RemoteInstantiator;

import java.util.List;

import static java.util.Arrays.asList;
import static testinfrastructure.junitrule.config.DriverInstantiator.FIREFOX_JS_OFF;
import static testinfrastructure.junitrule.config.DriverInstantiator.FIREFOX_JS_ON;
import static testinfrastructure.junitrule.config.DriverInstantiator.PHANTOMJS;
import static testinfrastructure.junitrule.config.RemoteInstantiator.*;
import static testinfrastructure.junitrule.statement.TestClassInConfiguredDriversStatement.DriverHasJavaScriptEnabled.NO;
import static testinfrastructure.junitrule.statement.TestClassInConfiguredDriversStatement.DriverHasJavaScriptEnabled.YES;

@SuppressWarnings("deprecation")
public class TestClassInConfiguredDriversStatement extends Statement {

	public enum DriverHasJavaScriptEnabled { YES, NO }

    private final TestClassSession testClassSession;
    private final DriverToRunTestsIn driverToRunTestsIn;
    private final Statement base;
    private final String url;

	public TestClassInConfiguredDriversStatement(TestClassSession testClassSession,
                                                 Statement base,
                                                 String url) {
        this.testClassSession = testClassSession;
        this.driverToRunTestsIn = testClassSession.getDriverToRunTestsIn();
        this.base = base;
        this.url = url;
	}

	@Override
	public void evaluate() throws Throwable {
		executeTestOnHtmlUnits();
		executeTestOnChrome();
		executeTestOnIE();
		executeTestOnFirefoxWithJS();
		executeTestOnFirefoxWithoutJS();
		executeTestOnPhantomJS();
		executeTestOnRemote();
		testClassSession.reportSummaryOfAllFailures();
	}

	private void executeTestOnHtmlUnits() {
		executeTestOnHtmlUnitEmulatingChromeJavaScriptOn();
		executeTestOnHtmlUnitEmulatingChromeJavaScriptOff();
		executeTestOnHtmlUnitEmulatingFirefoxJavaScriptOn();
		executeTestOnHtmlUnitEmulatingFirefoxJavaScriptOff();
		executeTestOnHtmlUnitEmulatingIE11JavaScriptOn();
		executeTestOnHtmlUnitEmulatingIE11JavaScriptOff();
	}

	private void executeTestOnHtmlUnitEmulatingChromeJavaScriptOn() {
		boolean shouldExecute = driverToRunTestsIn.canRunHtmlUnitWithJavaScriptOn()  || driverToRunTestsIn == DriverToRunTestsIn.HTMLUNIT_CHROME_JS_ON_ONLY
																					 || driverToRunTestsIn == DriverToRunTestsIn.HTMLUNIT_CHROME_JS_ON_AND_OFF;
		executeTestOn(shouldExecute, DriverInstantiator.HTMLUNIT_CHROME_JS_ON, YES);
	}
	private void executeTestOnHtmlUnitEmulatingChromeJavaScriptOff() {
		boolean shouldExecute = driverToRunTestsIn.canRunHtmlUnitWithJavaScriptOff() || driverToRunTestsIn == DriverToRunTestsIn.HTMLUNIT_CHROME_JS_OFF_ONLY
																					 || driverToRunTestsIn == DriverToRunTestsIn.HTMLUNIT_CHROME_JS_ON_AND_OFF;
		executeTestOn(shouldExecute, DriverInstantiator.HTMLUNIT_CHROME_JS_OFF, NO);
	}
	private void executeTestOnHtmlUnitEmulatingFirefoxJavaScriptOn()  {
		executeTestOn(driverToRunTestsIn.canRunHtmlUnitWithJavaScriptOn(), DriverInstantiator.HTMLUNIT_FIREFOX_JS_ON, YES);
	}
	private void executeTestOnHtmlUnitEmulatingFirefoxJavaScriptOff() {
		executeTestOn(driverToRunTestsIn.canRunHtmlUnitWithJavaScriptOff(), DriverInstantiator.HTMLUNIT_FIREFOX_JS_OFF, NO);
	}
	private void executeTestOnHtmlUnitEmulatingIE11JavaScriptOn()     {
		executeTestOn(driverToRunTestsIn.canRunHtmlUnitWithJavaScriptOn(), DriverInstantiator.HTMLUNIT_IE11_JS_ON, YES);
	}
	private void executeTestOnHtmlUnitEmulatingIE11JavaScriptOff()    {
		executeTestOn(driverToRunTestsIn.canRunHtmlUnitWithJavaScriptOff(), DriverInstantiator.HTMLUNIT_IE11_JS_OFF, NO);
	}

    private void executeTestOnChrome() {
        executeTestOn(driverToRunTestsIn.canRunChrome(), DriverInstantiator.CHROME, YES);
    }

    private void executeTestOnIE() {
        executeTestOn(driverToRunTestsIn.canRunIE(), DriverInstantiator.IE, YES);
    }

    private void executeTestOnFirefoxWithJS() {
        executeTestOn(driverToRunTestsIn.canRunFirefox() && driverToRunTestsIn.shouldRunWithJavaScriptOn(), FIREFOX_JS_ON, YES);
    }

    private void executeTestOnFirefoxWithoutJS() {
        executeTestOn(driverToRunTestsIn.canRunFirefox() && driverToRunTestsIn.shouldRunWithJavaScriptOff(), FIREFOX_JS_OFF, NO);
    }

    private void executeTestOnPhantomJS() {
        executeTestOn(driverToRunTestsIn.canRunPhantomJS(), PHANTOMJS, YES);
    }

	private void executeTestOnRemote() {
        boolean shouldRunRemoteTests = driverToRunTestsIn.canRunRemote() || driverToRunTestsIn == DriverToRunTestsIn.REMOTE;

        List<RemoteInstantiator> remoteInstantiators = asList(REMOTE_CHROME, REMOTE_FIREFOX, REMOTE_IE_10, REMOTE_IE_11, REMOTE_SAFARI, REMOTE_EDGE);
        for (RemoteInstantiator remote : remoteInstantiators) {
            executeTestOn(shouldRunRemoteTests, remote, YES);
        }
    }

	private void executeTestOn(boolean shouldExecute, DriverInstantiator driverInstantiator, DriverHasJavaScriptEnabled driverHasJavaScriptEnabled) {
		if (shouldExecute) {
            String driverDescription = driverInstantiator.getDriverDescription();
            if (driverInstantiator.shouldSkipTestClass(testClassSession.getTestClass())) {
                testClassSession.log("\t@## > Skipping test class for " + driverDescription + " due to annotation.");
                return;
            }

            testClassSession.log("\t@## > Instantiating " + driverDescription);
            BrowserFunctions browser = SeleniumQuery.$;
            testClassSession.reportCurrentBrowser(driverDescription, driverHasJavaScriptEnabled, browser);
            driverInstantiator.instantiateDriver(browser);
            testClassSession.log("\t@## > Opening URL " + url);
            EndToEndTestUtils.openUrl(url);
            testClassSession.log("\t@## > Executing test class for driver " + driverDescription);
            try {
                base.evaluate();
            } catch (Throwable t) {
                throw new RuntimeException("Unexpected exception.", t);
            }
            testClassSession.log("\t@## > Quitting driver " + driverDescription);
			browser.quit();
            testClassSession.eraseCurrentDriver();
            testClassSession.log("\t@## > Quit driver " + driverDescription);
		}
	}

}
