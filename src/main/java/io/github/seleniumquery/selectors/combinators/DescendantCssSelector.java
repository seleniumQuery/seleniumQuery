package io.github.seleniumquery.selectors.combinators;

import io.github.seleniumquery.selector.SelectorUtils;
import io.github.seleniumquery.selectorcss.CssSelector;
import io.github.seleniumquery.selectorcss.CssSelectorMatcherService;
import io.github.seleniumquery.selectorxpath.SqSelectorKind;
import io.github.seleniumquery.selectorxpath.XPathExpression;
import io.github.seleniumquery.selectorxpath.XPathSelectorCompilerService;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.DescendantSelector;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SimpleSelector;

public class DescendantCssSelector implements CssSelector<DescendantSelector> {

	private static final DescendantCssSelector instance = new DescendantCssSelector();
	public static DescendantCssSelector getInstance() {
		return instance;
	}
	private DescendantCssSelector() { }
	
	/**
	 * E F
	 */
	@Override
	public boolean is(WebDriver driver, WebElement element, Map<String, String> stringMap, DescendantSelector descendantSelector) {
		if (CssSelectorMatcherService.elementMatchesSelector(driver, element, stringMap, descendantSelector.getSimpleSelector())) {
	
			WebElement ancestor = SelectorUtils.parent(element);
	
			while (ancestor != null) {
				if (CssSelectorMatcherService.elementMatchesSelector(driver, ancestor, stringMap, descendantSelector.getAncestorSelector())) {
					return true;
				}
				ancestor = SelectorUtils.parent(ancestor);
			}
		}
		return false;
	}

	@Override
	public XPathExpression toXPath(Map<String, String> stringMap, DescendantSelector descendantSelector) {
		Selector ancestorCSSSelector = descendantSelector.getAncestorSelector();
		XPathExpression ancestorCompiled = XPathSelectorCompilerService.compileSelector(stringMap, ancestorCSSSelector);
		
		SimpleSelector descendantCSSSelector = descendantSelector.getSimpleSelector();
		XPathExpression childrenCompiled = XPathSelectorCompilerService.compileSelector(stringMap, descendantCSSSelector);
		childrenCompiled.kind = SqSelectorKind.DESCENDANT_GENERAL;
		
		return ancestorCompiled.combine(childrenCompiled);
	}
	
}