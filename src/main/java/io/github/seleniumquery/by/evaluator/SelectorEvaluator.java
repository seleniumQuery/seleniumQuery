package io.github.seleniumquery.by.evaluator;

import io.github.seleniumquery.by.parser.ParsedSelector;
import io.github.seleniumquery.by.parser.SelectorParser;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SelectorList;

public class SelectorEvaluator {
	
	public static boolean elementMatchesStringSelector(WebDriver driver, WebElement element, String selector) {
		ParsedSelector<SelectorList> parsedSelector = SelectorParser.parseSelector(selector);
		SelectorList selectorList = parsedSelector.getSelector();
        for (int i = 0; i < selectorList.getLength(); i++) {
			if (!SelectorEvaluator.elementMatchesSelector(driver, element, parsedSelector.getStringMap(), selectorList.item(i))) {
				return false;
			}
		}
        return true;
	}

	public static boolean elementMatchesSelector(WebDriver driver, WebElement element, Map<String, String> stringMap, Selector selector) {
		@SuppressWarnings("unchecked")
		CSSSelector<Selector> cssSelector =  (CSSSelector<Selector>) SelectorEvaluatorFactory.getInstance().getSelector(selector);
		return cssSelector.is(driver, element, stringMap, selector);	
	}

}