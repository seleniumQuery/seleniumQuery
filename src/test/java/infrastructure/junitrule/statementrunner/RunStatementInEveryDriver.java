package infrastructure.junitrule.statementrunner;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import infrastructure.junitrule.DriverToRunTestsIn;
import org.junit.runners.model.Statement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static io.github.seleniumquery.SeleniumQuery.$;

public class RunStatementInEveryDriver extends Statement {

    private final StatementRunner statementRunner;
    private final DriverToRunTestsIn driverToRunTestsIn;

    public RunStatementInEveryDriver(DriverToRunTestsIn driverToRunTestsIn, StatementRunner statementRunner) {
        super();
        this.driverToRunTestsIn = driverToRunTestsIn;
        this.statementRunner = statementRunner;
    }

    @Override
    public void evaluate() throws Throwable {
        executeTestOnHtmlUnits();
        executeTestOnChrome();
        executeTestOnIE();
        executeTestOnFirefox();
        executeTestOnPhantomJS();
        statementRunner.reportFailures();
    }

    @SuppressWarnings("deprecation")
    private void executeTestOnHtmlUnits() {
        if (!driverToRunTestsIn.canRunHtmlUnit()) {
            return;
        }
        executeOnHtmlUnit(BrowserVersion.FIREFOX_17);
        executeOnHtmlUnit(BrowserVersion.FIREFOX_24);
        executeOnHtmlUnit(BrowserVersion.INTERNET_EXPLORER_8);
        executeOnHtmlUnit(BrowserVersion.INTERNET_EXPLORER_9);
        executeOnHtmlUnit(BrowserVersion.INTERNET_EXPLORER_11);
        executeOnHtmlUnit(BrowserVersion.CHROME);
    }

    private void executeTestOnChrome() {
        if (!driverToRunTestsIn.canRunChrome()) {
            return;
        }
        System.out.println("@## > Instantiating Chrome Driver");
        $.browser.globalDriver().useChrome();
        statementRunner.executeMethodForDriver("Chrome");
        $.browser.quit();
    }

    private void executeTestOnIE() {
        if (!driverToRunTestsIn.canRunIE()) {
            return;
        }
        System.out.println("@## > Instantiating on IE");
        $.browser.globalDriver().useInternetExplorer();
        statementRunner.executeMethodForDriver("IE");
        $.browser.quit();
    }

    private void executeTestOnFirefox() {
        if (!driverToRunTestsIn.canRunFirefox()) {
            return;
        }
        System.out.println("@## > Instantiating Firefox Driver");
        $.browser.globalDriver().useFirefox();
        statementRunner.executeMethodForDriver("Firefox");
        $.browser.quit();
    }

    private void executeTestOnPhantomJS() {
        if (!driverToRunTestsIn.canRunPhantomJS()) {
            return;
        }
        System.out.println("@## > Instantiating PhantomJS Driver");
        $.browser.globalDriver().usePhantomJS();
        statementRunner.executeMethodForDriver("PhantomJS");
        $.browser.quit();
    }

    private void executeOnHtmlUnit(BrowserVersion browserVersion) {
        System.out.println("@## > Instantiating HtmlUnit ("+browserVersion+") Driver - JavaScript ON");
        $.browser.globalDriver().use(createHtmlUnitDriverWithJavasCript(browserVersion, true));
        statementRunner.executeMethodForDriver("HtmlUnit(" + browserVersion + ")");
        $.browser.quit();

        System.out.println("@## > Instantiating HtmlUnit ("+browserVersion+") Driver - JavaScript OFF");
        $.browser.globalDriver().use(createHtmlUnitDriverWithJavasCript(browserVersion, false));
        statementRunner.executeMethodForDriver("HtmlUnit(" + browserVersion + ")");
        $.browser.quit();
    }

    private HtmlUnitDriver createHtmlUnitDriverWithJavasCript(BrowserVersion browserVersion, boolean enableJavascript) {
        HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(browserVersion);
        htmlUnitDriver.setJavascriptEnabled(enableJavascript);
        return htmlUnitDriver;
    }

}