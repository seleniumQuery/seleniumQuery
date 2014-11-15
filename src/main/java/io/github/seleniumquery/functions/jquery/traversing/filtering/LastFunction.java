package io.github.seleniumquery.functions.jquery.traversing.filtering;

import io.github.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * http://api.jquery.com/last/
 *
 * $("selector").last()
 *
 * @author acdcjunior
 * @author ricardo-sc
 *
 * @since 1.0.0
 */
public class LastFunction {
	
	public static SeleniumQueryObject last(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements) {
		return EqFunction.eq(seleniumQueryObject, elements, -1);
	}

}