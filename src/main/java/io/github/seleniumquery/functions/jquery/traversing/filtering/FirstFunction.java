package io.github.seleniumquery.functions.jquery.traversing.filtering;

import io.github.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * http://api.jquery.com/first/
 *
 * $("selector").first()
 *
 * @author acdcjunior
 * @author ricardo-sc
 *
 * @since 0.9.0
 */
public class FirstFunction {
	
	public static SeleniumQueryObject first(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements) {
		return EqFunction.eq(seleniumQueryObject, elements, 0);
	}

}