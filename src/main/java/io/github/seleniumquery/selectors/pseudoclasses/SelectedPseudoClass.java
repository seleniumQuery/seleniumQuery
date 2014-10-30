package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.selectorxpath.XPathExpression;
import io.github.seleniumquery.selectorxpath.XPathSelectorFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * https://developer.mozilla.org/en-US/docs/Web/CSS/:selected
 * 
 * @author acdcjunior
 * @since 1.0.0
 */
public class SelectedPseudoClass implements PseudoClass {
	
	private static final SelectedPseudoClass instance = new SelectedPseudoClass();
	public static SelectedPseudoClass getInstance() {
		return instance;
	}
	private SelectedPseudoClass() { }
	
	private static final String OPTION_TAG = "option";
	
	private static final String SELECTED_PSEUDO_CLASS_NO_COLON = "selected";

	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return SELECTED_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return isSelected(element);
	}
	
	public boolean isSelected(WebElement element) {
		return element.getTagName().equals(OPTION_TAG) && element.isSelected();
	}
	
	@Override
	public XPathExpression pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		return XPathSelectorFactory.createNoFilterSelector("[local-name() = 'option' and @selected]");
	}

}