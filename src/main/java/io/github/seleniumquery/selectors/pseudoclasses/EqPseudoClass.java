package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.locator.ElementFilter;
import io.github.seleniumquery.selectorcss.CompiledCssSelector;
import io.github.seleniumquery.selectorcss.CssSelectorCompilerService;
import io.github.seleniumquery.selectorxpath.SqXPathSelector;
import io.github.seleniumquery.selectorxpath.XPathSelectorFactory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EqPseudoClass implements PseudoClass {

	private static final EqPseudoClass instance = new EqPseudoClass();
	public static EqPseudoClass getInstance() {
		return instance;
	}
	private EqPseudoClass() { }

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
		CompiledCssSelector compileSelector = CssSelectorCompilerService.compileSelector(driver, pseudoClassSelector.getStringMap(), pseudoClassSelector.getSelector());
		List<WebElement> elements = compileSelector.execute(driver);
		if (index < 0) {
			return elements.size() >= -index && elements.get(elements.size() + index).equals(element);
		}
		return elements.size() > index && elements.get(index).equals(element);
	}

	@Override
	public CompiledCssSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		ElementFilter eqPseudoClassFilter = new PseudoClassFilter(getInstance(), pseudoClassSelector);
		return CompiledCssSelector.createFilterOnlySelector(eqPseudoClassFilter);
	}
	
	@Override
	public SqXPathSelector pseudoClassToXPath(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		String eqIndex = pseudoClassSelector.getPseudoClassContent();
		if (!eqIndex.matches("[+-]?\\d+")) {
			throw new RuntimeException("The :eq() pseudo-class requires an integer but got: " + eqIndex);
		}
		if (eqIndex.charAt(0) == '+') {
			eqIndex = eqIndex.substring(1);
		}
		int index = Integer.valueOf(eqIndex);
		
		if (index >= 0) {
			return XPathSelectorFactory.createNoFilterSelectorAppliedToAll("[position() = "+(index+1)+"]");
		}
		return XPathSelectorFactory.createNoFilterSelectorAppliedToAll("[position() = (last()-"+ (-index-1) +")]");
	}

}