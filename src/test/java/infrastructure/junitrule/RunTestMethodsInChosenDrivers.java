package infrastructure.junitrule;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.junit.runners.model.Statement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static io.github.seleniumquery.SeleniumQuery.$;

@SuppressWarnings("deprecation")
public class RunTestMethodsInChosenDrivers extends Statement {

	private final TestMethodsRunner testMethodsRunner;
	private final DriverToRunTestsIn driverToRunTestsIn;

	public RunTestMethodsInChosenDrivers(DriverToRunTestsIn driverToRunTestsIn, Statement base, String url) {
		super();
		this.driverToRunTestsIn = driverToRunTestsIn;
		this.testMethodsRunner = new TestMethodsRunner(base, url);
	}

	@Override
	public void evaluate() throws Throwable {
		executeTestOnHtmlUnits();
		executeTestOnChrome();
		executeTestOnIE();
		executeTestOnFirefox();
		executeTestOnPhantomJS();
		testMethodsRunner.reportFailures();
	}

	private void executeTestOnHtmlUnits() {
		if (!driverToRunTestsIn.canRunHtmlUnit()) {
			return;
		}
		if (driverToRunTestsIn != DriverToRunTestsIn.HTMLUNIT_CHROME &&
			driverToRunTestsIn != DriverToRunTestsIn.HTMLUNIT_CHROME_WITH_JS_OFF_AS_WELL) {
			executeOnHtmlUnitEmulatingFirefox17();
			executeOnHtmlUnitEmulatingFirefox24();
			executeOnHtmlUnitEmulatingIE8();
			executeOnHtmlUnitEmulatingIE9();
			executeOnHtmlUnitEmulatingIE11();
		}
		executeOnHtmlUnitEmulatingChrome();
	}

	private void executeTestOnChrome() {
		if (!driverToRunTestsIn.canRunChrome()) {
			return;
		}
		System.out.println("@## > Instantiating Chrome Driver");
		$.driver().useChrome();
		testMethodsRunner.executeMethodForDriver("Chrome");
		$.quit();
	}

	private void executeTestOnIE() {
		if (!driverToRunTestsIn.canRunIE()) {
			return;
		}
		System.out.println("@## > Instantiating on IE");
		$.driver().useInternetExplorer();
		testMethodsRunner.executeMethodForDriver("IE");
		$.quit();
	}

	private void executeTestOnFirefox() {
		if (!driverToRunTestsIn.canRunFirefox()) {
			return;
		}
		System.out.println("@## > Instantiating Firefox Driver - JavaScript ON");
		$.driver().useFirefox();
		testMethodsRunner.executeMethodForDriver("Firefox");
		$.quit();

		if (!driverToRunTestsIn.shouldTestDisabledJavaScript()) {
			return;
		}
		System.out.println("@## > Instantiating Firefox Driver - JavaScript OFF");
		$.driver().useFirefox().withoutJavaScript();
		testMethodsRunner.executeMethodForDriver("Firefox");
		$.quit();
	}

	private void executeTestOnPhantomJS() {
		if (!driverToRunTestsIn.canRunPhantomJS()) {
			return;
		}
		System.out.println("@## > Instantiating PhantomJS Driver");
		$.driver().usePhantomJS();
		testMethodsRunner.executeMethodForDriver("PhantomJS");
		$.quit();
	}

	private void executeOnHtmlUnitEmulatingChrome() {
		executeOnHtmlUnit(BrowserVersion.CHROME);
	}

	private void executeOnHtmlUnitEmulatingIE11() {
		executeOnHtmlUnit(BrowserVersion.INTERNET_EXPLORER_11);
	}

	private void executeOnHtmlUnitEmulatingIE9() {
		executeOnHtmlUnit(BrowserVersion.INTERNET_EXPLORER_9);
	}

	private void executeOnHtmlUnitEmulatingIE8() {
		executeOnHtmlUnit(BrowserVersion.INTERNET_EXPLORER_8);
	}

	private void executeOnHtmlUnitEmulatingFirefox24() {
		executeOnHtmlUnit(BrowserVersion.FIREFOX_24);
	}

	private void executeOnHtmlUnitEmulatingFirefox17() {
		executeOnHtmlUnit(BrowserVersion.FIREFOX_17);
	}

	private void executeOnHtmlUnit(BrowserVersion browserVersion) {
		System.out.println("@## > Instantiating HtmlUnit ("+browserVersion+") Driver - JavaScript ON");
		$.driver().use(createHtmlUnitDriverWithJavasCript(browserVersion, true));
		testMethodsRunner.executeMethodForDriver("HtmlUnit(" + browserVersion + ")");
		$.quit();

		if (!driverToRunTestsIn.shouldTestDisabledJavaScript()) {
			return;
		}
		System.out.println("@## > Instantiating HtmlUnit ("+browserVersion+") Driver - JavaScript OFF");
		$.driver().use(createHtmlUnitDriverWithJavasCript(browserVersion, false));
		testMethodsRunner.executeMethodForDriver("HtmlUnit(" + browserVersion + ")");
		$.quit();
	}

	private HtmlUnitDriver createHtmlUnitDriverWithJavasCript(BrowserVersion browserVersion, boolean enableJavascript) {
		HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(browserVersion);
		htmlUnitDriver.setJavascriptEnabled(enableJavascript);
		return htmlUnitDriver;
	}

}

class TestMethodsRunner {

	private String failed = "";
	private Throwable firstFailure;
	private Statement base;
	private String url;

	public TestMethodsRunner(Statement base, String url) {
		this.base = base;
		this.url = url;
	}

	public void executeMethodForDriver(String driver) {
		System.out.println("   @## >>> Running on "+driver);
		$.url(url); // this wont be needed when everyone use this both as @Rule and @ClassRule
		try {
			base.evaluate();
		} catch (Throwable t) {
			if (this.firstFailure == null) {
				this.firstFailure = t;
			}
			failed += driver + " ";
			System.out.println("   @## %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% FAILED on "+driver+"! -> "+t.getMessage());
		}
		System.out.println("   @## <<< Done on "+driver);
	}

	public void reportFailures() throws Throwable {
		if (this.firstFailure != null) {
			throw new AssertionError("There are test failures in some drivers: "+failed, this.firstFailure);
		}
	}

}