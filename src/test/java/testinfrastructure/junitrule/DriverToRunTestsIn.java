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

@SuppressWarnings("unused")
public enum DriverToRunTestsIn {

	ALL_DRIVERS_JS_ON_AND_OFF			(true,  true,  true,  true,  true,    true,  true),
	ALL_DRIVERS_JS_ON_ONLY				(true,  true,  true,  true,  true,    true,  false),
	HEADLESS_DRIVERS_JS_ON_AND_OFF      (false, false, false, true,  true,    true,  true),
	HEADLESS_DRIVERS_JS_ON_ONLY         (false, false, false, true,  true,    true,  false),
	NON_HEADLESS_DRIVERS_JS_ON_ONLY     (true,  true,  true,  false, false,   true,  false),

	FIREFOX_JS_ON_ONLY                  (true,  false, false, false, false,   true,  false),
	FIREFOX_JS_ON_AND_OFF               (true,  false, false, false, false,   true,  true),
	FIREFOX_JS_OFF_ONLY                 (true,  false, false, false, false,   false, true),

	CHROME                              (false, true,  false, false, false,   true,  false),
	IE                                  (false, false, true,  false, false,   true,  false),
	PHANTOMJS                           (false, false, false, true,  false,   true,  false),

	HTMLUNIT_ALL_JS_ON_AND_OFF          (false, false, false, false, true,    true,  true),
	HTMLUNIT_ALL_JS_ON_ONLY             (false, false, false, false, true,    true,  false),
	HTMLUNIT_ALL_JS_OFF_ONLY            (false, false, false, false, true,    false, true),

	HTMLUNIT_CHROME_JS_ON_AND_OFF       (false, false, false, false, false,   false,  false),
	HTMLUNIT_CHROME_JS_ON_ONLY          (false, false, false, false, false,   false,  false),
	HTMLUNIT_CHROME_JS_OFF_ONLY         (false, false, false, false, false,   false,  false);


	DriverToRunTestsIn(boolean firefox, boolean chrome, boolean ie, boolean phantomJS, boolean htmlUnit, boolean javaScriptOn, boolean javaScriptOff) {
		this.firefox = firefox;
		this.chrome = chrome;
		this.ie = ie;
		this.phantomJS = phantomJS;
		this.htmlUnit = htmlUnit;
		this.javaScriptOn = javaScriptOn;
		this.javaScriptOff = javaScriptOff;
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