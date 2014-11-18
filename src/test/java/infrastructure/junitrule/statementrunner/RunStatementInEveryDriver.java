package infrastructure.junitrule.statementrunner;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.junit.runners.model.Statement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static io.github.seleniumquery.SeleniumQuery.$;

public class RunStatementInEveryDriver extends Statement {

    private final StatementRunner statementRunner;

    public RunStatementInEveryDriver(StatementRunner statementRunner) {
        super();
        this.statementRunner = statementRunner;
    }

    @Override
    public void evaluate() throws Throwable {
        executeAllHtmlUnits();
        executeAllOtherDrivers();
        statementRunner.reportFailures();
    }

    @SuppressWarnings("deprecation")
    private void executeAllHtmlUnits() {
        executeOnHtmlUnit(BrowserVersion.FIREFOX_17);
        executeOnHtmlUnit(BrowserVersion.FIREFOX_24);
        executeOnHtmlUnit(BrowserVersion.INTERNET_EXPLORER_8);
        executeOnHtmlUnit(BrowserVersion.INTERNET_EXPLORER_9);
        executeOnHtmlUnit(BrowserVersion.INTERNET_EXPLORER_11);
        executeOnHtmlUnit(BrowserVersion.CHROME);
    }

    private void executeAllOtherDrivers() {
        executeTestOnChrome();
        executeTestOnIE();
        executeTestOnFirefox();
        executeTestOnPhantomJS();
    }

    private void executeTestOnChrome() {
        System.out.println("@## > Instantiating Chrome Driver");
        $.browser.setDefaultDriverAsChrome();
        statementRunner.executeMethodForDriver("Chrome");
        $.browser.quitDefaultDriver();
    }

    private void executeTestOnIE() {
        System.out.println("@## > Instantiating on IE");
        $.browser.setDefaultDriverAsIE();
        statementRunner.executeMethodForDriver("IE");
        $.browser.quitDefaultDriver();
    }

    private void executeTestOnFirefox() {
        System.out.println("@## > Instantiating Firefox Driver");
        $.browser.setDefaultDriverAsFirefox();
        statementRunner.executeMethodForDriver("Firefox");
        $.browser.quitDefaultDriver();
    }

    private void executeTestOnPhantomJS() {
        System.out.println("@## > Instantiating PhantomJS Driver");
        $.browser.setDefaultDriverAsPhantomJS();
        statementRunner.executeMethodForDriver("PhantomJS");
        $.browser.quitDefaultDriver();
    }

    private void executeOnHtmlUnit(BrowserVersion browserVersion) {
        System.out.println("@## > Instantiating HtmlUnit ("+browserVersion+") Driver - JavaScript ON");
        $.browser.setDefaultDriver(createHtmlUnitDriverWithJavasCript(browserVersion, true));
        statementRunner.executeMethodForDriver("HtmlUnit(" + browserVersion + ")");
        $.browser.quitDefaultDriver();

        System.out.println("@## > Instantiating HtmlUnit ("+browserVersion+") Driver - JavaScript OFF");
        $.browser.setDefaultDriver(createHtmlUnitDriverWithJavasCript(browserVersion, false));
        statementRunner.executeMethodForDriver("HtmlUnit(" + browserVersion + ")");
        $.browser.quitDefaultDriver();
    }

    private HtmlUnitDriver createHtmlUnitDriverWithJavasCript(BrowserVersion browserVersion, boolean enableJavascript) {
        HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(browserVersion);
        htmlUnitDriver.setJavascriptEnabled(enableJavascript);
        return htmlUnitDriver;
    }

}