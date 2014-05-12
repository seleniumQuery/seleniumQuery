package io.github.seleniumquery.by.evaluator.tagname;

import io.github.seleniumquery.by.evaluator.CSSSelector;
import io.github.seleniumquery.by.selector.CompiledSelector;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.ElementSelector;

public class TagNameEvaluator implements CSSSelector<ElementSelector> {

	private static final TagNameEvaluator instance = new TagNameEvaluator();

	public static TagNameEvaluator getInstance() {
		return instance;
	}

	@Override
	public boolean is(WebDriver driver, WebElement element, ElementSelector elementSelector) {
		String name = elementSelector.getLocalName();
		return name == null || name.equalsIgnoreCase(element.getTagName());
	}

	@Override
	public CompiledSelector compile(WebDriver driver, ElementSelector selector) {
		// nothing to do, everyone supports filtering by tag name
		return CompiledSelector.createNoFilterSelector(selector);
	}

}