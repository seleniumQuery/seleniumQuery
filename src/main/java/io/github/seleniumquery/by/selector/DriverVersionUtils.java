package io.github.seleniumquery.by.selector;

import com.gargoylesoftware.htmlunit.WebClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Contains some utility functions for dealing with WebDrivers, such as inspecting their version.
 */
public class DriverVersionUtils {

	private static final Log LOGGER = LogFactory.getLog(DriverVersionUtils.class);
	
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
	private static final String PHANTOM_JS_DRIVER_CLASSNAME = "PhantomJSDriver";

	public static boolean isHtmlUnitDriver(Object driver) {
		return instanceEqualsClassName(driver, HTML_UNIT_DRIVER_CLASSNAME);
	}
	
	public static boolean isNotHtmlUnitDriver(Object driver) {
		return !isHtmlUnitDriver(driver);
	}
	
	public static boolean isNotPhantomJsDriver(WebDriver driver) {
		return !instanceEqualsClassName(driver, PHANTOM_JS_DRIVER_CLASSNAME);
	}
	
	private static boolean instanceEqualsClassName(Object instance, final String className) {
		return instance.getClass().getSimpleName().equals(className);
	}
	
	public static boolean isHtmlUnitDriverEmulatingIE(WebDriver driver) {
		if (!(driver instanceof HtmlUnitDriver)) {
			return false;
		}
		return DriverVersionUtils.getEmulatedBrowser((HtmlUnitDriver) driver).startsWith("IE");
	}

	public static boolean isHtmlUnitDriverEmulatingIEBelow11(WebDriver driver) {
		if (!(driver instanceof HtmlUnitDriver)) {
			return false;
		}
		String emulatedBrowser = DriverVersionUtils.getEmulatedBrowser((HtmlUnitDriver) driver);
		try {
			int ieVersion = Integer.parseInt(emulatedBrowser.substring(2));
			return ieVersion < 11;
		} catch (NumberFormatException e) {
			LOGGER.debug("Error while inspecting HtmlUnitDriver IE version.", e);
			return false;
		}
	}
	
	private static String getEmulatedBrowser(HtmlUnitDriver htmlUnitDriver) {
		try {
			// #HtmlUnit #reflection #hack
			Method getWebClientMethod = HtmlUnitDriver.class.getDeclaredMethod("getWebClient");
			boolean wasAccessibleBefore = getWebClientMethod.isAccessible();
			getWebClientMethod.setAccessible(true);
			WebClient webClient = (WebClient) getWebClientMethod.invoke(htmlUnitDriver);
			getWebClientMethod.setAccessible(wasAccessibleBefore);
			return webClient.getBrowserVersion().toString();
		} catch (Exception e) {
			LOGGER.debug("Error while inspecting HtmlUnitDriver version.", e);
			return "";
		}
	}

}