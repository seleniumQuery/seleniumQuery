package io.github.seleniumquery.by.css.pseudoclasses;

import io.github.seleniumquery.by.css.CssSelectorMatcherService;
import io.github.seleniumquery.by.xpath.XPathSelectorCompilerService;
import io.github.seleniumquery.by.xpath.component.SimpleConditionalComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * https://developer.mozilla.org/en-US/docs/Web/CSS/:not
 * 
 * @author acdcjunior
 * @since 0.9.0
 */
public class NotPseudoClass implements PseudoClass<SimpleConditionalComponent> {
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return pseudoClassValue.matches("not-sq\\(.*\\)");
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		String notSelector = pseudoClassSelector.getPseudoClassContent();
		return !CssSelectorMatcherService.elementMatchesStringSelector(driver, element, notSelector);
	}
	
	@Override
	public SimpleConditionalComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		String notSelector = pseudoClassSelector.getPseudoClassContent();
		String insideNotXPath = XPathSelectorCompilerService.compileSelectorList(notSelector).toXPathCondition();
		return new SimpleConditionalComponent("[not(" + insideNotXPath + ")]");
	}
	
}