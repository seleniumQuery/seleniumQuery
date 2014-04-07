package org.openqa.selenium.seleniumquery.by.enhancements;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.by.SeleniumQueryBy;

public class ContainsSelector implements SeleniumQueryEnhancement {
	
	private static final String CONTAINS_PATTERN = "(.*)"+":contains"+"\\("+"([^)]+)"+"\\)";

	@Override
	public boolean isApplicable(String selector) {
		return selector.matches(CONTAINS_PATTERN);
	}

	@Override
	public List<WebElement> apply(String selector, SearchContext context) {
		String effectiveSelector = selector;
		String textToContain = null;
		
		Pattern p = Pattern.compile(CONTAINS_PATTERN);
		Matcher m = p.matcher(selector);
		if (m.find()) {
			effectiveSelector = m.group(1);
			textToContain = m.group(2);
		}
		
		if (textToContain.matches("^['\"].*['\"]$")) {
			textToContain = textToContain.substring(1, textToContain.length()-2);
		}

		List<WebElement> elementsFound = SeleniumQueryBy.byEnhancedSelector(effectiveSelector).findElements(context);
		
		for (Iterator<WebElement> iterator = elementsFound.iterator(); iterator.hasNext();) {
			WebElement webElement = iterator.next();
			if (!webElement.getText().contains(textToContain)) {
				iterator.remove();
			}
		}

		return elementsFound;
	}

}