package infrastructure.junitrule;

public enum DriverToRunTestsIn {

	ALL_DRIVERS, GIVEN_DRIVER, HEADLESS_DRIVERS(true, false, false, false, true);

	DriverToRunTestsIn() { }

	DriverToRunTestsIn(boolean htmlUnit, boolean firefox, boolean chrome, boolean ie, boolean phantomJS) {
		this.htmlUnit = htmlUnit;
		this.firefox = firefox;
		this.chrome = chrome;
		this.ie = ie;
		this.phantomJS = phantomJS;
	}

	private boolean htmlUnit = true;
	private boolean firefox = true;
	private boolean chrome = true;
	private boolean ie = true;
	private boolean phantomJS = true;

	public boolean canRunHtmlUnit() { return htmlUnit; }
	public boolean canRunFirefox() { return firefox; }
	public boolean canRunChrome() { return chrome; }
	public boolean canRunIE() { return ie; }
	public boolean canRunPhantomJS() { return phantomJS; }

}