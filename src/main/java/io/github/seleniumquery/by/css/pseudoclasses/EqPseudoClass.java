package io.github.seleniumquery.by.css.pseudoclasses;

import io.github.seleniumquery.by.xpath.component.XPathComponent;
import io.github.seleniumquery.by.xpath.XPathSelectorCompilerService;
import io.github.seleniumquery.by.xpath.component.XPathComponentFactory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * :eq()
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class EqPseudoClass implements PseudoClass {

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
		XPathComponent compiledSelector = XPathSelectorCompilerService.compileSelector(pseudoClassSelector.getStringMap(), pseudoClassSelector.getSelector());
		List<WebElement> elements = compiledSelector.findWebElements(driver);
		if (index < 0) {
			return elements.size() >= -index && elements.get(elements.size() + index).equals(element);
		}
		return elements.size() > index && elements.get(index).equals(element);
	}

	@Override
	public XPathComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		String eqIndex = pseudoClassSelector.getPseudoClassContent();
		if (!eqIndex.matches("[+-]?\\d+")) {
			throw new IllegalArgumentException("The :eq() pseudo-class requires an integer but got: " + eqIndex);
		}
		if (eqIndex.charAt(0) == '+') {
			eqIndex = eqIndex.substring(1);
		}
		int index = Integer.valueOf(eqIndex);
		
		if (index >= 0) {
			return XPathComponentFactory.createNoFilterAppliedToAll("[position() = " + (index + 1) + "]");
		}
		return XPathComponentFactory.createNoFilterAppliedToAll("[position() = (last()-" + (-index - 1) + ")]");
	}

}