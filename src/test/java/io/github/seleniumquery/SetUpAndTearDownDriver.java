package io.github.seleniumquery;

import static io.github.seleniumquery.SeleniumQuery.$;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
 
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
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());
		
//		driver = new FirefoxDriver();
//    	System.setProperty("webdriver.chrome.driver", "F:\\desenv\\chromedriver.exe");
//    	driver = new ChromeDriver();
//		System.setProperty("webdriver.ie.driver",  "F:\\desenv\\IEDriverServer.exe");
//    	driver = new InternetExplorerDriver();
		
		if (htmlTestUrlClass == null) {
			$.browser.openUrl(TestInfrastructure.getHtmlTestFileUrl(testClassInstance.getClass()));
		} else {
			$.browser.openUrl(TestInfrastructure.getHtmlTestFileUrl(htmlTestUrlClass));
		}
	}
	
	private void after() {
		$.browser.quitDefaultBrowser();
	}
 
}