package io.github.seleniumquery.selectors.tagname;

import io.github.seleniumquery.selector.xpath.CssSelectorType;
import io.github.seleniumquery.selector.xpath.XPathExpression;
import io.github.seleniumquery.selector.xpath.XPathExpressionFactory;
import io.github.seleniumquery.selectorcss.CssSelector;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.ElementSelector;

/**
 * $("tagname")
 *
 * @author acdcjunior
 * @since 1.0.0
 */
public class TagNameSelector implements CssSelector<ElementSelector> {

	@Override
	public boolean is(WebDriver driver, WebElement element, Map<String, String> stringMap, ElementSelector elementSelector) {
		String name = elementSelector.getLocalName();
		return name == null || name.equalsIgnoreCase(element.getTagName());
	}

	@Override
	public XPathExpression toXPath(Map<String, String> stringMap, ElementSelector selector) {
		String tagName = selector.toString();
		XPathExpression tagSelector = XPathExpressionFactory.createNoFilterSelector(tagName);
		tagSelector.kind = CssSelectorType.TAG;
		return tagSelector;
	}

}