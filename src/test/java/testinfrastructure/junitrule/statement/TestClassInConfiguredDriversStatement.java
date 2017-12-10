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

import static java.util.Arrays.asList;
import static testinfrastructure.junitrule.config.DriverInstantiator.EDGE;
import static testinfrastructure.junitrule.config.DriverInstantiator.FIREFOX;
import static testinfrastructure.junitrule.config.DriverInstantiator.OPERA;
import static testinfrastructure.junitrule.config.DriverInstantiator.PHANTOMJS;
import static testinfrastructure.junitrule.config.RemoteInstantiator.REMOTE_CHROME;
import static testinfrastructure.junitrule.config.RemoteInstantiator.REMOTE_CHROME_OSX;
import static testinfrastructure.junitrule.config.RemoteInstantiator.REMOTE_CHROME_W7;
import static testinfrastructure.junitrule.config.RemoteInstantiator.REMOTE_FIREFOX;
import static testinfrastructure.junitrule.config.RemoteInstantiator.REMOTE_IE_11;
import static testinfrastructure.junitrule.config.RemoteInstantiator.REMOTE_SAFARI;
import static testinfrastructure.junitrule.statement.TestClassInConfiguredDriversStatement.DriverHasJavaScriptEnabled.NO;
import static testinfrastructure.junitrule.statement.TestClassInConfiguredDriversStatement.DriverHasJavaScriptEnabled.YES;

import java.util.List;

import org.junit.runners.model.Statement;

import io.github.seleniumquery.SeleniumQuery;
import io.github.seleniumquery.browser.BrowserFunctions;
import testinfrastructure.junitrule.TestClassSession;
import testinfrastructure.junitrule.config.DriverInstantiator;
import testinfrastructure.junitrule.config.DriverToRunTestsIn;
import testinfrastructure.junitrule.config.RemoteInstantiator;

@SuppressWarnings("deprecation")
public class TestClassInConfiguredDriversStatement extends Statement {

	public enum DriverHasJavaScriptEnabled { YES, NO }

    private final TestClassSession testClassSession;
    private final DriverToRunTestsIn driverToRunTestsIn;
    private final Statement base;

    public TestClassInConfiguredDriversStatement(TestClassSession testClassSession, Statement base) {
        this.testClassSession = testClassSession;
        this.driverToRunTestsIn = testClassSession.getDriverToRunTestsIn();
        this.base = base;
    }

	@Override
	public void evaluate() throws Throwable {
		executeTestOnHtmlUnits();
		executeTestOnChrome();
		executeTestOnFirefox();
		executeTestOnEdge();
		executeTestOnOpera();
		executeTestOnIE();
		executeTestOnPhantomJS();
		executeTestOnRemote();
		testClassSession.reportSummaryOfAllFailures();
	}

	private void executeTestOnHtmlUnits() {
		executeTestOnHtmlUnitEmulatingChromeJavaScriptOn();
		executeTestOnHtmlUnitEmulatingChromeJavaScriptOff();
		executeTestOnHtmlUnitEmulatingFirefoxJavaScriptOn();
		executeTestOnHtmlUnitEmulatingFirefoxJavaScriptOff();
		executeTestOnHtmlUnitEmulatingIEJavaScriptOn();
		executeTestOnHtmlUnitEmulatingIEJavaScriptOff();
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
	private void executeTestOnHtmlUnitEmulatingIEJavaScriptOn()     {
		executeTestOn(driverToRunTestsIn.canRunHtmlUnitWithJavaScriptOn(), DriverInstantiator.HTMLUNIT_IE_JS_ON, YES);
	}
	private void executeTestOnHtmlUnitEmulatingIEJavaScriptOff()    {
		executeTestOn(driverToRunTestsIn.canRunHtmlUnitWithJavaScriptOff(), DriverInstantiator.HTMLUNIT_IE_JS_OFF, NO);
	}

    private void executeTestOnChrome() {
        executeTestOn(driverToRunTestsIn.canRunChrome(), DriverInstantiator.CHROME, YES);
        executeTestOn(driverToRunTestsIn.canRunChromeHeadless(), DriverInstantiator.CHROME_HEADLESS, YES);
    }

    private void executeTestOnIE() {
        executeTestOn(driverToRunTestsIn.canRunIE(), DriverInstantiator.IE, YES);
    }

    private void executeTestOnFirefox() {
        executeTestOn(driverToRunTestsIn.canRunFirefox() && driverToRunTestsIn.shouldRunWithJavaScriptOn(), FIREFOX, YES);
    }

    private void executeTestOnEdge() {
        executeTestOn(driverToRunTestsIn.canRunEdge() && driverToRunTestsIn.shouldRunWithJavaScriptOn(), EDGE, YES);
    }

    private void executeTestOnOpera() {
        executeTestOn(driverToRunTestsIn.canRunOpera() && driverToRunTestsIn.shouldRunWithJavaScriptOn(), OPERA, YES);
    }

    private void executeTestOnPhantomJS() {
        executeTestOn(driverToRunTestsIn.canRunPhantomJS(), PHANTOMJS, YES);
    }

	private void executeTestOnRemote() {
        boolean shouldRunRemoteTests = driverToRunTestsIn.canRunRemote() || driverToRunTestsIn == DriverToRunTestsIn.REMOTE;

        List<RemoteInstantiator> remoteInstantiators = asList(
            REMOTE_CHROME
            ,REMOTE_CHROME_OSX
            ,REMOTE_CHROME_W7
            ,REMOTE_FIREFOX
//            ,REMOTE_FIREFOX_OSX
//            ,REMOTE_FIREFOX_W7
//            ,REMOTE_IE_10
            ,REMOTE_IE_11
            ,REMOTE_SAFARI
//            ,REMOTE_EDGE
//            ,REMOTE_OPERA
        );
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
