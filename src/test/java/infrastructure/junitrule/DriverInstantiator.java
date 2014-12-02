package infrastructure.junitrule;

import io.github.seleniumquery.browser.BrowserFunctions;

@SuppressWarnings("deprecation")
abstract class DriverInstantiator {

    private String driverDescription;
    public DriverInstantiator(String driverDescription) { this.driverDescription = driverDescription; }
    public String getDriverDescription() { return driverDescription; }

    abstract void instantiateDriver(BrowserFunctions browser);

    static DriverInstantiator PHANTOMJS = new DriverInstantiator("PhantomJS") {
        @Override public void instantiateDriver(BrowserFunctions $) {
            $.driver().usePhantomJS();
        }
    };
    static DriverInstantiator FIREFOX_JS_ON = new DriverInstantiator("Firefox - JS ON") {
        @Override public void instantiateDriver(BrowserFunctions $) {
            $.driver().useFirefox();
        }
    };
    static DriverInstantiator FIREFOX_JS_OFF = new DriverInstantiator("Firefox - JS OFF") {
        @Override public void instantiateDriver(BrowserFunctions $) {
            $.driver().useFirefox().withoutJavaScript();
        }
    };
    static DriverInstantiator IE = new DriverInstantiator("IE") {
        @Override public void instantiateDriver(BrowserFunctions $) {
            $.driver().useInternetExplorer();
        }
    };
    static DriverInstantiator CHROME = new DriverInstantiator("Chrome") {
        @Override public void instantiateDriver(BrowserFunctions $) {
            $.driver().useChrome();
        }
    };
    static DriverInstantiator HTMLUNIT_CHROME_JS_ON = new DriverInstantiator("HtmlUnit (Chrome) - JS ON") {
        @Override public void instantiateDriver(BrowserFunctions $) {
            $.driver().useHtmlUnit().emulatingChrome();
        }
    };
    static DriverInstantiator HTMLUNIT_CHROME_JS_OFF = new DriverInstantiator("HtmlUnit (Chrome) - JS OFF") {
        @Override public void instantiateDriver(BrowserFunctions $) {
            $.driver().useHtmlUnit().emulatingChrome().withoutJavaScript();
        }
    };
    static DriverInstantiator HTMLUNIT_FIREFOX_JS_ON = new DriverInstantiator("HtmlUnit (Firefox) - JS ON") {
        @Override public void instantiateDriver(BrowserFunctions $) {
            $.driver().useHtmlUnit().emulatingFirefox();
        }
    };
    static DriverInstantiator HTMLUNIT_FIREFOX_JS_OFF = new DriverInstantiator("HtmlUnit (Firefox) - JS OFF") {
        @Override public void instantiateDriver(BrowserFunctions $) {
            $.driver().useHtmlUnit().emulatingFirefox().withoutJavaScript();
        }
    };
    static DriverInstantiator HTMLUNIT_IE8_JS_ON   = new DriverInstantiator("HtmlUnit (IE8) - JS ON")   { @Override public void instantiateDriver(BrowserFunctions $) { $.driver().useHtmlUnit().emulatingInternetExplorer8(); } };
    static DriverInstantiator HTMLUNIT_IE8_JS_OFF  = new DriverInstantiator("HtmlUnit (IE8) - JS OFF")  { @Override public void instantiateDriver(BrowserFunctions $) { $.driver().useHtmlUnit().emulatingInternetExplorer8().withoutJavaScript(); } };
    static DriverInstantiator HTMLUNIT_IE9_JS_ON   = new DriverInstantiator("HtmlUnit (IE9) - JS ON")   { @Override public void instantiateDriver(BrowserFunctions $) { $.driver().useHtmlUnit().emulatingInternetExplorer9(); } };
    static DriverInstantiator HTMLUNIT_IE9_JS_OFF  = new DriverInstantiator("HtmlUnit (IE9) - JS OFF")  { @Override public void instantiateDriver(BrowserFunctions $) { $.driver().useHtmlUnit().emulatingInternetExplorer9().withoutJavaScript(); } };
    static DriverInstantiator HTMLUNIT_IE11_JS_ON  = new DriverInstantiator("HtmlUnit (IE11) - JS ON")  { @Override public void instantiateDriver(BrowserFunctions $) { $.driver().useHtmlUnit().emulatingInternetExplorer11(); } };
    static DriverInstantiator HTMLUNIT_IE11_JS_OFF = new DriverInstantiator("HtmlUnit (IE11) - JS OFF") { @Override public void instantiateDriver(BrowserFunctions $) { $.driver().useHtmlUnit().emulatingInternetExplorer11().withoutJavaScript(); } };

}