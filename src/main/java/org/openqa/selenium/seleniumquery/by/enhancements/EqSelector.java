package org.openqa.selenium.seleniumquery.by.enhancements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.by.SeleniumQueryBy;

public class EqSelector implements SeleniumQueryEnhancement {
	
	private static final String EQ_PATTERN = "(.*)"+"(?<!\\\\):"+"eq\\(([0-9]+)\\)$";

	@Override
	public boolean isApplicable(String selector) {
		return selector.matches(EQ_PATTERN);
	}

	@Override
	public List<WebElement> apply(String selector, SearchContext context) {
		String effectiveSelector = selector;
		Integer eqIndex = null;
		
		Pattern p = Pattern.compile(EQ_PATTERN);
		Matcher m = p.matcher(selector);
		
		m.find(); // trigger regex matching so .group() is available
		effectiveSelector = m.group(1);
		eqIndex = Integer.parseInt(m.group(2));

		List<WebElement> elementsFound = SeleniumQueryBy.byEnhancedSelector(effectiveSelector).findElements(context);
		ArrayList<WebElement> zeroOrOneElementList = new ArrayList<WebElement>();
		if (elementsFound.size() > eqIndex) {
			zeroOrOneElementList.add(elementsFound.get(eqIndex));
		}
		return zeroOrOneElementList;
	}

}