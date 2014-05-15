package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import static io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.PseudoClassFilter.PSEUDO_CLASS_VALUE_NOT_USED;
import static io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.PseudoClassFilter.SELECTOR_NOT_USED;

import java.util.Arrays;
import java.util.List;

import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SqCSSFilter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

/**
 * http://api.jquery.com/header-selector/
 * 
 * @since 1.0.0
 * @author acdcjunior
 */
public class HeaderPseudoClass implements PseudoClass {
	
	private static final HeaderPseudoClass instance = new HeaderPseudoClass();
	public static HeaderPseudoClass getInstance() {
		return instance;
	}
	private HeaderPseudoClass() { }
	
	private static final List<String> HEADER_TAGS = Arrays.asList("h0", "h1", "h2", "h3", "h4", "h5", "h6", "h7", "h8", "h9");
	
	private static final String HEADER = "header";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return HEADER.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		return HEADER_TAGS.contains(element.getTagName());
	}
	
	private static final SqCSSFilter headerPseudoClassFilter = new PseudoClassFilter(getInstance(),
																		SELECTOR_NOT_USED, PSEUDO_CLASS_VALUE_NOT_USED);
	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		// :header is an extension selector, nobody implements it natively
		return CompiledSelector.createFilterOnlySelector(headerPseudoClassFilter);
	}
	
}