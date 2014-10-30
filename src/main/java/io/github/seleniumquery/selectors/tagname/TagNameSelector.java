package io.github.seleniumquery.selectors.tagname;

import java.util.Map;

import io.github.seleniumquery.selectorcss.CompiledCssSelector;
import io.github.seleniumquery.selectorcss.CssSelector;
import io.github.seleniumquery.selectorxpath.SqSelectorKind;
import io.github.seleniumquery.selectorxpath.XPathExpression;
import io.github.seleniumquery.selectorxpath.XPathSelectorFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.ElementSelector;

public class TagNameSelector implements CssSelector<ElementSelector> {

	private static final TagNameSelector instance = new TagNameSelector();

	public static TagNameSelector getInstance() {
		return instance;
	}

	@Override
	public boolean is(WebDriver driver, WebElement element, Map<String, String> stringMap, ElementSelector elementSelector) {
		String name = elementSelector.getLocalName();
		return name == null || name.equalsIgnoreCase(element.getTagName());
	}

	@Override
	public CompiledCssSelector compile(WebDriver driver, Map<String, String> stringMap, ElementSelector selector) {
		// nothing to do, everyone supports filtering by tag name
		return CompiledCssSelector.createNoFilterSelector(selector);
	}

	@Override
	public XPathExpression toXPath(WebDriver driver, Map<String, String> stringMap, ElementSelector selector) {
		String tagName = selector.toString();
		XPathExpression tagSelector = XPathSelectorFactory.createNoFilterSelector(tagName);
		tagSelector.kind = SqSelectorKind.TAG;
		return tagSelector;
//		if ("*".equals(tagName)) {
//			return XPathSelectorFactory.createNoFilterSelector("");
//		}
//		return XPathSelectorFactory.createNoFilterSelector("[local-name() = '"+selector+"']");
	}

}