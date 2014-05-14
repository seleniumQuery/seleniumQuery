package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import io.github.seleniumquery.by.selector.SqCSSFilter;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

class PseudoClassFilter implements SqCSSFilter {
	
	public static final Selector SELECTOR_NOT_USED = null;
	public static final String PSEUDO_CLASS_VALUE_NOT_USED = null;
	
	private PseudoClass pseudoClass;
	private Selector selectorThisConditionShouldApply;
	private String pseudoClassValue;
	
	public PseudoClassFilter(PseudoClass pseudoClass, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		this.pseudoClass = pseudoClass;
		this.selectorThisConditionShouldApply = selectorThisConditionShouldApply;
		this.pseudoClassValue = pseudoClassValue;
	}
	
	@Override
	public List<WebElement> filter(WebDriver driver, List<WebElement> elements) {
		for (Iterator<WebElement> iterator = elements.iterator(); iterator.hasNext();) {
			WebElement webElement = iterator.next();
			if (!pseudoClass.isPseudoClass(driver, webElement, selectorThisConditionShouldApply, pseudoClassValue)) {
				iterator.remove();
			}
		}
		return elements;
	}

}