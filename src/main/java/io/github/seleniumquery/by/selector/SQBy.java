package io.github.seleniumquery.by.selector;

import io.github.seleniumquery.by.evaluator.SelectorUtils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SQBy extends By {
	
	/**
	 * Enhanced selector is not just the CSS selector, it also supports XPath expressions and some
	 * Sizzle enhancements.
	 * 
	 * @author acdcjunior
	 * @since 0.2.0
	 */
	public static SQBy byEnhancedSelector(final String selector) {
		if (selector == null) {
			throw new IllegalArgumentException("Cannot find elements when the selector is null");
		}
		return new SQBy(selector);
	}

	private final String selector;
	private boolean selectorIsXPathExpression;
	private CompiledSelectorList compiledSelectorList;

	/**
	 * Constructs a SeleniumQueryBy for the given selector.
	 * @param selector the selector you need the elements to match.
	 * 
	 * @author acdcjunior
	 * @since 0.2.0
	 */
	private SQBy(String selector) {
		this.selector = selector;
		this.selectorIsXPathExpression = isXPathExpression(selector);
		
	}
	
	
	/**
	 * Returns the list of elements that match this By in the given context.
	 * @return the list of elements that match this By in the given context.
	 * 
	 * @author acdcjunior
	 * @since 0.2.0
	 */
	@Override
	public List<WebElement> findElements(SearchContext context) {
		WebDriver driver = SelectorUtils.getWebDriver(context);
		this.compiledSelectorList = SeleniumQueryCssCompiler.compileSelectorList(driver, selector);
		if (this.selectorIsXPathExpression) {
			return new By.ByXPath(this.selector).findElements(context);
		}
		return this.compiledSelectorList.execute(context);
	}

	/**
	 * Returns the string representation of this By.
	 * @return the string representation of this By.
	 * 
	 * @author acdcjunior
	 * @since 0.2.0
	 */
	@Override
	public String toString() {
		return "$(\"" + selector + "\")";
	}
	
	/**
	 * If it begins with "/" or "(/" or "(((((/", we assume the selector given is a XPath expression.
	 */
	private boolean isXPathExpression(String selector) {
		return selector != null && selector.matches("\\(*/.*");
	}
	
	/**
	 * Returns the selector used in the creation of this By.
	 * @return the selector used in the creation of this By.
	 * 
	 * @author acdcjunior
	 * @since 0.3.0
	 */
	public String getSelector() {
		return this.selector;
	}
	
}