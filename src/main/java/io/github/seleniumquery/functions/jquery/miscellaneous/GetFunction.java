package io.github.seleniumquery.functions.jquery.miscellaneous;

import io.github.seleniumquery.SeleniumQueryObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * <pre>
 * $("selector").get(index);
 * </pre>
 *
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class GetFunction {
	
	private static final Log LOGGER = LogFactory.getLog(GetFunction.class);
	
	public static WebElement get(SeleniumQueryObject caller, int index) {
		List<WebElement> elements = caller.get();
		if (elements.size() <= index) {
			LOGGER.warn(".get() called on index larger than or equal to current .size(). null was returned. Index: "+index+", Size: "+elements.size());
			return null;
		}
		return elements.get(index);
	}

}