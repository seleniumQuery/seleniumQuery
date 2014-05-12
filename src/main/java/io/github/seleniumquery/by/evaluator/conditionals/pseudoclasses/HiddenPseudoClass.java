package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SqCSSFilter;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

public class HiddenPseudoClass implements PseudoClass {
	
	private static final HiddenPseudoClass instance = new HiddenPseudoClass();
	public static HiddenPseudoClass getInstance() {
		return instance;
	}
	private HiddenPseudoClass() { }
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return "hidden".equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		return !element.isDisplayed();
	}
	
	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		return CompiledSelector.createFilterOnlySelector(HiddenPseudoClassFilter);
	}
	
	public static final SqCSSFilter HiddenPseudoClassFilter = new SqCSSFilter() {
		@Override
		public List<WebElement> filter(List<WebElement> elements) {
			for (Iterator<WebElement> iterator = elements.iterator(); iterator.hasNext();) {
				WebElement webElement = iterator.next();
				if (webElement.isDisplayed()) {
					iterator.remove();
				}
			}
			return elements;
		}
	};
	
}