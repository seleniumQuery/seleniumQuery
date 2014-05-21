package io.github.seleniumquery.selector;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

public class CompiledCssSelectorList {
	
	List<CompiledCssSelector> css;
	
	CompiledCssSelectorList(List<CompiledCssSelector> css) {
		this.css = css;
	}
	
	public List<WebElement> execute(SearchContext context) {
		Set<WebElement> elements = new LinkedHashSet<WebElement>();
		for (CompiledCssSelector cs : css) {
			List<WebElement> execute = cs.execute(context);
			elements.addAll(execute);
		}
		return new ArrayList<WebElement>(elements);
	}

}