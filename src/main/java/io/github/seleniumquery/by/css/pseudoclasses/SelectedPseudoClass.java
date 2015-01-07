package io.github.seleniumquery.by.css.pseudoclasses;

import io.github.seleniumquery.by.xpath.component.XPathComponent;
import io.github.seleniumquery.by.xpath.component.XPathComponentFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static io.github.seleniumquery.by.WebElementUtils.isOptionTag;

/**
 * https://developer.mozilla.org/en-US/docs/Web/CSS/:selected
 * 
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class SelectedPseudoClass implements PseudoClass {

	public static final String SELECTED_PSEUDO_CONDITION = "local-name() = 'option' and (@selected or (ancestor::select[not(@multiple) and not(option[@selected])] and position() = 1))";
	private static final String SELECTED_PSEUDO_CONDITIONAL_EXPRESSION = "[" + SELECTED_PSEUDO_CONDITION + "]";

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
		return isOptionTag(element) && element.isSelected();
	}
	
	@Override
	public XPathComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		return XPathComponentFactory.createNoFilter(SELECTED_PSEUDO_CONDITIONAL_EXPRESSION);
	}

}