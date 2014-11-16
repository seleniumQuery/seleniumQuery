package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.by.selector.filter.ElementFilter;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

class PseudoClassFilter implements ElementFilter {
	
	public static final Map<String, String> STRING_MAP_NOT_USED = null;
	public static final Selector SELECTOR_NOT_USED = null;
	public static final String PSEUDO_CLASS_VALUE_NOT_USED = null;
	
	private PseudoClass pseudoClass;
	
	private PseudoClassSelector pseudoClassSelector;
	
	public PseudoClassFilter(PseudoClass pseudoClass) {
		this.pseudoClass = pseudoClass;
	}
	
	public PseudoClassFilter(PseudoClass pseudoClass, PseudoClassSelector pseudoClassSelector) {
		this.pseudoClass = pseudoClass;
		this.pseudoClassSelector = pseudoClassSelector;
	}
	
	@Override
	public List<WebElement> filterElements(WebDriver driver, List<WebElement> elements) {
		for (Iterator<WebElement> iterator = elements.iterator(); iterator.hasNext();) {
			WebElement webElement = iterator.next();
			if (!pseudoClass.isPseudoClass(driver, webElement, this.pseudoClassSelector)) {
				iterator.remove();
			}
		}
		return elements;
	}

}