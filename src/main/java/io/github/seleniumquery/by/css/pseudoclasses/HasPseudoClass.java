package io.github.seleniumquery.by.css.pseudoclasses;

import io.github.seleniumquery.by.xpath.TagComponentList;
import io.github.seleniumquery.by.xpath.XPathSelectorCompilerService;
import io.github.seleniumquery.by.xpath.component.SimpleConditionalComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * :has(selector)
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class HasPseudoClass implements PseudoClass<SimpleConditionalComponent> {
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return pseudoClassValue.matches("has\\(.*\\)");
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		String hasSelector = pseudoClassSelector.getPseudoClassContent();
		
		TagComponentList compiledSelector = XPathSelectorCompilerService.compileSelectorList(hasSelector);
		List<WebElement> elements = compiledSelector.findWebElements(driver);
		
		return !elements.isEmpty();
	}
	
	@Override
	public SimpleConditionalComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		String notSelector = pseudoClassSelector.getPseudoClassContent();
		String insideHasXPath = XPathSelectorCompilerService.compileSelectorList(notSelector).toXPath();
		insideHasXPath = insideHasXPath.substring(1, insideHasXPath.length()-1);
		return new SimpleConditionalComponent("[" + insideHasXPath + "]");
	}
	
}