package io.github.seleniumquery.by.evaluator;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public class DriverSupportMap {
	
	private static final DriverSupportMap instance = new DriverSupportMap();
	public static DriverSupportMap getInstance() {
		return instance;
	}
	private DriverSupportMap() { }
	
	private Map<WebDriver, Map<String, Boolean>> driverSupportedPseudos = new HashMap<WebDriver, Map<String, Boolean>>();
	
	public boolean supportsNatively(WebDriver driver, String pseudoClass) {
		Map<String, Boolean> driverMap = getDriverMap(driver);
		Boolean supports = driverMap.get(pseudoClass);
		if (supports == null) {
			boolean supported = testPseudoClassNativeSupport(pseudoClass, driver);
			driverMap.put(pseudoClass, supports);
			return supported;
		}
		return supports;
	}
	
	static boolean testPseudoClassNativeSupport(String pseudo, SearchContext context) {
		try {
			By.cssSelector("#AAA_SomeIdThatShouldNotExist"+pseudo).findElements(context);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Returns the Map for the given driver, initializing it if necessary.
	 */
	private Map<String, Boolean> getDriverMap(WebDriver driver) {
		Map<String, Boolean> driverMap = driverSupportedPseudos.get(driver);
		if (driverMap == null) {
			driverMap = new HashMap<String, Boolean>();
			driverSupportedPseudos.put(driver, driverMap);
		}
		return driverMap;
	}
	
	private static final String HTML_UNIT_DRIVER_CLASSNAME = "HtmlUnitDriver";
	public static boolean isHtmlUnitDriver(Object instance) {
		return instance.getClass().getSimpleName().equals(HTML_UNIT_DRIVER_CLASSNAME);
	}
	
	public static boolean isNotHtmlUnitDriver(Object instance) {
		return !isHtmlUnitDriver(instance);
	}
	
}