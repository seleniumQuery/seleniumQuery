package io.github.seleniumquery.functions.jquery.miscellaneous;

import io.github.seleniumquery.globalfunctions.SeleniumQueryBrowser;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebElement;

/**
 * @author acdcjunior
 * @since 0.9.0
 */
public class GetFunction {
	
	private static final Log LOGGER = LogFactory.getLog(SeleniumQueryBrowser.class);
	
	public static WebElement get(List<WebElement> elements, int index) {
		if (elements.size() <= index) {
			LOGGER.warn(".get() called on index larger than or equal to current .size(). null was returned. Index: "+index+", Size: "+elements.size());
			return null;
		}
		return elements.get(index);
	}

	public static List<WebElement> get(List<WebElement> elements) {
		return new ArrayList<WebElement>(elements);
	}

}