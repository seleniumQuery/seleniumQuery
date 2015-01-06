package io.github.seleniumquery.by.css.pseudoclasses;

import io.github.seleniumquery.by.filter.ElementFilter;
import io.github.seleniumquery.by.xpath.XPathComponent;
import io.github.seleniumquery.by.xpath.XPathComponentFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static io.github.seleniumquery.by.SelectorUtils.isVisible;

/**
 * see -> http://api.jqueryui.com/focusable-selector/
 * No browser supports :focusable natively.
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
 *
 * @since 0.9.0
 */
public class FocusablePseudoClass implements PseudoClass {
	
	private static final String FOCUSABLE_PSEUDO_CLASS_NO_COLON = "focusable";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return FOCUSABLE_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		if (!isVisible(element)) {
			return false;
		}
		if (DisabledPseudoClass.DISABLEABLE_TAGS.contains(element.getTagName())) {
			return element.isEnabled();
		}
		if (element.getTagName().equals("a") && element.getAttribute("href") != null) {
			return true;
		}
		//noinspection SimplifiableIfStatement
		if (element.getTagName().equals("area") && element.getAttribute("href") != null /* && inside a named map */ /* && there is a visible image using the map */) {
			return true;
		}
		return element.getAttribute("tabindex") != null;
	}
	
	private final ElementFilter focusablePseudoClassFilter = new PseudoClassFilter(this);

	// //button[.='OK' and not(ancestor::div[contains(@style,'display:none')]) and ]
	
	
	// upon changing the expression below, check also the one at :tabbable
	public static final String FOCUSABLE_XPATH = "("
			+ VisiblePseudoClass.NOT_DISPLAY_NONE_XPATH
			+ " and "
				+ " ("
					+ " ("
						+ " (local-name() = 'input' or local-name() = 'button' or local-name() = 'optgroup' or local-name() = 'option' or local-name() = 'select' or local-name() = 'textarea')"
						+ " and "
						+ EnabledPseudoClass.ENABLED_XPATH
					+ " ) "
					+ " or "
					+ " (local-name() = 'a' and @href) "
					+ " or "
					+ " (local-name() = 'area' and @href)"
					+ " or "
					+ " @tabindex"
				+ ")"
			+ ")";
	
	@Override
	public XPathComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		UnsupportedXPathPseudoClassException.xPathFiltersAreNotImplementedYed(":focusable");
		
		// #no-xpath
		System.err.println(":focusable is not fully XPath supported (if the 'display:none' is in a CSS class, it won't know)!!!");
		return XPathComponentFactory.createSimpleConditional("[" + FOCUSABLE_XPATH + "]", focusablePseudoClassFilter);
	}
	
}