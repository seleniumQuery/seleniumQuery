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
	private List<XPathExpression> otherExpressions = new ArrayList<XPathExpression>();
	
	private List<ElementFilter> elementFilters;
	
	// TODO this is very, very, very nasty!
	public SqSelectorKind kind;

	XPathExpression(String xPathExpression, List<ElementFilter> elementFilter) {
		this.xPathExpression = xPathExpression;
		this.elementFilters = elementFilter;
		this.kind = SqSelectorKind.CONDITIONAL_SIMPLE;
	}
	
	@Override
	public String toString() {
		return "[XPath: \""+xPathExpression+"\", kind: "+kind+", otherExps: "+otherExpressions+", filters: "+elementFilters+"]";
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
			// we are currently disabling the support to filters
			// we will only take it back when the system is stable
			throw new UnsupportedPseudoClassException("The current selector is unsupported. Please try a simpler one.");
		}
		for (ElementFilter elementFilter : elementFilters) {
			elements = elementFilter.filterElements(driver, elements);
		}
		return elements;
	}
	
	public XPathExpression combine(XPathExpression other) {
		this.otherExpressions.add(other);
		this.otherExpressions.addAll(other.otherExpressions);
		return this;
	}
	
	public String toXPath() {
		if (this.kind != SqSelectorKind.TAG) {
			throw new RuntimeException("This should not happen!");
		}
		this.xPathExpression = ".//" + this.xPathExpression;
		for (XPathExpression other : otherExpressions) {
			mergeExpression(other);
		}
		return "(" + this.xPathExpression + ")";
	}
	
	public String toXPathCondition() {
		if (this.kind != SqSelectorKind.TAG) {
			throw new RuntimeException("This should not happen!");
		}
		if (!"*".equals(this.xPathExpression)) {
			this.xPathExpression = "[local-name() = '"+this.xPathExpression+"']";
		} else {
			this.xPathExpression = "[local-name()]";
		}
		for (XPathExpression other : otherExpressions) {
			mergeExpression(other);
		}
		return this.xPathExpression.substring(1, this.xPathExpression.length()-1); // removes [] around
	}
	
	private void mergeExpression(XPathExpression other) {
		this.elementFilters.addAll(other.elementFilters);
		this.xPathExpression = other.kind.merge(this.xPathExpression, other.xPathExpression);
	}

}