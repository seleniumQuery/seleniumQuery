package io.github.seleniumquery;

import static io.github.seleniumquery.SeleniumQuery.$;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;
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
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				before(target);
				try {
					base.evaluate();
				} finally {
					after();
				}
			}
		};
	}
	
	private void before(Object testClassInstance) {
		$.browser.setDefaultDriver(getHtmlUnitFirefox3Driver());
//		$.browser.setDefaultDriverAsChrome();
//		$.browser.setDefaultDriverAsIE();
//		$.browser.setDefaultDriverAsFirefox();
//		$.browser.setDefaultDriverAsPhantomJS();
//		$.browser.setDefaultDriverAsHtmlUnit();
		
		
		if (htmlTestUrlClass == null) {
			$.browser.openUrl(TestInfrastructure.getHtmlTestFileUrl(testClassInstance.getClass()));
		} else {
			$.browser.openUrl(TestInfrastructure.getHtmlTestFileUrl(htmlTestUrlClass));
		}
	}
	
	/**
	 * Driver to be used by all tests.
	 * This method exists so we can, from time to time, run all tests in a different browser.
	 */
	private static WebDriver getHtmlUnitFirefox3Driver() {
		@SuppressWarnings("deprecation")
		HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(BrowserVersion.FIREFOX_3_6);
		htmlUnitDriver.setJavascriptEnabled(true);
		return htmlUnitDriver;
	}
	
	private void after() {
		$.browser.quitDefaultBrowser();
	}
	
}