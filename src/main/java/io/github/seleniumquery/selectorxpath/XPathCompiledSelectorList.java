package io.github.seleniumquery.selectorxpath;

import io.github.seleniumquery.locator.Locator;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

public class XPathCompiledSelectorList implements Locator {
	
	List<XPathExpression> locators;
	
	XPathCompiledSelectorList(List<XPathExpression> css) {
		this.locators = css;
	}
	
	@Override
	public List<WebElement> locate(SearchContext context) {
		Set<WebElement> elements = new LinkedHashSet<WebElement>();
		for (XPathExpression cs : locators) {
			List<WebElement> execute = cs.locate(context);
			elements.addAll(execute);
		}
		return new ArrayList<WebElement>(elements);
	}
	
	public String toXPath() {
		StringBuilder sb = new StringBuilder();
		for (XPathExpression cs : locators) {
			sb.append(cs.toXPath()).append(" | ");
		}
		if (!locators.isEmpty()) {
			sb.delete(sb.length()-3, sb.length());
		}
		return sb.toString(); 
	}
	
	public String toXPathCondition() {
		StringBuilder sb = new StringBuilder();
		sb.append('(');
		for (XPathExpression cs : locators) {
			sb.append(cs.toXPathCondition()).append(") or (");
		}
		if (!locators.isEmpty()) {
			sb.delete(sb.length()-6, sb.length());
		}
		sb.append(')');
		return sb.toString(); 
	}

}