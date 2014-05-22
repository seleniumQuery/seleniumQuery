package io.github.seleniumquery;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.junit.Assert.fail;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;
 
public class SetUpAndTearDownDriver implements MethodRule {
	
	private Class<?> htmlTestUrlClass;
	
	public SetUpAndTearDownDriver() {
		this.htmlTestUrlClass = null;
	}
	public SetUpAndTearDownDriver(Class<?> htmlTestUrlClass) {
		this.htmlTestUrlClass = htmlTestUrlClass;
	}
	
	@Override
	public Statement apply(final Statement base, FrameworkMethod method, final Object target) {
		return new RunStatementInEveryBrowser(base, target);
//		return new RunStatementForSelectedBrowser(base, target);
	}
	
	class RunStatementInEveryBrowser extends Statement {
		private Statement base;
		private Object target;
		private boolean failed = false;
		public RunStatementInEveryBrowser(Statement base, Object target) {
			super();
			this.base = base;
			this.target = target;
		}
		
		@SuppressWarnings("deprecation")
		@Override
		public void evaluate() throws Throwable {
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
			System.out.println("@# Running on Chrome");
			$.browser.setDefaultDriverAsChrome();
			execute();
			System.out.println("@# Running on IE10");
			$.browser.setDefaultDriverAsIE();
			execute();
			System.out.println("@# Running on Firefox");
			$.browser.setDefaultDriverAsFirefox();
			execute();
			System.out.println("@# Running on PhantomJS");
			$.browser.setDefaultDriverAsPhantomJS();
			execute();
			if (failed) {
				fail("There are test failures.");
			}
		}
		
		private void execute() throws Throwable {
			before(target);
			try {
				base.evaluate();
			} catch (Throwable t) {
				failed = true;
				System.err.println("Test failed!"+t.getMessage());
			} finally {
				after();
			}
		}
		
		private void executeInHtmlUnit(BrowserVersion browserVersion) throws Throwable {
			System.out.println("@# Running on HtmlUnit ("+browserVersion+")");
			$.browser.setDefaultDriver(createHtmlUnitDriverWithJavasCriptEnabled(browserVersion));
			execute();
		}
	}
	
	class RunStatementForSelectedBrowser extends Statement {
		private Statement base;
		private Object target;
		public RunStatementForSelectedBrowser(Statement base, Object target) {
			super();
			this.base = base;
			this.target = target;
		}
		@Override
		public void evaluate() throws Throwable {
			before(target);
			try {
				base.evaluate();
			} finally {
				after();
			}
		}
	}
	
	private void before(Object testClassInstance) {
//		$.browser.setDefaultDriverAsHtmlUnit();
//		$.browser.setDefaultDriverAsChrome();
//		$.browser.setDefaultDriverAsIE();
//		$.browser.setDefaultDriverAsFirefox();
//		$.browser.setDefaultDriverAsPhantomJS();
		openPage(testClassInstance);
	}
	
	private void openPage(Object testClassInstance) {
		if (htmlTestUrlClass == null) {
			$.browser.openUrl(TestInfrastructure.getHtmlTestFileUrl(testClassInstance.getClass()));
		} else {
			$.browser.openUrl(TestInfrastructure.getHtmlTestFileUrl(htmlTestUrlClass));
		}
	}
	
	private void after() {
		$.browser.quitDefaultBrowser();
	}
	
	private static HtmlUnitDriver createHtmlUnitDriverWithJavasCriptEnabled(final BrowserVersion browserVersion) {
		HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(browserVersion);
		htmlUnitDriver.setJavascriptEnabled(true);
		return htmlUnitDriver;
	}
	
}