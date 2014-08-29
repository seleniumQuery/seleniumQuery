package io.github.seleniumquery.selectorxpath;

import io.github.seleniumquery.locator.ElementFilter;
import io.github.seleniumquery.locator.Locator;
import io.github.seleniumquery.selector.SelectorUtils;

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
		return "[XPath: \""+xPathExpression+"\", kind: "+kind+", filters: "+elementFilters+"]";
	}
	
	@Override
	public List<WebElement> findWebElements(SearchContext context) {
		String finalXPathExpression = this.toXPath();
		List<WebElement> elements = new By.ByXPath(finalXPathExpression).findElements(context);
		WebDriver driver = SelectorUtils.getWebDriver(context);
		return filter(driver, elements);
	}
	
	public List<WebElement> filter(WebDriver driver, List<WebElement> elements) {
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
	
	private XPathExpression merge(XPathExpression other) {
		switch (other.kind) {
		case ADJACENT:
			this.xPathExpression = this.xPathExpression + "/following-sibling::" + other.xPathExpression;
			break;
		case DESCENDANT_DIRECT:
			this.xPathExpression = this.xPathExpression + "/" + other.xPathExpression;
			break;
		case DESCENDANT_GENERAL:
			this.xPathExpression = this.xPathExpression + "/x/" + other.xPathExpression;
			break;
		case CONDITIONAL_SIMPLE:
			if (this.xPathExpression.endsWith("]")) {
				this.xPathExpression = this.xPathExpression.substring(0, this.xPathExpression.length()-1) + " and " + other.xPathExpression.substring(1);
			} else {
				this.xPathExpression = this.xPathExpression + other.xPathExpression;
			}
			break;
		case CONDITIONAL_TO_ALL:
			this.xPathExpression = "(" + this.xPathExpression + ")" + other.xPathExpression;
			break;
		case TAG:
			if (!"*".equals(other.xPathExpression)) {
				this.merge(XPathSelectorFactory.createNoFilterSelector("[name() = '"+other.xPathExpression+"']"));
			} else {
				this.merge(XPathSelectorFactory.createNoFilterSelector("[name()]"));
			}
			break;
		default:
			throw new RuntimeException("Kind '"+other.kind + "' is not supported yet!");
		}
		
		this.elementFilters.addAll(other.elementFilters);
		return this;
	}
	
	public String toXPath() {
		System.out.println("@# line: "+ this.xpathLine);
		if (this.kind != SqSelectorKind.TAG) {
			throw new RuntimeException("Weird... i didnt think this was possible!");
		}
		this.xPathExpression = "/z/" + this.xPathExpression;
		for (XPathExpression x : xpathLine) {
			this.merge(x);
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
		for (XPathExpression x : xpathLine) {
			this.merge(x);
		}
		return this.xPathExpression.substring(1, this.xPathExpression.length()-1); // removes [] around
	}

}