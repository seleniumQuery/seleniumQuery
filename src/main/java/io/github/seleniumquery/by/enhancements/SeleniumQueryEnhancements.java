package io.github.seleniumquery.by.enhancements;

import static io.github.seleniumquery.by.enhancements.SeleniumQueryEnhancementUtils.supportsNatively;

import org.openqa.selenium.SearchContext;

@SuppressWarnings("unused")
public class SeleniumQueryEnhancements {
	
	// SeleniumQueryEnhancements.isNativeCss
	public static boolean isNativeCss(String selector, SearchContext context) {
//		supportsNatively(":checked", context);
//		supportsNatively(":enabled", context);
//		supportsNatively(":disabled", context);
//		supportsNatively(selector, context);
		return false;
	}

}