package io.github.seleniumquery.by.css.tagname;

import io.github.seleniumquery.by.css.CssSelector;
import io.github.seleniumquery.by.xpath.component.TagComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.ElementSelector;

import java.util.Map;

/**
 * $("tagname")
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class TagNameSelector implements CssSelector<ElementSelector, TagComponent> {

	@Override
	public boolean is(WebDriver driver, WebElement element, Map<String, String> stringMap, ElementSelector elementSelector) {
		String name = elementSelector.getLocalName();
		return name == null || name.equalsIgnoreCase(element.getTagName());
	}

	@Override
	public TagComponent toXPath(Map<String, String> stringMap, ElementSelector selector) {
		String tagName = selector.toString();
		return new TagComponent(tagName);
	}

}