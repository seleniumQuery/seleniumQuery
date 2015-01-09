package io.github.seleniumquery.by.css.combinators;

import io.github.seleniumquery.by.SelectorUtils;
import io.github.seleniumquery.by.css.CssSelector;
import io.github.seleniumquery.by.css.CssSelectorMatcherService;
import io.github.seleniumquery.by.xpath.XPathSelectorCompilerService;
import io.github.seleniumquery.by.xpath.component.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.SiblingSelector;

import java.util.Map;

/**
 * E + F
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class DirectAdjacentCssSelector implements CssSelector<SiblingSelector, TagComponent> {

	@Override
	public boolean is(WebDriver driver, WebElement element, Map<String, String> stringMap, SiblingSelector siblingSelector) {
		WebElement previousElement = SelectorUtils.getPreviousSibling(element);
		return CssSelectorMatcherService.elementMatchesSelector(driver, previousElement, stringMap, siblingSelector.getSelector())
				&& CssSelectorMatcherService.elementMatchesSelector(driver, element, stringMap, siblingSelector.getSiblingSelector());
	}

	@Override
	public TagComponent toXPath(Map<String, String> stringMap, SiblingSelector siblingSelector) {
		TagComponent previousCompiledExpression = XPathSelectorCompilerService.compileSelector(stringMap, siblingSelector.getSelector());
		TagComponent siblingSelectorCompiledAdjacentExpression = XPathSelectorCompilerService.compileSelector(stringMap, siblingSelector.getSiblingSelector());

		SimpleConditionalComponent positionOne = new SimpleConditionalComponent("[position() = 1]");
		TagComponent siblingAtPositionOne = siblingSelectorCompiledAdjacentExpression.cloneAndCombineTo(positionOne);
		
		return AdjacentComponent.combine(previousCompiledExpression, siblingAtPositionOne);
	}

}