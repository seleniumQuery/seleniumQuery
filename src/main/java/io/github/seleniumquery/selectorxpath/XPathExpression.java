package io.github.seleniumquery.selectorxpath;

import io.github.seleniumquery.locator.Locator;
import io.github.seleniumquery.locator.filter.ElementFilterList;
import io.github.seleniumquery.locator.filter.ElementFilterListCombinator;
import io.github.seleniumquery.selector.SelectorUtils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class XPathExpression implements Locator {

	private String xPathExpression;
	private List<XPathExpression> otherExpressions = new ArrayList<XPathExpression>();
	private ElementFilterList elementFilterList;
	
	// TODO this is very, very, very nasty!
	// should probably be replaced by polymorphism
	public SqSelectorKind kind;

	XPathExpression(String xPathExpression, ElementFilterList elementFilterList) {
		this.xPathExpression = xPathExpression;
		this.elementFilterList = elementFilterList;
		this.kind = SqSelectorKind.CONDITIONAL_SIMPLE;
	}
	
	@Override
	public String toString() {
		return "[XPath: \""+xPathExpression+"\", kind: "+kind+", otherExps: "+otherExpressions+", filter: "+elementFilterList+"]";
	}
	
	@Override
	public List<WebElement> findWebElements(SearchContext context) {
		String finalXPathExpression = this.toXPath();
		List<WebElement> elements = new By.ByXPath(finalXPathExpression).findElements(context);
		WebDriver driver = SelectorUtils.getWebDriver(context);
		return elementFilterList.filter(driver, elements);
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
		this.elementFilterList = ElementFilterListCombinator.combine((WebElement) null, this.xPathExpression, this.elementFilterList,
																		other.kind, other.xPathExpression, other.elementFilterList);
		this.xPathExpression = other.kind.merge(this.xPathExpression, other.xPathExpression);
	}

}