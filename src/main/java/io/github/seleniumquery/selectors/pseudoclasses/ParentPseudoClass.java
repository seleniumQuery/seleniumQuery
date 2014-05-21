package io.github.seleniumquery.selectors.pseudoclasses;

import java.util.List;

import io.github.seleniumquery.selector.CompiledCssSelector;
import io.github.seleniumquery.selector.CssFilter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ParentPseudoClass implements PseudoClass {
	
	private static final ParentPseudoClass instance = new ParentPseudoClass();
	public static ParentPseudoClass getInstance() {
		return instance;
	}
	private ParentPseudoClass() { }
	
	private static final String PARENT_PSEUDO_CLASS_NO_COLON = "parent";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return PARENT_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		System.out.println("#"+element.getAttribute("id"));
		List<WebElement> findElements = element.findElements(By.xpath("./*"));
		if (findElements.isEmpty()) {
			System.out.println("\nHAS NO CHILDREN. Text is: '"+element.getText()+"'");
			System.out.println("Empty? "+element.getText().isEmpty());
			return !element.getText().isEmpty();
		}
		System.out.println("HAS CHILDREN!");
		// has children
		return true;
	}
	
	private static final CssFilter parentPseudoClassFilter = new PseudoClassFilter(getInstance());
	@Override
	public CompiledCssSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// we never consider :parent to be supported natively
		return CompiledCssSelector.createFilterOnlySelector(parentPseudoClassFilter);
	}
	
}