package io.github.seleniumquery.by.css.pseudoclasses;

import io.github.seleniumquery.by.SelectorUtils;
import io.github.seleniumquery.by.filter.ElementFilter;
import io.github.seleniumquery.by.xpath.component.XPathComponent;
import io.github.seleniumquery.by.xpath.component.XPathComponentFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * :hidden
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class HiddenPseudoClass implements PseudoClass {
	
	private static final String HIDDEN_PSEUDO_CLASS_NO_COLON = "hidden";

    private final ElementFilter hiddenPseudoClassFilter = new PseudoClassFilter(this);

	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return HIDDEN_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}

	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return !SelectorUtils.isVisible(element);
	}

	public static final String HIDDEN_XPATH_MUST_FILTER = "("
			// we consider an element to be hidden when...
			
			// element itself or ancestor have 'display: none'
			+ " ancestor-or-self::*[contains(normalize-space(@style),'display: none') or contains(normalize-space(@style),'display:none')] "
			// element itself or ancestor have a class (a class itself won't hide the element, but it could! The filter exists to check exactly if
			// the class' style is hiding the element or its children)
			+ " or ancestor-or-self::*[@class] "
			// it is not under body (and is not <html> itself), because all <head> elements are not visible!
			+ " or (count(ancestor-or-self::body) = 0 and local-name() != 'html')"
			+ ")";
	
	@Override
	public XPathComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		UnsupportedXPathPseudoClassException.xPathFiltersAreNotImplementedYed(":hidden");

		// #not-pure-xpath // it is not pure because XPath can't see the styles affecting the element's classes
		return XPathComponentFactory.createSimpleConditional("[" + HIDDEN_XPATH_MUST_FILTER + "]", hiddenPseudoClassFilter);
	}
	
}