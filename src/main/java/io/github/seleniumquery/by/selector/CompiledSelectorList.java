package io.github.seleniumquery.by.selector;

import java.util.List;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

public class CompiledSelectorList {
	
	public List<CompiledSelector> css;
	
	CompiledSelectorList(List<CompiledSelector> css) {
		this.css = css;
	}
	
	public List<WebElement> execute(SearchContext context) {
		return null;
	}

}