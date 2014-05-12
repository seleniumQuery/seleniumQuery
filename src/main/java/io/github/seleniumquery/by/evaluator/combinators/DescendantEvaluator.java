package io.github.seleniumquery.by.evaluator.combinators;

import io.github.seleniumquery.by.evaluator.CSSSelector;
import io.github.seleniumquery.by.evaluator.SelectorEvaluator;
import io.github.seleniumquery.by.evaluator.SelectorUtils;
import io.github.seleniumquery.by.selector.CompiledSelector;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.DescendantSelector;

public class DescendantEvaluator implements CSSSelector<DescendantSelector> {
	
	private static final DescendantEvaluator instance = new DescendantEvaluator();
	public static DescendantEvaluator getInstance() {
		return instance;
	}
	private DescendantEvaluator() { }
	
	/**
	 * E F
	 */
	@Override
	public boolean is(WebDriver driver, WebElement element, DescendantSelector descendantSelector) {
		if (SelectorEvaluator.is(driver, element, descendantSelector.getSimpleSelector())) {
	
			WebElement ancestor = SelectorUtils.parent(element);
	
			while (ancestor != null) {
				if (SelectorEvaluator.is(driver, ancestor, descendantSelector.getAncestorSelector())) {
					return true;
				}
				ancestor = SelectorUtils.parent(ancestor);
			}
		}
		return false;
	}

	@Override
	public CompiledSelector compile(WebDriver driver, DescendantSelector selector) {
		return CompiledSelector.createNoFilterSelector(selector);
	}

}