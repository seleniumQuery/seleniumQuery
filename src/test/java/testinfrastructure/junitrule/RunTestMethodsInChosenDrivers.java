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

package testinfrastructure.junitrule;

import org.junit.runners.model.Statement;

import static io.github.seleniumquery.SeleniumQuery.$;
import static testinfrastructure.junitrule.DriverInstantiator.*;
import static testinfrastructure.junitrule.RunTestMethodsInChosenDrivers.DriverHasJavaScriptEnabled.NO;
import static testinfrastructure.junitrule.RunTestMethodsInChosenDrivers.DriverHasJavaScriptEnabled.YES;

@SuppressWarnings("deprecation")
class RunTestMethodsInChosenDrivers extends Statement {

	enum DriverHasJavaScriptEnabled { YES, NO }

	private final TestMethodsRunner testMethodsRunner;
	private final DriverToRunTestsIn driverToRunTestsIn;
    private final SetUpAndTearDownDriver setUpAndTearDownDriver;

	RunTestMethodsInChosenDrivers(DriverToRunTestsIn driverToRunTestsIn, Statement base, String url, SetUpAndTearDownDriver setUpAndTearDownDriver) {
		super();
		this.driverToRunTestsIn = driverToRunTestsIn;
		this.testMethodsRunner = new TestMethodsRunner(base, url);
        this.setUpAndTearDownDriver = setUpAndTearDownDriver;
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
		testMethodsRunner.reportFailures();
	}

	private void executeTestOnHtmlUnits() {
		executeTestOnHtmlUnitEmulatingChromeJavaScriptOn();
		executeTestOnHtmlUnitEmulatingChromeJavaScriptOff();
		executeTestOnHtmlUnitEmulatingFirefoxJavaScriptOn();
		executeTestOnHtmlUnitEmulatingFirefoxJavaScriptOff();
		executeTestOnHtmlUnitEmulatingIE11JavaScriptOn();
		executeTestOnHtmlUnitEmulatingIE11JavaScriptOff();
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
	private void executeTestOnRemote() {
		executeTestOn(driverToRunTestsIn.canRunRemote(), DriverInstantiator.REMOTE, YES);
	}

	private void executeTestOn(boolean shouldExecute, DriverInstantiator driverInstantiator, DriverHasJavaScriptEnabled driverHasJavaScriptEnabled) {
		if (shouldExecute) {
            this.setUpAndTearDownDriver.driverHasJavaScriptEnabled = driverHasJavaScriptEnabled == YES;
			System.out.println("@## > Instantiating " + driverInstantiator.getDriverDescription());
			driverInstantiator.instantiateDriver($);
			testMethodsRunner.executeMethodForDriver(driverInstantiator.getDriverDescription());
			$.quit();
		}
	}

}