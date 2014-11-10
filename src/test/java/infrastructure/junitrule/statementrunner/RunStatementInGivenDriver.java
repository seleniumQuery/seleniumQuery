package infrastructure.junitrule.statementrunner;

import org.junit.runners.model.Statement;

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

        $.browser.quitDefaultBrowser();

        sQBeforeAfterer.reportFailures();
    }

    private void setUpDriver() {
		$.browser.setDefaultDriverAsHtmlUnit();
//		$.browser.setDefaultDriverAsChrome();
//		$.browser.setDefaultDriverAsIE();
//      $.browser.setDefaultDriverAsFirefox();
//		$.browser.setDefaultDriverAsPhantomJS();
    }

}