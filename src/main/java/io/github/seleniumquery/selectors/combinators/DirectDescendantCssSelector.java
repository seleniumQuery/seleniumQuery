package io.github.seleniumquery.selectors.combinators;

import io.github.seleniumquery.selector.SelectorUtils;
import io.github.seleniumquery.selector.xpath.SqSelectorKind;
import io.github.seleniumquery.selector.xpath.XPathExpression;
import io.github.seleniumquery.selector.xpath.XPathSelectorCompilerService;
import io.github.seleniumquery.selectorcss.CssSelector;
import io.github.seleniumquery.selectorcss.CssSelectorMatcherService;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.DescendantSelector;

public class DirectDescendantCssSelector implements CssSelector<DescendantSelector> {
	
	private static final DirectDescendantCssSelector instance = new DirectDescendantCssSelector();
	public static DirectDescendantCssSelector getInstance() {
		return instance;
	}
	private DirectDescendantCssSelector() { }
	
	/**
	 * PARENT > ELEMENT
	 */
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
		XPathExpression elementCompiledSelector = XPathSelectorCompilerService.compileSelector(stringMap, descendantSelector.getSimpleSelector());
		XPathExpression parentCompiledSelector = XPathSelectorCompilerService.compileSelector(stringMap, descendantSelector.getAncestorSelector());
		
		elementCompiledSelector.kind = SqSelectorKind.DESCENDANT_DIRECT;
		return parentCompiledSelector.combine(elementCompiledSelector);
	}

}