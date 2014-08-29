package io.github.seleniumquery.selectorxpath;

import io.github.seleniumquery.locator.ElementFilter;
import io.github.seleniumquery.locator.Locator;
import io.github.seleniumquery.selector.SelectorUtils;
import io.github.seleniumquery.selectors.pseudoclasses.UnsupportedPseudoClassException;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class XPathExpression implements Locator {

	private String xPathExpression;
	private List<ElementFilter> elementFilters;
	
	/**
	 * What is a XPath line??? TODO
	 */
	private List<XPathExpression> xpathLine = new ArrayList<XPathExpression>();
	
	public SqSelectorKind kind;

	XPathExpression(String xPathExpression, List<ElementFilter> elementFilter) {
		this.xPathExpression = xPathExpression;
		this.elementFilters = elementFilter;
		this.kind = SqSelectorKind.CONDITIONAL_SIMPLE;
	}
	
	@Override
	public String toString() {
		return "[XPath: \""+xPathExpression+"\", kind: "+kind+", line: "+xpathLine+", filters: "+elementFilters+"]";
	}
	
	@Override
	public List<WebElement> findWebElements(SearchContext context) {
		String finalXPathExpression = this.toXPath();
		List<WebElement> elements = new By.ByXPath(finalXPathExpression).findElements(context);
		WebDriver driver = SelectorUtils.getWebDriver(context);
		return filter(driver, elements);
	}
	
	public List<WebElement> filter(WebDriver driver, List<WebElement> elements) {
		if (this.elementFilters.size() > 0) {
			throw new UnsupportedPseudoClassException("The current selector is unsupported. Please try a simpler one.");
		}
		for (ElementFilter elementFilter : elementFilters) {
			elements = elementFilter.filterElements(driver, elements);
		}
		return elements;
	}
	
	public XPathExpression combine(XPathExpression other) {
		this.xpathLine.add(other);
		this.xpathLine.addAll(other.xpathLine);
		return this;
	}
	
	public String toXPath() {
		if (this.kind != SqSelectorKind.TAG) {
			throw new RuntimeException("Weird... i didnt think this was possible!");
		}
		this.xPathExpression = "//" + this.xPathExpression;
		for (XPathExpression other : xpathLine) {
			mergeExpression(other);
		}
		return "(" + this.xPathExpression + ")";
	}
	
	public String toXPathCondition() {
		if (this.kind != SqSelectorKind.TAG) {
			throw new RuntimeException("Weird... i didnt think this was possible!");
		}
		if (!"*".equals(this.xPathExpression)) {
			this.xPathExpression = "[name() = '"+this.xPathExpression+"']";
		} else {
			this.xPathExpression = "[name()]";
		}
		for (XPathExpression other : xpathLine) {
			mergeExpression(other);
		}
		return this.xPathExpression.substring(1, this.xPathExpression.length()-1); // removes [] around
	}
	
	private void mergeExpression(XPathExpression other) {
		this.elementFilters.addAll(other.elementFilters);
		this.xPathExpression = other.kind.merge(this.xPathExpression, other.xPathExpression);
	}

}