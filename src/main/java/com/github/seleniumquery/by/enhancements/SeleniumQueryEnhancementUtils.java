package org.openqa.selenium.seleniumquery.by.enhancements;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;

class SeleniumQueryEnhancementUtils {
	
	private static final String HTML_UNIT_DRIVER_CLASSNAME = "HtmlUnitDriver";

	static boolean supportsNatively(String pseudo, SearchContext context) {
		try {
			By.cssSelector("#AAA_SomeIdThatShouldNotExist"+pseudo).findElements(context);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	static boolean isHtmlUnitDriver(Object instance) {
		return instance.getClass().getSimpleName().equals(HTML_UNIT_DRIVER_CLASSNAME);
	}
	
	static boolean isNotHtmlUnitDriver(Object instance) {
		return !isHtmlUnitDriver(instance);
	}

}