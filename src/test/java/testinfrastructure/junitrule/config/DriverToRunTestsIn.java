/*
 * Copyright (c) 2017 seleniumQuery authors
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

package testinfrastructure.junitrule.config;

import static java.util.Arrays.asList;
import static testinfrastructure.junitrule.config.DriverToRunTestsIn.ShouldRun._CHROME;
import static testinfrastructure.junitrule.config.DriverToRunTestsIn.ShouldRun._CHROME_HEADLESS;
import static testinfrastructure.junitrule.config.DriverToRunTestsIn.ShouldRun._EDGE;
import static testinfrastructure.junitrule.config.DriverToRunTestsIn.ShouldRun._FIREFOX;
import static testinfrastructure.junitrule.config.DriverToRunTestsIn.ShouldRun._FIREFOX_HEADLESS;
import static testinfrastructure.junitrule.config.DriverToRunTestsIn.ShouldRun._HTMLUNIT;
import static testinfrastructure.junitrule.config.DriverToRunTestsIn.ShouldRun._IE;
import static testinfrastructure.junitrule.config.DriverToRunTestsIn.ShouldRun._JAVASCRIPT_OFF;
import static testinfrastructure.junitrule.config.DriverToRunTestsIn.ShouldRun._JAVASCRIPT_ON;
import static testinfrastructure.junitrule.config.DriverToRunTestsIn.ShouldRun._OPERA;
import static testinfrastructure.junitrule.config.DriverToRunTestsIn.ShouldRun._PHANTOMJS;
import static testinfrastructure.testutils.EnvironmentTestUtils.gitLastCommitMessageContains;

import java.util.List;

@SuppressWarnings("unused")
public enum DriverToRunTestsIn {

	ALL_DRIVERS_JS_ON_AND_OFF			(_FIREFOX, _CHROME, _IE, _PHANTOMJS, _HTMLUNIT, _JAVASCRIPT_ON, _JAVASCRIPT_OFF),
	ALL_DRIVERS_JS_ON_ONLY				(_FIREFOX, _CHROME, _IE, _PHANTOMJS, _HTMLUNIT, _JAVASCRIPT_ON),

	HEADLESS_DRIVERS_JS_ON_AND_OFF      (_PHANTOMJS, _HTMLUNIT, _JAVASCRIPT_ON, _JAVASCRIPT_OFF),
	HEADLESS_DRIVERS_JS_ON_ONLY         (_PHANTOMJS, _HTMLUNIT, _FIREFOX_HEADLESS, _CHROME_HEADLESS, _JAVASCRIPT_ON),
	NON_HEADLESS_DRIVERS_JS_ON_ONLY     (_FIREFOX, _CHROME, _IE, _JAVASCRIPT_ON),

	FIREFOX                             (_FIREFOX, _JAVASCRIPT_ON),
	FIREFOX_HEADLESS                    (_FIREFOX_HEADLESS, _JAVASCRIPT_ON),

	CHROME                              (_CHROME, _JAVASCRIPT_ON),
	CHROME_NO_AUTODOWNLOAD              (),
	CHROME_HEADLESS                     (_CHROME_HEADLESS, _JAVASCRIPT_ON),

    FIREFOX_AND_CHROME_HEADLESS         (_FIREFOX_HEADLESS, _CHROME_HEADLESS, _JAVASCRIPT_ON),
    FIREFOX_AND_EDGE                    (_FIREFOX, _EDGE, _JAVASCRIPT_ON),

    IE                                  (_IE, _JAVASCRIPT_ON),
	EDGE                                (_EDGE, _JAVASCRIPT_ON),

	OPERA                                (_OPERA, _JAVASCRIPT_ON),

    PHANTOMJS                           (_PHANTOMJS, _JAVASCRIPT_ON),

	HTMLUNIT_ALL_JS_ON_AND_OFF          (_HTMLUNIT, _JAVASCRIPT_ON, _JAVASCRIPT_OFF),
	HTMLUNIT_ALL_JS_ON_ONLY             (_HTMLUNIT, _JAVASCRIPT_ON),
	HTMLUNIT_ALL_JS_OFF_ONLY            (_HTMLUNIT, _JAVASCRIPT_OFF),

	HTMLUNIT_CHROME_JS_ON_AND_OFF       (),
	HTMLUNIT_CHROME_JS_ON_ONLY          (),
	HTMLUNIT_CHROME_JS_OFF_ONLY         (),

	REMOTE                              ();

	private final boolean htmlUnit;
	private final boolean firefox;
    private final boolean firefoxHeadless;
	private final boolean chrome;
	private final boolean chromeHeadless;
	private final boolean ie;
	private final boolean edge;
	private final boolean opera;
	private final boolean phantomJS;
	private final boolean javaScriptOn;
	private final boolean javaScriptOff;

    public enum ShouldRun {_FIREFOX, _FIREFOX_HEADLESS, _CHROME, _CHROME_HEADLESS, _IE, _EDGE, _OPERA, _PHANTOMJS, _HTMLUNIT, _JAVASCRIPT_ON, _JAVASCRIPT_OFF}

	DriverToRunTestsIn(ShouldRun... shouldRuns) {
        List<ShouldRun> shouldRunList = asList(shouldRuns);
        this.firefox = shouldRunList.contains(_FIREFOX);
        this.firefoxHeadless = shouldRunList.contains(_FIREFOX_HEADLESS);
		this.chrome = shouldRunList.contains(_CHROME);
		this.chromeHeadless = shouldRunList.contains(_CHROME_HEADLESS);
		this.ie = shouldRunList.contains(_IE);
		this.edge = shouldRunList.contains(_EDGE);
		this.opera = shouldRunList.contains(_OPERA);
		this.phantomJS = shouldRunList.contains(_PHANTOMJS);
		this.htmlUnit = shouldRunList.contains(_HTMLUNIT);
		this.javaScriptOn = shouldRunList.contains(_JAVASCRIPT_ON);
		this.javaScriptOff = shouldRunList.contains(_JAVASCRIPT_OFF);
	}

	public boolean canRunHtmlUnit() { return htmlUnit; }
	public boolean canRunFirefox() { return firefox; }
	public boolean canRunFirefoxHeadless() { return firefoxHeadless; }
	public boolean canRunChrome() { return chrome; }
	public boolean canRunChromeHeadless() { return chromeHeadless; }
	public boolean canRunIE() { return ie; }
	public boolean canRunEdge() { return edge; }
	public boolean canRunOpera() { return opera; }
	public boolean canRunPhantomJS() { return phantomJS; }
	public boolean shouldRunWithJavaScriptOn() { return javaScriptOn; }
	public boolean shouldRunWithJavaScriptOff() { return javaScriptOff; }
	public boolean canRunHtmlUnitWithJavaScriptOn() { return canRunHtmlUnit() && shouldRunWithJavaScriptOn(); }
	public boolean canRunHtmlUnitWithJavaScriptOff() { return canRunHtmlUnit() && shouldRunWithJavaScriptOff(); }
	public boolean canRunRemote() { return gitLastCommitMessageContains("[run sauce]"); }

}
