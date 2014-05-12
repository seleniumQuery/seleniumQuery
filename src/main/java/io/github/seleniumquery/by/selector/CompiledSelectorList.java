package io.github.seleniumquery.by.selector;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

public class CompiledSelectorList {
	
	public List<CompiledSelector> css;
	
	CompiledSelectorList(List<CompiledSelector> css) {
		this.css = css;
	}
	
	public List<WebElement> execute(SearchContext context) {
		List<List<WebElement>> elements = new ArrayList<List<WebElement>>(css.size());
		List<WebElement> execute = null;
		for (CompiledSelector cs : css) {
			execute = cs.execute(context);
			elements.add(execute);
		}
		// you have to implement the intersection here!
		System.err.println("NOT IMPLEMENTED!!!");
		return execute;
	}

}