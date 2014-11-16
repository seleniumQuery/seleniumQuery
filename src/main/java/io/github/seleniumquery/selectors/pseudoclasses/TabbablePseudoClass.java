package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.by.filter.ElementFilter;
import io.github.seleniumquery.by.selector.xpath.XPathExpression;
import io.github.seleniumquery.by.selector.xpath.XPathExpressionFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * :tabbable elements are all those :focusable, except those that have a negative tabindex.
 * 
 * From http://api.jqueryui.com/tabbable-selector/: "Elements with a negative tab index are :focusable, but not :tabbable."
 * 
 * @author acdcjunior
 * @since 1.0.0
 */
public class TabbablePseudoClass implements PseudoClass {
	
	private static final String TABBABLE_PSEUDO_CLASS_NO_COLON = "tabbable";

	private final FocusablePseudoClass focusable = new FocusablePseudoClass();
    private final ElementFilter tabbablePseudoClassFilter = new PseudoClassFilter(this);

	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return TABBABLE_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}

	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		boolean isFocusable = focusable.isPseudoClass(driver, element, pseudoClassSelector);
		if (!isFocusable) {
			return false;
		}
		// at this point, it is focusable!
		String tabindex = element.getAttribute("tabindex");
		if (tabindex == null) {
			return true;
		}
		// at this point, there is a tabindex
		boolean tabindexIsNegativeInteger = tabindex.matches("\\s*-\\d+\\s*");
		return !tabindexIsNegativeInteger;
	}

	// see :focusable. change there before here, this selector is highly dependable on :focusable as it is just a small change to it
	@Override
	public XPathExpression pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		UnsupportedXPathPseudoClassException.xPathFiltersAreNotImplementedYed(":tabbable");
		
		// #no-xpath
		System.err.println(":tabbable is not fully XPath supported (if the 'display:none' is in a CSS class, it won't know)!!!");
		return XPathExpressionFactory.create("[(" + FocusablePseudoClass.FOCUSABLE_XPATH + " and (not(@tabindex) or @tabindex > -1))]",
				tabbablePseudoClassFilter);
	}
	
}