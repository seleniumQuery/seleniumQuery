package io.github.seleniumquery.by;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import io.github.seleniumquery.by.enhancements.CheckedSelector;
import io.github.seleniumquery.by.enhancements.ContainsSelector;
import io.github.seleniumquery.by.enhancements.DisabledSelector;
import io.github.seleniumquery.by.enhancements.EnabledSelector;
import io.github.seleniumquery.by.enhancements.EqSelector;
import io.github.seleniumquery.by.enhancements.HiddenSelector;
import io.github.seleniumquery.by.enhancements.NotSelector;
import io.github.seleniumquery.by.enhancements.PresentSelector;
import io.github.seleniumquery.by.enhancements.SelectedSelector;
import io.github.seleniumquery.by.enhancements.SeleniumQueryEnhancement;
import io.github.seleniumquery.by.enhancements.VisibleSelector;

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
	 * Enhancements to be applied to selector.
	 * 
	 * @author acdcjunior
	 * @since 0.2.0
	 */
	private static List<? extends SeleniumQueryEnhancement> enhancements = Arrays.asList(new NotSelector(), new EqSelector(),
			new ContainsSelector(), new SelectedSelector(), new VisibleSelector(), new HiddenSelector(), new EnabledSelector(),
			new DisabledSelector(), new CheckedSelector(), new PresentSelector());
	
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
					"Try not using more than one .queryUntil() in a single line.");
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
	private boolean isXPathExpression(String selector) {
		return selector != null && selector.matches("\\(*/.*");
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
			return "("+this.selector+")["+position+"]";
		}
		return this.selector+":eq("+position+")";
	}
	
	/**
	 * Applies all enhancements, such as :eq(), :not(), etc. and returns.
	 * 
	 * If no enhancement is appliable, ordinary CSS selection is used.
	 * 
	 * @author acdcjunior
	 * @since 0.2.0
	 */
	private List<WebElement> enhancedCssFindElements(SearchContext context) {
		for (SeleniumQueryEnhancement enhancement : enhancements) {
			if (enhancement.isApplicable(this.selector, context)) {
				return enhancement.apply(this.selector, context);
			}
		}
		return new By.ByCssSelector(this.selector).findElements(context);
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