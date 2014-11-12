package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.selector.xpath.XPathExpression;
import io.github.seleniumquery.selector.xpath.XPathSelectorCompilerService;
import io.github.seleniumquery.selector.xpath.XPathExpressionFactory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * :gt(#)
 *
 * @author acdcjunior
 * @since 1.0.0
 */
public class GtPseudoClass implements PseudoClass {

	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return pseudoClassValue.matches("gt\\(.*\\)");
	}

	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		String gtIndex = pseudoClassSelector.getPseudoClassContent();
		if (!gtIndex.matches("[+-]?\\d+")) {
			throw new RuntimeException("The :gt() pseudo-class requires an integer but got: " + gtIndex);
		}
		if (gtIndex.charAt(0) == '+') {
			gtIndex = gtIndex.substring(1);
		}
		int index = Integer.valueOf(gtIndex);
		
		return GtPseudoClass.isGt(driver, element, pseudoClassSelector, index);
	}
	
	private static boolean isGt(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector, int wantedIndex) {
		XPathExpression compiledSelector = XPathSelectorCompilerService.compileSelector(pseudoClassSelector.getStringMap(), pseudoClassSelector.getSelector());
		List<WebElement> elements = compiledSelector.findWebElements(driver);
		if (elements.isEmpty()) {
			return false;
		}
		int actuallyWantedIndex = wantedIndex;
		if (wantedIndex < 0) {
			actuallyWantedIndex = elements.size() + wantedIndex;
		}
		
		if (elements.size() <= actuallyWantedIndex) {
			return false;
		}
		int indexFound = elements.indexOf(element);
		if (indexFound == -1) {
			return false;
		}
		return indexFound > actuallyWantedIndex;
	}
	
	@Override
	public XPathExpression pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		String eqIndex = pseudoClassSelector.getPseudoClassContent();
		if (!eqIndex.matches("[+-]?\\d+")) {
			throw new RuntimeException("The :gt() pseudo-class requires an integer but got: " + eqIndex);
		}
		if (eqIndex.charAt(0) == '+') {
			eqIndex = eqIndex.substring(1);
		}
		int index = Integer.valueOf(eqIndex);
		
		if (index >= 0) {
			return XPathExpressionFactory.createNoFilterSelectorAppliedToAll("[position() > " + (index + 1) + "]");
		}
		return XPathExpressionFactory.createNoFilterSelectorAppliedToAll("[position() > (last()-" + (-index - 1) + ")]");
	}

}