package io.github.seleniumquery.selectors.tagname;

import java.util.Map;

import io.github.seleniumquery.selector.CompiledCssSelector;
import io.github.seleniumquery.selector.CssSelector;

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

}