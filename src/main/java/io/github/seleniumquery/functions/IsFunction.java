package io.github.seleniumquery.functions;

import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.selector.parser.ParsedSelector;
import io.github.seleniumquery.selector.parser.SelectorParser;
import io.github.seleniumquery.selectorcss.CssSelector;
import io.github.seleniumquery.selectorcss.CssSelectorFactory;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SelectorList;

public class IsFunction {
	
	public static boolean is(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements, String selector) {
		return is(seleniumQueryObject.getWebDriver(), elements, selector);
	}
	
	public static boolean is(WebDriver driver, List<WebElement> elements, String selector) {
		ParsedSelector<SelectorList> parsedSelector = SelectorParser.parseSelector(selector);
		SelectorList parsedSelectorList = parsedSelector.getSelector();
		Map<String, String> stringMap = parsedSelector.getStringMap();

		for (int i = 0; i < parsedSelectorList.getLength(); i++) {
    		Selector parsedSimpleSelector = parsedSelectorList.item(i);
    		
    		// If there is a :not(:present), then having no element is a expected status!
    		if (hasNegatedPresent(stringMap, parsedSimpleSelector.toString()) && elements.isEmpty()) {
    			return true;
    		}

    		CssSelector<Selector> cssSelector = CssSelectorFactory.parsedSelectorToCssSelector(parsedSimpleSelector);
			for (WebElement webElement : elements) {
    			// if any matches, then it returns true
				if (cssSelector.is(driver, webElement, stringMap, parsedSimpleSelector)) {
					return true;
				}
    		}
    	}
		return false;
	}

	private static boolean hasNegatedPresent(Map<String, String> stringMap, String parsedSimpleSelector) {
		Pattern p = Pattern.compile("not-sq\\((\\d+)\\)");
		Matcher m = p.matcher(parsedSimpleSelector); 
		while (m.find()) {
		    String stringMapId = m.group(1);
		    String notPseudoClassContent = stringMap.get(stringMapId);
		    
			ParsedSelector<SelectorList> parsedPseudoClassContent = SelectorParser.parseSelector(notPseudoClassContent);
			SelectorList parsedPseudoClassContentSelectorList = parsedPseudoClassContent.getSelector();
			Map<String, String> parsedPseudoClassContentStringMap = parsedPseudoClassContent.getStringMap();
			for (int i = 0; i < parsedPseudoClassContentSelectorList.getLength(); i++) {
				Selector parsedPseudoClassContentSimpleSelector = parsedPseudoClassContentSelectorList.item(i);
				if (!hasNegatedPresent(parsedPseudoClassContentStringMap, parsedPseudoClassContentSimpleSelector.toString())) {
					return true;
				}
			}
		}
		return false;
	}
	
}