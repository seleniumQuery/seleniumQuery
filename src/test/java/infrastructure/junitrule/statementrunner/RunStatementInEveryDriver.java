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
        executeInHtmlUnit(BrowserVersion.FIREFOX_17);
        executeInHtmlUnit(BrowserVersion.FIREFOX_24);
        executeInHtmlUnit(BrowserVersion.INTERNET_EXPLORER_8);
        executeInHtmlUnit(BrowserVersion.INTERNET_EXPLORER_9);
        executeInHtmlUnit(BrowserVersion.INTERNET_EXPLORER_11);
        executeInHtmlUnit(BrowserVersion.CHROME);
    }

    private void executeAllOtherDrivers() {
        System.out.println("@# Running on Chrome");
        $.browser.setDefaultDriverAsChrome();
        statementRunner.executeMethodForDriver("Chrome");
        $.browser.quitDefaultBrowser();

//			System.out.println("@# Running on IE10");
//			$.browser.setDefaultDriverAsIE();
//			sQBeforeAfterer.executeMethodForDriver("IE10");
//          $.browser.quitDefaultBrowser();

        System.out.println("@# Running on Firefox");
        $.browser.setDefaultDriverAsFirefox();
        statementRunner.executeMethodForDriver("Firefox");
        $.browser.quitDefaultBrowser();

        System.out.println("@# Running on PhantomJS");
        $.browser.setDefaultDriverAsPhantomJS();
        statementRunner.executeMethodForDriver("PhantomJS");
        $.browser.quitDefaultBrowser();
    }

    private void executeInHtmlUnit(BrowserVersion browserVersion) {
        System.out.println("@# Running on HtmlUnit ("+browserVersion+")");
        $.browser.setDefaultDriver(createHtmlUnitDriverWithJavasCriptEnabled(browserVersion));
        statementRunner.executeMethodForDriver("HtmlUnit(" + browserVersion.toString() + ")");
        $.browser.quitDefaultBrowser();
    }

    private HtmlUnitDriver createHtmlUnitDriverWithJavasCriptEnabled(final BrowserVersion browserVersion) {
        HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(browserVersion);
        htmlUnitDriver.setJavascriptEnabled(true);
        return htmlUnitDriver;
    }

}