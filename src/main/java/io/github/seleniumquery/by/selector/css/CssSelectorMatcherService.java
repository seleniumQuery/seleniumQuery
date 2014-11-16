package io.github.seleniumquery.by.selector.css;

import io.github.seleniumquery.by.selector.parser.ParsedSelector;
import io.github.seleniumquery.by.selector.parser.SelectorParser;

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
		CssSelector<Selector> cssSelector = CssSelectorFactory.parsedSelectorToCssSelector(selector);
		return cssSelector.is(driver, element, stringMap, selector);	
	}

}