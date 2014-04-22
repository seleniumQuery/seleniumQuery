package io.github.seleniumquery.by.evaluator.combinators;

import io.github.seleniumquery.by.evaluator.CSSSelector;
import io.github.seleniumquery.by.evaluator.SelectorEvaluator;
import io.github.seleniumquery.by.evaluator.SelectorUtils;
import io.github.seleniumquery.by.selector.CSSFilter;
import io.github.seleniumquery.by.selector.CompiledSelector;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.DescendantSelector;

public class ChildSelectorEvaluator implements CSSSelector<DescendantSelector> {
	
	private static final ChildSelectorEvaluator instance = new ChildSelectorEvaluator();
	public static ChildSelectorEvaluator getInstance() {
		return instance;
	}
	private ChildSelectorEvaluator() { }
	
	/**
	 * PARENT > ELEMENT
	 */
	@Override
	public boolean is(WebDriver driver, WebElement element, DescendantSelector descendantSelector) {
		WebElement parent = SelectorUtils.parent(element);
		if (parent.getTagName().equals("html")) {
			return false;
		}
		return SelectorEvaluator.is(driver, element, descendantSelector.getSimpleSelector())
				&& SelectorEvaluator.is(driver, parent, descendantSelector.getAncestorSelector());
	}
	@Override
	public CompiledSelector compile(WebDriver driver, DescendantSelector selector) {
		return new CompiledSelector(selector.toString(), CSSFilter.FILTER_NOTHING);
	}

}