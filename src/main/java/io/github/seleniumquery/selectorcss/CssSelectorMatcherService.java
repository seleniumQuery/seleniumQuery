package io.github.seleniumquery.selectorcss;

import io.github.seleniumquery.selector.parser.ParsedSelector;
import io.github.seleniumquery.selector.parser.SelectorParser;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SelectorList;

public class CssSelectorMatcherService {
	
	public static boolean elementMatchesStringSelector(WebDriver driver, WebElement element, String selector) {
		ParsedSelector<SelectorList> parsedSelector = SelectorParser.parseSelector(selector);
		SelectorList selectorList = parsedSelector.getSelector();
        for (int i = 0; i < selectorList.getLength(); i++) {
			if (CssSelectorMatcherService.elementMatchesSelector(driver, element, parsedSelector.getStringMap(), selectorList.item(i))) {
				return true;
			}
		}
        return false;
	}

	public static boolean elementMatchesSelector(WebDriver driver, WebElement element, Map<String, String> stringMap, Selector selector) {
		@SuppressWarnings("unchecked")
		CssSelector<Selector> cssSelector =  (CssSelector<Selector>) CssSelectorFactory.getInstance().getSelector(selector);
		return cssSelector.is(driver, element, stringMap, selector);	
	}

}