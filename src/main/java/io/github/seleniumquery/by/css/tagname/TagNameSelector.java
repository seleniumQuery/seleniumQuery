package io.github.seleniumquery.by.css.tagname;

import io.github.seleniumquery.by.xpath.CssSelectorType;
import io.github.seleniumquery.by.xpath.XPathComponent;
import io.github.seleniumquery.by.xpath.XPathComponentFactory;
import io.github.seleniumquery.by.css.CssSelector;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.ElementSelector;

/**
 * $("tagname")
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class TagNameSelector implements CssSelector<ElementSelector> {

	@Override
	public boolean is(WebDriver driver, WebElement element, Map<String, String> stringMap, ElementSelector elementSelector) {
		String name = elementSelector.getLocalName();
		return name == null || name.equalsIgnoreCase(element.getTagName());
	}

	@Override
	public XPathComponent toXPath(Map<String, String> stringMap, ElementSelector selector) {
		String tagName = selector.toString();
		return XPathComponentFactory.createNoFilter(tagName, CssSelectorType.TAG);
	}

}