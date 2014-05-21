package io.github.seleniumquery.by.selector;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

public class CompiledSelectorList {
	
	List<CompiledSelector> css;
	
	CompiledSelectorList(List<CompiledSelector> css) {
		this.css = css;
	}
	
	public List<WebElement> execute(SearchContext context) {
		Set<WebElement> elements = new LinkedHashSet<WebElement>();
		for (CompiledSelector cs : css) {
			List<WebElement> execute = cs.execute(context);
			elements.addAll(execute);
		}
		return new ArrayList<WebElement>(elements);
	}

}