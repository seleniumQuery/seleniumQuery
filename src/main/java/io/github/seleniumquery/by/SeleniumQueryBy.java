package io.github.seleniumquery.by;

import io.github.seleniumquery.selector.SelectorUtils;
import io.github.seleniumquery.selectorcss.CompiledCssSelectorList;
import io.github.seleniumquery.selectorcss.CssSelectorCompilerService;
import io.github.seleniumquery.selectorxpath.XPathCompiledSelectorList;
import io.github.seleniumquery.selectorxpath.XPathSelectorCompilerService;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This By is a combination of the By.xpath and By.css, where the css is also enhanced with some of
 * sizzle's selectors.
 * 
 * 
 * @author acdcjunior
 * @since 0.2.0
 */
public class SeleniumQueryBy extends By {
	
	/**
	 * A By to be used in an element created with no By. Attempting to filter elements through this By
	 * will throw a RuntimeException.
	 * 
	 * @author acdcjunior
	 * @since 0.3.0
	 */
	public static final SeleniumQueryBy NO_SELECTOR_INVALID_BY = new SeleniumQueryBy(null) {
		@Override public List<WebElement> findElements(SearchContext context) {
			throw new RuntimeException("This object was instantiated without a selector, you cannot search " +
					"elements based on it as the string used to match it is unavailable.\n" +
					"Try not using more than one .waitUntil() in a single line.");
		}
	};
	
	/**
	 * Enhanced selector is not just the CSS selector, it also supports XPath expressions and some
	 * Sizzle enhancements.
	 * 
	 * @author acdcjunior
	 * @since 0.2.0
	 */
	public static SeleniumQueryBy byEnhancedSelector(final String selector) {
		if (selector == null) {
			throw new IllegalArgumentException("Cannot find elements when the selector is null");
		}
		return new SeleniumQueryBy(selector);
	}

	private final String selector;
	private boolean selectorIsXPathExpression;

	
	/**
	 * Constructs a SeleniumQueryBy for the given selector.
	 * @param selector the selector you need the elements to match.
	 * 
	 * @author acdcjunior
	 * @since 0.2.0
	 */
	private SeleniumQueryBy(String selector) {
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
		if (this.selectorIsXPathExpression) {
			return new By.ByXPath(this.selector).findElements(context);
		}
		return this.enhancedCssFindElements(context);
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

	static boolean isXPathExpression(String selector) {
		return selector != null && selector.matches("(\\s*\\(\\s*)*/.*");
	}
	
	/**
	 * Zero-based.
	 * 
	 * @param position
	 * @return the selector string for the element at <code>position</code>.
	 * 
	 * @author acdcjunior
	 * @since 0.2.0
	 */
	public String getSelectorForElementAtPosition(int position) {
		if (this.selectorIsXPathExpression) {
			// notice that, in XPath, the position is one-based.
			return "("+this.selector+")["+ (position+1) +"]";
		}
		return this.selector+":eq("+position+")";
	}
	
	/**
	 * Compiles the selector for the given context (the context
	 * will determinate what selectors are natively supported and what selectors should
	 * be handled by SeleniumQuery) and matches elements based on it.
	 * 
	 * @since 0.6.2
	 */
	private List<WebElement> enhancedCssFindElements(SearchContext context) {
		WebDriver driver = SelectorUtils.getWebDriver(context);

		if (new Object().equals("never execute this")) {
			CompiledCssSelectorList compileSelectorList = CssSelectorCompilerService.compileSelectorList(driver, this.selector);
			return compileSelectorList.execute(context);
		}
		
		XPathCompiledSelectorList xPathLocator = XPathSelectorCompilerService.compileSelectorList(driver, this.selector);
		return xPathLocator.locate(context);
		
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