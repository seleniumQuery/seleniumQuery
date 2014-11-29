package infrastructure.junitrule;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.junit.runners.model.Statement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static io.github.seleniumquery.SeleniumQuery.$;

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

	@SuppressWarnings("deprecation")
	private void executeTestOnHtmlUnits() {
		if (!driverToRunTestsIn.canRunHtmlUnit()) {
			return;
		}
		if (driverToRunTestsIn != DriverToRunTestsIn.HTMLUNIT_ONLY_ONCE) {
			executeOnHtmlUnit(BrowserVersion.FIREFOX_17);
			executeOnHtmlUnit(BrowserVersion.FIREFOX_24);
			executeOnHtmlUnit(BrowserVersion.INTERNET_EXPLORER_8);
			executeOnHtmlUnit(BrowserVersion.INTERNET_EXPLORER_9);
			executeOnHtmlUnit(BrowserVersion.INTERNET_EXPLORER_11);
		}
		executeOnHtmlUnit(BrowserVersion.CHROME);
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

	public void openTestPage() {
		$.url(url);
	}

	public void runTests() throws Throwable {
		base.evaluate();
	}

	public void executeMethodForDriver(String driver) {
		System.out.println("   @## >>> Running on "+driver);
		openTestPage();
		try {
			runTests();
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
		if (!failed.isEmpty()) {
			throw new AssertionError("There are test failures in some drivers: "+failed, this.firstFailure);
		}
	}

}