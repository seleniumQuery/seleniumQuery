package io.github.seleniumquery.by.css.pseudoclasses;

import io.github.seleniumquery.by.xpath.XPathSelectorCompilerService;
import io.github.seleniumquery.by.xpath.component.ConditionalAppliedToAllComponent;
import io.github.seleniumquery.by.xpath.component.TagComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * :eq()
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class EqPseudoClass implements PseudoClass<ConditionalAppliedToAllComponent> {

	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return pseudoClassValue.matches("eq\\(.*\\)");
	}

	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		String eqIndex = pseudoClassSelector.getPseudoClassContent();
		if (!eqIndex.matches("[+-]?\\d+")) {
			throw new RuntimeException("The :eq() pseudo-class requires an integer but got: " + eqIndex);
		}
		if (eqIndex.charAt(0) == '+') {
			eqIndex = eqIndex.substring(1);
		}
		int index = Integer.valueOf(eqIndex);
		
		return EqPseudoClass.isEq(driver, element, pseudoClassSelector, index);
	}
	
	static boolean isEq(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector, int index) {
		TagComponent compiledSelector = XPathSelectorCompilerService.compileSelector(pseudoClassSelector.getStringMap(), pseudoClassSelector.getSelector());
		List<WebElement> elements = compiledSelector.findWebElements(driver);
		if (index < 0) {
			return elements.size() >= -index && elements.get(elements.size() + index).equals(element);
		}
		return elements.size() > index && elements.get(index).equals(element);
	}

	@Override
	public ConditionalAppliedToAllComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		String eqIndex = pseudoClassSelector.getPseudoClassContent();
		if (!eqIndex.matches("[+-]?\\d+")) {
			throw new IllegalArgumentException("The :eq() pseudo-class requires an integer but got: " + eqIndex);
		}
		if (eqIndex.charAt(0) == '+') {
			eqIndex = eqIndex.substring(1);
		}
		int index = Integer.valueOf(eqIndex);
		
		if (index >= 0) {
			return new ConditionalAppliedToAllComponent("[position() = " + (index + 1) + "]");
		}
		String xPathExpression = "[position() = (last()-" + (-index - 1) + ")]";
		return new ConditionalAppliedToAllComponent(xPathExpression);
	}

}