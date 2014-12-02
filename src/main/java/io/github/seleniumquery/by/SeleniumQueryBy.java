package io.github.seleniumquery.by;

import io.github.seleniumquery.SeleniumQueryException;
import io.github.seleniumquery.by.xpath.XPathExpressionList;
import io.github.seleniumquery.by.xpath.XPathSelectorCompilerService;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.regex.Pattern;

/**
 * This By is a combination of the By.xpath and By.css, where the CSS3, XPath, jQuery/Sizzle and others
 * selectors are supported.
 * 
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class SeleniumQueryBy extends By {
	
	/**
	 * A {@link By} to be used in an element created with no By. Attempting to filter elements through this
	 * will throw an exception.
	 * @since 0.9.0
	 */
	public static final SeleniumQueryBy NO_SELECTOR_INVALID_BY = new SeleniumQueryBy(null) {
		@Override public List<WebElement> findElements(SearchContext context) {
			throw new SeleniumQueryException("This object was instantiated without a selector, you cannot search " +
					"elements based on it as the string used to match it is unavailable.\n" +
					"Try not using more than one .waitUntil() in a single line.");
		}
	};

	private static final String STARTING_BRACES = "(\\s*\\(\\s*)*";
	private static final String XPATH_AXES = "ancestor|ancestor-or-self|attribute|child|descendant|descendant-or-self|following|following-sibling|parent|preceding|preceding-sibling|self";

	private static final Pattern XPATH_EXPRESSION_PATTERN = Pattern.compile(STARTING_BRACES + "(/|(" + XPATH_AXES + ")::).*");

	/**
	 * Enhanced selector is not just the CSS selector, it also supports XPath expressions and some
	 * Sizzle enhancements.
	 *
	 * @since 0.9.0
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
	 */
	private SeleniumQueryBy(String selector) {
		this.selector = selector;
		this.selectorIsXPathExpression = isXPathExpression(selector);
	}
	
	
	/**
	 * Returns the list of elements that match this By in the given context.
	 * @return the list of elements that match this By in the given context.
	 * @since 0.9.0
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
	 * @since 0.9.0
	 */
	@Override
	public String toString() {
		return "$(\"" + selector + "\")";
	}
	
	/**
	 * If it begins with "/" or "(/" or "(((((/" or an XPath Axis, we assume the selector given is a XPath expression.
	 */
	static boolean isXPathExpression(String selector) {
		return selector != null && XPATH_EXPRESSION_PATTERN.matcher(selector).matches();
	}
	
	/**
	 * Zero-based.
	 * 
	 * @param position Zero-based index of the wanted element.
	 * @return the selector string for the element at <code>position</code>.
	 * @since 0.9.0
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
	 */
	private List<WebElement> enhancedCssFindElements(SearchContext context) {
		XPathExpressionList xPathLocator = XPathSelectorCompilerService.compileSelectorList(this.selector);
		return xPathLocator.findWebElements(context);
	}

	/**
	 * Returns the selector used in the creation of this By.
	 * @return the selector used in the creation of this By.
	 * @since 0.9.0
	 */
	public String getSelector() {
		return this.selector;
	}
	
}