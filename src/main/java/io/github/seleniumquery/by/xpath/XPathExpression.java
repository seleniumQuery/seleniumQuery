package io.github.seleniumquery.by.xpath;

import io.github.seleniumquery.by.SelectorUtils;
import io.github.seleniumquery.by.filter.ElementFilterList;
import io.github.seleniumquery.by.filter.ElementFilterListCombinator;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class XPathExpression {

	public static final String MATCH_EVERYTHING_XPATH_CONDITIONAL = "true()";

	private String xPathExpression;
	private List<XPathExpression> otherExpressions = new ArrayList<XPathExpression>();
	private ElementFilterList elementFilterList;
	
	/**
	 * The behavior this field drives should probably be replaced with polymorphism.
	 * Also, its presence shows a "rupture" with this class' (XPathExpression) concept.
	 * How can an "XPath Exression" have a "CSS Selector Type"?
	 * Maybe this should be a CSSSelector (or SQLocator, or whatever) that has a toXPathExpression() method.
	 * Or the conversion to XPath may be a visitor hierarchy. Something like that.
	 */
	private CssSelectorType cssSelectorType;

	XPathExpression(String xPathExpression, ElementFilterList elementFilterList) {
		this(xPathExpression, elementFilterList, CssSelectorType.CONDITIONAL_SIMPLE);
	}

	XPathExpression(String xPathExpression, ElementFilterList elementFilterList, CssSelectorType cssSelectorType) {
		this.xPathExpression = xPathExpression;
		this.elementFilterList = elementFilterList;
		this.cssSelectorType = cssSelectorType;
	}
	
	@Override
	public String toString() {
		return "[XPath: \""+xPathExpression+"\", kind: "+ getCssSelectorType() +", otherExps: "+otherExpressions+", filter: "+elementFilterList+"]";
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
		if (this.getCssSelectorType() != CssSelectorType.TAG) {
			throw new RuntimeException("This should not happen!");
		}
		if ("*".equals(this.xPathExpression)) {
			this.xPathExpression = ".//*";
		} else {
			this.xPathExpression = ".//*[self::" + this.xPathExpression+"]";
		}
		for (XPathExpression other : otherExpressions) {
			mergeExpression(other);
		}
		return "(" + this.xPathExpression + ")";
	}

	private void mergeExpression(XPathExpression other) {
		this.elementFilterList = ElementFilterListCombinator.combine(null, this.xPathExpression, this.elementFilterList,
				other.getCssSelectorType(), other.xPathExpression, other.elementFilterList);
		this.xPathExpression = other.getCssSelectorType().merge(this.xPathExpression, null, other.xPathExpression);
	}

	public String toXPathCondition() {
		if (this.getCssSelectorType() != CssSelectorType.TAG) {
			throw new RuntimeException("This should not happen!");
		}
		if ("*".equals(this.xPathExpression)) {
			this.xPathExpression = MATCH_EVERYTHING_XPATH_CONDITIONAL;
		} else {
			this.xPathExpression = "local-name() = '"+this.xPathExpression+"'";
		}
		for (XPathExpression other : otherExpressions) {
			mergeAsCondition(other);
		}
		return this.xPathExpression;
	}

	private void mergeAsCondition(XPathExpression other) {
		this.elementFilterList = ElementFilterListCombinator.combine(null, this.xPathExpression, this.elementFilterList,
				other.getCssSelectorType(), other.xPathExpression, other.elementFilterList);
		this.xPathExpression = other.getCssSelectorType().mergeAsCondition(this.xPathExpression, null, other.xPathExpression);
	}

	public String toSingleXPathExpression() {
		return this.xPathExpression;
	}

	public CssSelectorType getCssSelectorType() {
		return cssSelectorType;
	}

	public void setCssSelectorType(CssSelectorType cssSelectorType) {
		this.cssSelectorType = cssSelectorType;
	}
}