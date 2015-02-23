/*
 * Copyright (c) 2015 seleniumQuery authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.seleniumquery.by;

import com.gargoylesoftware.htmlunit.WebClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Contains some utility functions for dealing with WebDrivers, such as inspecting their version.
 *
 * @author acdcjunior
 * @author ricardo-sc
 *
 * @since 0.9.0
 */
public class DriverVersionUtils {

	private static DriverVersionUtils instance = new DriverVersionUtils();

	public static DriverVersionUtils getInstance() {
		return instance;
	}

	// for test purposes
	public static void setInstance(DriverVersionUtils instance) {
		DriverVersionUtils.instance = instance;
	}

	private static final Log LOGGER = LogFactory.getLog(DriverVersionUtils.class);
	
	private Map<WebDriver, Map<String, Boolean>> driverSupportedPseudos = new HashMap<WebDriver, Map<String, Boolean>>();
	
	public boolean hasNativeSupportForPseudo(WebDriver driver, String pseudoClass) {
		Map<String, Boolean> driverMap = getDriverMap(driver);
		Boolean supports = driverMap.get(pseudoClass);
		if (supports == null) {
			boolean supported = testPseudoClassNativeSupport(pseudoClass, driver);
			driverMap.put(pseudoClass, supported);
			return supported;
		}
		return supports;
	}
	
	private boolean testPseudoClassNativeSupport(String pseudo, SearchContext context) {
		try {
			By.cssSelector("#AAA_SomeIdThatShouldNotExist"+pseudo).findElements(context);
			return true;
		} catch (Exception ignored) {
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

    public static boolean isHtmlUnitDriverEmulatingIE(WebDriver driver) {
        return DriverVersionUtils.getInstance().isHtmlUnitDriver(driver) && getEmulatedBrowser((HtmlUnitDriver) driver).startsWith("IE");
	}

	public static boolean isHtmlUnitDriverEmulatingIEBelow11(WebDriver driver) {
        if (!DriverVersionUtils.getInstance().isHtmlUnitDriver(driver)) {
			return false;
		}
		String emulatedBrowser = getEmulatedBrowser((HtmlUnitDriver) driver);
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

    public boolean isPhantomJSDriver(WebDriver driver) {
        return driver instanceof PhantomJSDriver;
    }

    public boolean isHtmlUnitDriver(WebDriver driver) {
        return driver instanceof HtmlUnitDriver;
    }

}