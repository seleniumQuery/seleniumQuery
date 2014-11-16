package infrastructure.junitrule.statementrunner;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.junit.runners.model.Statement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.junit.Assert.fail;

public class RunStatementInGivenDriver extends Statement {

    private final StatementRunner sQBeforeAfterer;

    public RunStatementInGivenDriver(StatementRunner sQBeforeAfterer) {
        super();
        this.sQBeforeAfterer = sQBeforeAfterer;
    }

    @Override
    public void evaluate() throws Throwable {
        setUpDriver();

        String driverName = $.browser.getDefaultDriver().getClass().getSimpleName();
        sQBeforeAfterer.executeMethodForDriver(driverName);

        $.browser.quitDefaultDriver();

        sQBeforeAfterer.reportFailures();
    }

    private void setUpDriver() {
//		$.browser.setDefaultDriverAsHtmlUnit();
        $.browser.setDefaultDriver(new HtmlUnitDriver(BrowserVersion.FIREFOX_24));
//		$.browser.setDefaultDriverAsChrome();
//		$.browser.setDefaultDriverAsIE();
//      $.browser.setDefaultDriverAsFirefox();
//		$.browser.setDefaultDriverAsPhantomJS();
    }

}