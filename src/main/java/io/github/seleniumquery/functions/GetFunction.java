package io.github.seleniumquery.functions;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

/**
 * @author acdcjunior
 * @since 0.4.0
 */
public class GetFunction {
	
	public static WebElement get(List<WebElement> elements, int index) {
		if (elements.size() < index) {
			return null;
		}
		return elements.get(index);
	}

	public static List<WebElement> get(List<WebElement> elements) {
		return new ArrayList<WebElement>(elements);
	}

}