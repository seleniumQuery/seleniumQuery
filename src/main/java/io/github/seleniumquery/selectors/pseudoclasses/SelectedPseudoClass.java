package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.by.selector.xpath.XPathExpression;
import io.github.seleniumquery.by.selector.xpath.XPathExpressionFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * https://developer.mozilla.org/en-US/docs/Web/CSS/:selected
 * 
 * @author acdcjunior
 * @since 1.0.0
 */
public class SelectedPseudoClass implements PseudoClass {

	public static final String SELECTED_PSEUDO_CONDITION = "local-name() = 'option' and (@selected or (ancestor::select[not(@multiple) and not(option[@selected])] and position() = 1))";
	private static final String SELECTED_PSEUDO_CONDITIONAL_EXPRESSION = "[" + SELECTED_PSEUDO_CONDITION + "]";

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
		return XPathExpressionFactory.createNoFilterSelector(SELECTED_PSEUDO_CONDITIONAL_EXPRESSION);
	}

}