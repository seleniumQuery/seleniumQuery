//package io.github.seleniumquery;
//
//import static io.github.seleniumquery.SeleniumQuery.$;
//
//import org.junit.rules.MethodRule;
//import org.junit.runners.model.FrameworkMethod;
//import org.junit.runners.model.Statement;
//import org.openqa.selenium.WebDriver;
// 
//public class SeleniumQueryRule implements MethodRule {
//	
//	private WebDriver webDriver;
//	
//	public SeleniumQueryRule(WebDriver webDriver) {
//		this.webDriver = webDriver;
//	}
//	
//	@Override
//	public Statement apply(final Statement base, FrameworkMethod method, final Object target) {
//		return new SeleniumQueryStatement(base, target);
//	}
//	
//	class SeleniumQueryStatement extends Statement {
//		private Statement base;
//		private Object target;
//		public SeleniumQueryStatement(Statement base, Object target) {
//			super();
//			this.base = base;
//			this.target = target;
//		}
//		@Override
//		public void evaluate() throws Throwable {
//			before();
//			boolean thereWasException = false;
//			try {
//				base.evaluate();
//			} catch (Exception e) {
//				thereWasException = true;
//				throw e;
//			} finally {
//				after(thereWasException);
//			}
//		}
//	}
//	
//	private void before() {
//		$.browser.setDefaultDriver(webDriver);
//	}
//	
//	private void after(boolean thereWasException) {
//		if (!thereWasException) {
//			$.browser.quitDefaultBrowser();
//		}
//	}
//	
//}