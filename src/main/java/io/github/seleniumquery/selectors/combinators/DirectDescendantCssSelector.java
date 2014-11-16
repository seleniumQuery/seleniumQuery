package io.github.seleniumquery.selectors.combinators;

import io.github.seleniumquery.selector.SelectorUtils;
import io.github.seleniumquery.selector.xpath.XPathExpression;
import io.github.seleniumquery.selector.xpath.XPathSelectorCompilerService;
import io.github.seleniumquery.selector.css.CssSelector;
import io.github.seleniumquery.selector.css.CssSelectorMatcherService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.DescendantSelector;

import java.util.Map;

/**
 * PARENT > ELEMENT
 *
 * @author acdcjunior
 * @since 1.0.0
 */
public class DirectDescendantCssSelector implements CssSelector<DescendantSelector> {
	
	@Override
	public boolean is(WebDriver driver, WebElement element, Map<String, String> stringMap, DescendantSelector descendantSelector) {
		WebElement parent = SelectorUtils.parent(element);
		if (parent.getTagName().equals("html")) {
			return false;
		}
		return CssSelectorMatcherService.elementMatchesSelector(driver, element, stringMap, descendantSelector.getSimpleSelector())
				&& CssSelectorMatcherService.elementMatchesSelector(driver, parent, stringMap, descendantSelector.getAncestorSelector());
	}
	
	@Override
	public XPathExpression toXPath(Map<String, String> stringMap, DescendantSelector descendantSelector) {
		XPathExpression elementCompiledSelector = XPathSelectorCompilerService.compileToDescendantDirectExpression(stringMap, descendantSelector.getSimpleSelector());
		XPathExpression parentCompiledSelector = XPathSelectorCompilerService.compileSelector(stringMap, descendantSelector.getAncestorSelector());

		return parentCompiledSelector.combine(elementCompiledSelector);
	}

}