package io.github.seleniumquery.selectors.combinators;

import io.github.seleniumquery.by.selector.SelectorUtils;
import io.github.seleniumquery.by.selector.xpath.CssSelectorType;
import io.github.seleniumquery.by.selector.xpath.XPathExpression;
import io.github.seleniumquery.by.selector.xpath.XPathSelectorCompilerService;
import io.github.seleniumquery.by.selector.xpath.XPathExpressionFactory;
import io.github.seleniumquery.by.css.CssSelector;
import io.github.seleniumquery.by.css.CssSelectorMatcherService;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.SiblingSelector;

/**
 * E + F
 *
 * @author acdcjunior
 * @since 1.0.0
 */
public class DirectAdjacentCssSelector implements CssSelector<SiblingSelector> {

	@Override
	public boolean is(WebDriver driver, WebElement element, Map<String, String> stringMap, SiblingSelector siblingSelector) {
		WebElement previousElement = SelectorUtils.getPreviousSibling(element);
		return CssSelectorMatcherService.elementMatchesSelector(driver, previousElement, stringMap, siblingSelector.getSelector())
				&& CssSelectorMatcherService.elementMatchesSelector(driver, element, stringMap, siblingSelector.getSiblingSelector());
	}

	@Override
	public XPathExpression toXPath(Map<String, String> stringMap, SiblingSelector siblingSelector) {
		XPathExpression previousElementCompiled = XPathSelectorCompilerService.compileSelector(stringMap, siblingSelector.getSelector());
		XPathExpression siblingElementCompiled = XPathSelectorCompilerService.compileSelector(stringMap, siblingSelector.getSiblingSelector());
		
		XPathExpression positionOne = XPathExpressionFactory.createNoFilterSelector("[position() = 1]");
		siblingElementCompiled.combine(positionOne).kind = CssSelectorType.ADJACENT;
		
		return previousElementCompiled.combine(siblingElementCompiled);
	}

}