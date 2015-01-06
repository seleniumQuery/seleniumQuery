package io.github.seleniumquery.by.css.pseudoclasses;

import io.github.seleniumquery.by.xpath.XPathComponent;
import io.github.seleniumquery.by.xpath.XPathSelectorCompilerService;
import io.github.seleniumquery.by.xpath.XPathComponentFactory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * :lt(#)
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class LtPseudoClass implements PseudoClass {

	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return pseudoClassValue.matches("lt\\(.*\\)");
	}

	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		String ltIndex = pseudoClassSelector.getPseudoClassContent();
		if (!ltIndex.matches("[+-]?\\d+")) {
			throw new RuntimeException("The :lt() pseudo-class requires an integer but got: " + ltIndex);
		}
		if (ltIndex.charAt(0) == '+') {
			ltIndex = ltIndex.substring(1);
		}
		int index = Integer.valueOf(ltIndex);
		
		return LtPseudoClass.isLt(driver, element, pseudoClassSelector, index);
	}
	
	private static boolean isLt(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector, int wantedIndex) {
		if (wantedIndex == 0) {
			return false;
		}
		XPathComponent compiledSelector = XPathSelectorCompilerService.compileSelector(pseudoClassSelector.getStringMap(), pseudoClassSelector.getSelector());
		List<WebElement> elements = compiledSelector.findWebElements(driver);
		if (elements.isEmpty()) {
			return false;
		}
		int actuallyWantedIndex = wantedIndex;
		if (wantedIndex < 0) {
			actuallyWantedIndex = elements.size() + wantedIndex;
		}
		
		if (elements.size() <= actuallyWantedIndex) {
			return true;
		}
		int indexFound = elements.indexOf(element);
		//noinspection SimplifiableIfStatement
		if (indexFound == -1) {
			return false;
		}
		return indexFound < actuallyWantedIndex;
	}

	@Override
	public XPathComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		String eqIndex = pseudoClassSelector.getPseudoClassContent();
		if (!eqIndex.matches("[+-]?\\d+")) {
			throw new RuntimeException("The :lt() pseudo-class requires an integer but got: " + eqIndex);
		}
		if (eqIndex.charAt(0) == '+') {
			eqIndex = eqIndex.substring(1);
		}
		int index = Integer.valueOf(eqIndex);
		
		if (index >= 0) {
			return XPathComponentFactory.createNoFilterAppliedToAll("[position() < " + (index + 1) + "]");
		}
		return XPathComponentFactory.createNoFilterAppliedToAll("[position() < (last()-" + (-index - 1) + ")]");
	}

}