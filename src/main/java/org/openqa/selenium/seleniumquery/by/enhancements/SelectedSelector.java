package org.openqa.selenium.seleniumquery.by.enhancements;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.by.SeleniumQueryBy;

/**
 * <p>Selects all <code>&lt;option&gt;<code>s that are selected.</p>
 * 
 * <p>
 * Just like jQuery, the <code>:selected<code> selector works for <code>&lt;option&gt;</code> elements.
 * It does not work for checkboxes or radio inputs; use <code>:checked</code> for them.
 * </p>
 * 
 * @author acdcjunior
 * @since 0.4.0
 */
public class SelectedSelector implements SeleniumQueryEnhancement {
	
	private static final String OPTION_TAG = "option";
	private static final String SELECTED_PATTERN = "(.*):selected";

	@Override
	public boolean isApplicable(String selector) {
		return selector.matches(SELECTED_PATTERN);
	}

	@Override
	public List<WebElement> apply(String selector, SearchContext context) {
		String effectiveSelector = selector;
		
		Pattern p = Pattern.compile(SELECTED_PATTERN);
		Matcher m = p.matcher(selector);
		
		m.find(); // trigger regex matching so .group() is available
		effectiveSelector = m.group(1);

		List<WebElement> elementsFound = SeleniumQueryBy.byEnhancedSelector(effectiveSelector).findElements(context);
		
		for (Iterator<WebElement> iterator = elementsFound.iterator(); iterator.hasNext();) {
			WebElement webElement = iterator.next();
			if (!webElement.getTagName().equals(OPTION_TAG) || !webElement.isSelected()) {
				iterator.remove();
			}
		}

		return elementsFound;
	}

}