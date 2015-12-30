/*
 * Copyright (c) 2015 seleniumQuery authors
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

import java.util.List;

import static java.util.Arrays.asList;
import static testinfrastructure.junitrule.DriverToRunTestsIn.ShouldRun.*;

@SuppressWarnings("unused")
public enum DriverToRunTestsIn {

	ALL_DRIVERS_JS_ON_AND_OFF			(_FIREFOX, _CHROME, _IE, _PHANTOMJS, _HTMLUNIT, _JAVASCRIPT_ON, _JAVASCRIPT_OFF),
	ALL_DRIVERS_JS_ON_ONLY				(_FIREFOX, _CHROME, _IE, _PHANTOMJS, _HTMLUNIT, _JAVASCRIPT_ON),

	HEADLESS_DRIVERS_JS_ON_AND_OFF      (_PHANTOMJS, _HTMLUNIT, _JAVASCRIPT_ON, _JAVASCRIPT_OFF),
	HEADLESS_DRIVERS_JS_ON_ONLY         (_PHANTOMJS, _HTMLUNIT, _JAVASCRIPT_ON),
	NON_HEADLESS_DRIVERS_JS_ON_ONLY     (_FIREFOX, _CHROME, _IE, _JAVASCRIPT_ON),

	FIREFOX_JS_ON_ONLY                  (_FIREFOX, _JAVASCRIPT_ON),
	FIREFOX_JS_ON_AND_OFF               (_FIREFOX, _JAVASCRIPT_ON, _JAVASCRIPT_OFF),
	FIREFOX_JS_OFF_ONLY                 (_FIREFOX, _JAVASCRIPT_OFF),

	CHROME                              (_CHROME, _JAVASCRIPT_ON),
	IE                                  (_IE, _JAVASCRIPT_ON),
	PHANTOMJS                           (_PHANTOMJS, _JAVASCRIPT_ON),

	HTMLUNIT_ALL_JS_ON_AND_OFF          (_HTMLUNIT, _JAVASCRIPT_ON, _JAVASCRIPT_OFF),
	HTMLUNIT_ALL_JS_ON_ONLY             (_HTMLUNIT, _JAVASCRIPT_ON),
	HTMLUNIT_ALL_JS_OFF_ONLY            (_HTMLUNIT, _JAVASCRIPT_OFF),

	HTMLUNIT_CHROME_JS_ON_AND_OFF       (),
	HTMLUNIT_CHROME_JS_ON_ONLY          (),
	HTMLUNIT_CHROME_JS_OFF_ONLY         (),
	HTMLUNIT_IE8_JS_ON_ONLY             ();

	enum ShouldRun {_FIREFOX, _CHROME, _IE, _PHANTOMJS, _HTMLUNIT, _JAVASCRIPT_ON, _JAVASCRIPT_OFF}

	DriverToRunTestsIn(ShouldRun... shouldRuns) {
        List<ShouldRun> shouldRunList = asList(shouldRuns);
        this.firefox = shouldRunList.contains(_FIREFOX);
		this.chrome = shouldRunList.contains(_CHROME);
		this.ie = shouldRunList.contains(_IE);
		this.phantomJS = shouldRunList.contains(_PHANTOMJS);
		this.htmlUnit = shouldRunList.contains(_HTMLUNIT);
		this.javaScriptOn = shouldRunList.contains(_JAVASCRIPT_ON);
		this.javaScriptOff = shouldRunList.contains(_JAVASCRIPT_OFF);
	}

	private final boolean htmlUnit;
	private final boolean firefox;
	private final boolean chrome;
	private final boolean ie;
	private final boolean phantomJS;
	private final boolean javaScriptOn;
	private final boolean javaScriptOff;

	public boolean canRunHtmlUnit() { return htmlUnit; }
	public boolean canRunFirefox() { return firefox; }
	public boolean canRunChrome() { return chrome; }
	public boolean canRunIE() { return ie; }
	public boolean canRunPhantomJS() { return phantomJS; }
	public boolean shouldRunWithJavaScriptOn() { return javaScriptOn; }
	public boolean shouldRunWithJavaScriptOff() { return javaScriptOff; }
	public boolean canRunHtmlUnitWithJavaScriptOn() { return canRunHtmlUnit() && shouldRunWithJavaScriptOn(); }
	public boolean canRunHtmlUnitWithJavaScriptOff() { return canRunHtmlUnit() && shouldRunWithJavaScriptOff(); }

}