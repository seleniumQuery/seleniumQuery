package infrastructure.junitrule.statementrunner;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.junit.runners.model.Statement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.junit.Assert.fail;

public class RunStatementInEveryDriver extends Statement {

    private final StatementRunner sQBeforeAfterer;

    public RunStatementInEveryDriver(StatementRunner sQBeforeAfterer) {
        super();
        this.sQBeforeAfterer = sQBeforeAfterer;
    }

    @Override
    public void evaluate() throws Throwable {
        executeAllHtmlUnits();
        executeAllOtherDrivers();
        sQBeforeAfterer.reportFailures();
    }

    @SuppressWarnings("deprecation")
    private void executeAllHtmlUnits() {
        executeInHtmlUnit(BrowserVersion.FIREFOX_3_6);
        executeInHtmlUnit(BrowserVersion.FIREFOX_10);
        executeInHtmlUnit(BrowserVersion.FIREFOX_17);
        executeInHtmlUnit(BrowserVersion.CHROME_16);
        executeInHtmlUnit(BrowserVersion.CHROME);
        executeInHtmlUnit(BrowserVersion.INTERNET_EXPLORER_6);
        executeInHtmlUnit(BrowserVersion.INTERNET_EXPLORER_7);
        executeInHtmlUnit(BrowserVersion.INTERNET_EXPLORER_8);
        executeInHtmlUnit(BrowserVersion.INTERNET_EXPLORER_9);
        executeInHtmlUnit(BrowserVersion.INTERNET_EXPLORER_10);
    }

    private void executeAllOtherDrivers() {
        System.out.println("@# Running on Chrome");
        $.browser.setDefaultDriverAsChrome();
        sQBeforeAfterer.executeMethodForDriver("Chrome");
        $.browser.quitDefaultBrowser();

//			System.out.println("@# Running on IE10");
//			$.browser.setDefaultDriverAsIE();
//			sQBeforeAfterer.executeMethodForDriver("IE10");
//          $.browser.quitDefaultBrowser();

        System.out.println("@# Running on Firefox");
        $.browser.setDefaultDriverAsFirefox();
        sQBeforeAfterer.executeMethodForDriver("Firefox");
        $.browser.quitDefaultBrowser();

        System.out.println("@# Running on PhantomJS");
        $.browser.setDefaultDriverAsPhantomJS();
        sQBeforeAfterer.executeMethodForDriver("PhantomJS");
        $.browser.quitDefaultBrowser();
    }

    private void executeInHtmlUnit(BrowserVersion browserVersion) {
        System.out.println("@# Running on HtmlUnit ("+browserVersion+")");
        $.browser.setDefaultDriver(createHtmlUnitDriverWithJavasCriptEnabled(browserVersion));
        sQBeforeAfterer.executeMethodForDriver("HtmlUnit(" + browserVersion.toString() + ")");
        $.browser.quitDefaultBrowser();
    }

    private HtmlUnitDriver createHtmlUnitDriverWithJavasCriptEnabled(final BrowserVersion browserVersion) {
        HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(browserVersion);
        htmlUnitDriver.setJavascriptEnabled(true);
        return htmlUnitDriver;
    }

}