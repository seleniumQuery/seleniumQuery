package io.github.seleniumquery;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;

public class TestInfrastructure {
	
	private static final String TEST_SRC_FOLDER = "src/test/java/";

	/**
	 * Driver to be used by all tests.
	 * This method exists so we can, from time to time, run all tests in a different browser.
	 */
	public static WebDriver getDriver() {
		@SuppressWarnings("deprecation")
		HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(BrowserVersion.FIREFOX_3_6);
		
		htmlUnitDriver.setJavascriptEnabled(true);
		return htmlUnitDriver;
	}
	
	// just a quick function so when I add a simple argument, the browser is switched to firefox:
	// $.browser.setDefaultDriver(TestInfrastructure.getDriver()); --> runs htmlunit
	// $.browser.setDefaultDriver(TestInfrastructure.getDriver(1)); --> runs firefox
	@Deprecated
	public static WebDriver getDriver(@SuppressWarnings("unused") int... key) {
		return new FirefoxDriver();
	}
	
	public static String getHtmlTestFileUrl(Class<?> clazz) {
		String classFullName = clazz.getName();
		String classPath = classFullName.replace('.', '/');
		String htmlPath = TEST_SRC_FOLDER + classPath + ".html"; 
		return new File(htmlPath).toURI().toString();
	}
	
}