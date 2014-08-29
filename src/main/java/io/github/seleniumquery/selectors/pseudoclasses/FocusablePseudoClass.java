package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.locator.ElementFilter;
import io.github.seleniumquery.selector.CompiledCssSelector;
import io.github.seleniumquery.selector.SqXPathSelector;
import io.github.seleniumquery.selector.XPathSelectorFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @see http://api.jqueryui.com/focusable-selector/
 * 
 * Some elements are natively focusable, while others require explicitly setting a tab index. In all cases, the element must be visible in order to be focusable.
 * 
 * Elements of the following type are focusable:
 * - input, if not disabled;
 * - select, if not disabled;
 * - textarea, if not disabled;
 * - button if not disabled;
 * - anchors, if they have an href or tabindex attribute.
 * - area elements are focusable if they are inside a named map, have an href attribute, and there is a visible image using the map.
 * - ALL OTHER elements are focusable based solely on their tabindex attribute and visibility.
 * 
 * @author acdcjunior
 * @since 1.0.0
 */
public class FocusablePseudoClass implements PseudoClass {
	
	private static final FocusablePseudoClass instance = new FocusablePseudoClass();
	public static FocusablePseudoClass getInstance() {
		return instance;
	}
	private FocusablePseudoClass() { }
	
	private static final String FOCUSABLE_PSEUDO_CLASS_NO_COLON = "focusable";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return FOCUSABLE_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		if (!element.isDisplayed()) {
			return false;
		}
		if (DisabledPseudoClass.DISABLEABLE_TAGS.contains(element.getTagName())) {
			return element.isEnabled();
		}
		if (element.getTagName().equals("a") && element.getAttribute("href") != null) {
			return true;
		}
		if (element.getTagName().equals("area") && element.getAttribute("href") != null /* && inside a named map */ /* && there is a visible image using the map */) {
			return true;
		}
		return element.getAttribute("tabindex") != null;
	}
	
	private static final ElementFilter focusablePseudoClassFilter = new PseudoClassFilter(getInstance());
	@Override
	public CompiledCssSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// no browser supports :focusable natively
		return CompiledCssSelector.createFilterOnlySelector(focusablePseudoClassFilter);
	}
	// //button[.='OK' and not(ancestor::div[contains(@style,'display:none')]) and ]
	
	
	// upon changing the expression below, check also the one at :tabbable
	public static final String FOCUSABLE_XPATH = "("
			+ VisiblePseudoClass.NOT_DISPLAY_NONE_XPATH
			+ " and "
				+ " ("
					+ " ("
						+ " (name() = 'input' or name() = 'button' or name() = 'optgroup' or name() = 'option' or name() = 'select' or name() = 'textarea')"
						+ " and "
						+ EnabledPseudoClass.ENABLED_XPATH
					+ " ) "
					+ " or "
					+ " (name() = 'a' and @href) "
					+ " or "
					+ " (name() = 'area' and @href)"
					+ " or "
					+ " @tabindex"
				+ ")"
			+ ")";
	
	@Override
	public SqXPathSelector pseudoClassToXPath(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// #no-xpath
		System.err.println(":focusable is not fully XPath supported (if the 'display:none' is in a CSS class, it won't know)!!!");
		return XPathSelectorFactory.createNoFilterSelector("[" + FOCUSABLE_XPATH + "]");
	}
	
}