package io.github.seleniumquery.by.css.pseudoclasses;

import io.github.seleniumquery.by.xpath.XPathSelectorCompilerService;
import io.github.seleniumquery.by.xpath.component.ConditionalAppliedToAllComponent;
import io.github.seleniumquery.by.xpath.component.TagComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * http://api.jquery.com/odd-selector/
 * 
 * @author acdcjunior
 * @since 0.9.0
 */
public class OddPseudoClass implements PseudoClass<ConditionalAppliedToAllComponent> {

	private static final String ODD_PSEUDO_CLASS_NO_COLON = "odd";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return ODD_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}

	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		TagComponent compiledSelector = XPathSelectorCompilerService.compileSelector(pseudoClassSelector.getStringMap(), pseudoClassSelector.getSelector());
		List<WebElement> elements = compiledSelector.findWebElements(driver);
		return elements.indexOf(element) % 2 == 1;
	}
	
	@Override
	public ConditionalAppliedToAllComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		// notice that XPath is 1-based and :odd is not.
		return new ConditionalAppliedToAllComponent("[(position() mod 2) = 0]");
	}

}