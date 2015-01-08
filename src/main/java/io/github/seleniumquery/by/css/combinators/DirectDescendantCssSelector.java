package io.github.seleniumquery.by.css.combinators;

import io.github.seleniumquery.by.SelectorUtils;
import io.github.seleniumquery.by.css.CssSelector;
import io.github.seleniumquery.by.css.CssSelectorMatcherService;
import io.github.seleniumquery.by.xpath.XPathSelectorCompilerService;
import io.github.seleniumquery.by.xpath.component.DescendantDirectComponent;
import io.github.seleniumquery.by.xpath.component.TagComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.DescendantSelector;

import java.util.Map;

/**
 * PARENT > ELEMENT
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class DirectDescendantCssSelector implements CssSelector<DescendantSelector, TagComponent> {
	
	@Override
	public boolean is(WebDriver driver, WebElement element, Map<String, String> stringMap, DescendantSelector descendantSelector) {
		WebElement parent = SelectorUtils.parent(element);
		//noinspection SimplifiableIfStatement
		if (parent.getTagName().equals("html")) {
			return false;
		}
		return CssSelectorMatcherService.elementMatchesSelector(driver, element, stringMap, descendantSelector.getSimpleSelector())
				&& CssSelectorMatcherService.elementMatchesSelector(driver, parent, stringMap, descendantSelector.getAncestorSelector());
	}
	
	@Override
	public TagComponent toXPath(Map<String, String> stringMap, DescendantSelector descendantSelector) {
		TagComponent elementCompiledSelector = XPathSelectorCompilerService.compileSelector(stringMap, descendantSelector.getSimpleSelector());
		TagComponent parentCompiledSelector = XPathSelectorCompilerService.compileSelector(stringMap, descendantSelector.getAncestorSelector());

		return DescendantDirectComponent.combine(parentCompiledSelector, elementCompiledSelector);
	}

}