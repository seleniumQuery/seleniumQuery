package io.github.seleniumquery.by.evaluator.combinators;

import io.github.seleniumquery.by.evaluator.CSSSelector;
import io.github.seleniumquery.by.evaluator.SelectorEvaluator;
import io.github.seleniumquery.by.evaluator.SelectorUtils;
import io.github.seleniumquery.by.selector.CSSFilter;
import io.github.seleniumquery.by.selector.CompiledSelector;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.SiblingSelector;

public class DirectAdjacentEvaluator implements CSSSelector<SiblingSelector> {

	private static final DirectAdjacentEvaluator instance = new DirectAdjacentEvaluator();
	public static DirectAdjacentEvaluator getInstance() {
		return instance;
	}
	private DirectAdjacentEvaluator() { }

	@Override
	public boolean is(WebDriver driver, WebElement element, SiblingSelector siblingSelector) {
		WebElement previousElement = SelectorUtils.getPreviousSibling(element);
		return SelectorEvaluator.is(driver, previousElement, siblingSelector.getSelector())
				&& SelectorEvaluator.is(driver, element, siblingSelector.getSiblingSelector());
	}

	@Override
	public CompiledSelector compile(WebDriver driver, SiblingSelector selector) {
		return new CompiledSelector(selector.toString(), CSSFilter.FILTER_NOTHING);
	}

}