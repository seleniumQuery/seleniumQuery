package org.openqa.selenium.seleniumquery;

import static org.openqa.selenium.seleniumquery.SeleniumQuery.$;

import java.io.File;

import org.openqa.selenium.WebDriver;

public class TestInfrastructure {
	
	private static final String TEST_SRC_FOLDER = "src/test/java/";

	/**
	 * Driver to be used by all tests.
	 * This method exists so we can, from time to time, run all tests in a different browser.
	 */
	public static WebDriver getDriver() {
		return $.browser.getDefaultDriver();
	}
	
	public static String getHtmlTestFileUrl(Class<?> clazz) {
		String classFullName = clazz.getName();
		String classPath = classFullName.replace('.', '/');
		String htmlPath = TEST_SRC_FOLDER + classPath + ".html"; 
		return new File(htmlPath).toURI().toString();
	}
	
}