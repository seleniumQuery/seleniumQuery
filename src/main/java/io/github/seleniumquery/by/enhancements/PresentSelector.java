package io.github.seleniumquery.by.enhancements;

import static io.github.seleniumquery.by.evaluator.SelectorUtils.ESCAPED_SLASHES;
import io.github.seleniumquery.by.SeleniumQueryBy;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

/**
 * @author acdcjunior
 * @since 0.5.0
 */
public class PresentSelector implements SeleniumQueryEnhancement {
	
	private static final String PRESENT_PATTERN = "(.*)"+ESCAPED_SLASHES+":present";

	@Override
	public boolean isApplicable(String selector, SearchContext context) {
		return selector.matches(PRESENT_PATTERN);
	}

	@Override
	public List<WebElement> apply(String selector, SearchContext context) {
		String effectiveSelector = selector;
		
		Pattern p = Pattern.compile(PRESENT_PATTERN);
		Matcher m = p.matcher(selector);
		
		m.find(); // trigger regex matching so .group() is available
		effectiveSelector = m.group(1);
		
		// the findElements() only picks up present elements, so no need to filter
		if (effectiveSelector.isEmpty()) {
			return new By.ByCssSelector("*").findElements(context);
		}
		return SeleniumQueryBy.byEnhancedSelector(effectiveSelector).findElements(context);
	}

}