package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SeleniumQueryCssCompiler;
import io.github.seleniumquery.by.selector.SqCSSFilter;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GtPseudoClass implements PseudoClass {

	private static final GtPseudoClass instance = new GtPseudoClass();
	public static GtPseudoClass getInstance() {
		return instance;
	}
	private GtPseudoClass() { }

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
		CompiledSelector compileSelector = SeleniumQueryCssCompiler.compileSelector(driver, pseudoClassSelector.getStringMap(), pseudoClassSelector.getSelector());
		List<WebElement> elements = compileSelector.execute(driver);
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
	public CompiledSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// no browser supports :gt() natively
		SqCSSFilter gtPseudoClassFilter = new PseudoClassFilter(getInstance(), pseudoClassSelector);
		return CompiledSelector.createFilterOnlySelector(gtPseudoClassFilter);
	}

}