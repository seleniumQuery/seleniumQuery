package infrastructure.junitrule;

@SuppressWarnings("unused")
public enum DriverToRunTestsIn {

	ALL_DRIVERS,
	ALL_DRIVERS_WITH_JS_OFF_AS_WELL(true, true, true, true, true, true),
	HEADLESS_DRIVERS(true, false, false, false, true, false),
	HEADLESS_DRIVERS_WITH_JS_OFF_AS_WELL(true, false, false, false, true, true),
	NON_HEADLESS_DRIVERS(false, true, true, true, false, false),
	HTMLUNIT(true, false, false, false, false, false),
	HTMLUNIT_ONLY_ONCE(true, false, false, false, false, false),
	HTMLUNIT_WITH_JS_OFF_AS_WELL(true, false, false, false, true, true),
	CHROME(false, false, true, false, false, false),
	FIREFOX(false, true, false, false, false, false),
	FIREFOX_WITH_JS_OFF_AS_WELL(false, true, false, false, false, true),
	IE(false, false, false, true, false, false),
	PHANTOMJS(false, false, false, false, true, false);

	DriverToRunTestsIn() { }

	DriverToRunTestsIn(boolean htmlUnit, boolean firefox, boolean chrome, boolean ie, boolean phantomJS, boolean shouldTestDisabledJavaScript) {
		this.htmlUnit = htmlUnit;
		this.firefox = firefox;
		this.chrome = chrome;
		this.ie = ie;
		this.phantomJS = phantomJS;
		this.shouldTestDisabledJavaScript = shouldTestDisabledJavaScript;
	}

	private boolean htmlUnit = true;
	private boolean firefox = true;
	private boolean chrome = true;
	private boolean ie = true;
	private boolean phantomJS = true;
	private boolean shouldTestDisabledJavaScript = false;

	public boolean canRunHtmlUnit() { return htmlUnit; }
	public boolean canRunFirefox() { return firefox; }
	public boolean canRunChrome() { return chrome; }
	public boolean canRunIE() { return ie; }
	public boolean canRunPhantomJS() { return phantomJS; }
	public boolean shouldTestDisabledJavaScript() { return shouldTestDisabledJavaScript; }

}