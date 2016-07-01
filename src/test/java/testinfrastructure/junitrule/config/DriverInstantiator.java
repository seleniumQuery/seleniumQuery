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

package testinfrastructure.junitrule.config;

import io.github.seleniumquery.browser.BrowserFunctions;

@SuppressWarnings("deprecation")
public abstract class DriverInstantiator {

    private String driverDescription;
    DriverInstantiator(String driverDescription) { this.driverDescription = driverDescription; }
    public String getDriverDescription() { return driverDescription; }

    public abstract void instantiateDriver(BrowserFunctions browser);

    public static DriverInstantiator PHANTOMJS = new DriverInstantiator("PhantomJS") {
        @Override public void instantiateDriver(BrowserFunctions $) {
            $.driver().usePhantomJS();
        }
    };
    public static DriverInstantiator FIREFOX_JS_ON = new DriverInstantiator("Firefox - JS ON") {
        @Override public void instantiateDriver(BrowserFunctions $) {
            $.driver().useFirefox();
        }
    };
    public static DriverInstantiator FIREFOX_JS_OFF = new DriverInstantiator("Firefox - JS OFF") {
        @Override public void instantiateDriver(BrowserFunctions $) {
            $.driver().useFirefox().withoutJavaScript();
        }
    };
    public static DriverInstantiator IE = new DriverInstantiator("IE") {
        @Override public void instantiateDriver(BrowserFunctions $) {
            $.driver().useInternetExplorer();
        }
    };
    public static DriverInstantiator CHROME = new DriverInstantiator("Chrome") {
        @Override public void instantiateDriver(BrowserFunctions $) {
            $.driver().useChrome();
        }
    };
    public static DriverInstantiator HTMLUNIT_CHROME_JS_ON = new DriverInstantiator("HtmlUnit (Chrome) - JS ON") {
        @Override public void instantiateDriver(BrowserFunctions $) {
            $.driver().useHtmlUnit().emulatingChrome();
        }
    };
    public static DriverInstantiator HTMLUNIT_CHROME_JS_OFF = new DriverInstantiator("HtmlUnit (Chrome) - JS OFF") {
        @Override public void instantiateDriver(BrowserFunctions $) {
            $.driver().useHtmlUnit().emulatingChrome().withoutJavaScript();
        }
    };
    public static DriverInstantiator HTMLUNIT_FIREFOX_JS_ON = new DriverInstantiator("HtmlUnit (Firefox) - JS ON") {
        @Override public void instantiateDriver(BrowserFunctions $) {
            $.driver().useHtmlUnit().emulatingFirefox();
        }
    };
    public static DriverInstantiator HTMLUNIT_FIREFOX_JS_OFF = new DriverInstantiator("HtmlUnit (Firefox) - JS OFF") {
        @Override public void instantiateDriver(BrowserFunctions $) {
            $.driver().useHtmlUnit().emulatingFirefox().withoutJavaScript();
        }
    };
    public static DriverInstantiator HTMLUNIT_IE11_JS_ON  = new DriverInstantiator("HtmlUnit (IE11) - JS ON")  { @Override public void instantiateDriver(BrowserFunctions $) { $.driver().useHtmlUnit().emulatingInternetExplorer11(); } };
    public static DriverInstantiator HTMLUNIT_IE11_JS_OFF = new DriverInstantiator("HtmlUnit (IE11) - JS OFF") { @Override public void instantiateDriver(BrowserFunctions $) { $.driver().useHtmlUnit().emulatingInternetExplorer11().withoutJavaScript(); } };

}
