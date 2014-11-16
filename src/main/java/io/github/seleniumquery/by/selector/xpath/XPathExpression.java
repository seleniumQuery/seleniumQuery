package io.github.seleniumquery.by.selector.xpath;

import io.github.seleniumquery.by.selector.SelectorUtils;
import io.github.seleniumquery.by.filter.ElementFilterList;
import io.github.seleniumquery.by.filter.ElementFilterListCombinator;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class XPathExpression {

	public static final String EMPTY_LOCAL_NAME_CONDITIONAL = "local-name()";

	private String xPathExpression;
	private List<XPathExpression> otherExpressions = new ArrayList<XPathExpression>();
	private ElementFilterList elementFilterList;
	
	// TODO this is very, very, very nasty!
	// should probably be replaced by polymorphism
	public CssSelectorType kind;

	XPathExpression(String xPathExpression, ElementFilterList elementFilterList) {
		this.xPathExpression = xPathExpression;
		this.elementFilterList = elementFilterList;
		this.kind = CssSelectorType.CONDITIONAL_SIMPLE;
	}
	
	@Override
	public String toString() {
		return "[XPath: \""+xPathExpression+"\", kind: "+kind+", otherExps: "+otherExpressions+", filter: "+elementFilterList+"]";
	}
	
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
		if (this.kind != CssSelectorType.TAG) {
			throw new RuntimeException("This should not happen!");
		}
		this.xPathExpression = ".//" + this.xPathExpression;
		for (XPathExpression other : otherExpressions) {
			mergeExpression(other);
		}
		return "(" + this.xPathExpression + ")";
	}

	private void mergeExpression(XPathExpression other) {
		this.elementFilterList = ElementFilterListCombinator.combine(null, this.xPathExpression, this.elementFilterList,
				other.kind, other.xPathExpression, other.elementFilterList);
		this.xPathExpression = other.kind.merge(this.xPathExpression, null, other.xPathExpression);
	}

	public String toXPathCondition() {
		if (this.kind != CssSelectorType.TAG) {
			throw new RuntimeException("This should not happen!");
		}
		if (!"*".equals(this.xPathExpression)) {
			this.xPathExpression = "local-name() = '"+this.xPathExpression+"'";
		} else {
			this.xPathExpression = EMPTY_LOCAL_NAME_CONDITIONAL;
		}
		for (XPathExpression other : otherExpressions) {
			mergeAsCondition(other);
		}
		return this.xPathExpression;
	}

	private void mergeAsCondition(XPathExpression other) {
		this.elementFilterList = ElementFilterListCombinator.combine(null, this.xPathExpression, this.elementFilterList,
				other.kind, other.xPathExpression, other.elementFilterList);
		this.xPathExpression = other.kind.mergeAsCondition(this.xPathExpression, null, other.xPathExpression);
	}

}