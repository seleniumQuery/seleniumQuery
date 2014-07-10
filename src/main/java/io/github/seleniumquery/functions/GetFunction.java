package io.github.seleniumquery.functions;

import io.github.seleniumquery.globalfunctions.SeleniumQueryDefaultBrowser;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebElement;

/**
 * @author acdcjunior
 * @since 1.0.0
 */
public class GetFunction {
	
	private static final Log LOGGER = LogFactory.getLog(SeleniumQueryDefaultBrowser.class);
	
	public static WebElement get(List<WebElement> elements, int index) {
		if (elements.size() <= index) {
			LOGGER.warn(".get() called on index ("+index+") larger than current .size() ("+elements.size()+").");
			return null;
		}
		return elements.get(index);
	}

	public static List<WebElement> get(List<WebElement> elements) {
		return new ArrayList<WebElement>(elements);
	}

}