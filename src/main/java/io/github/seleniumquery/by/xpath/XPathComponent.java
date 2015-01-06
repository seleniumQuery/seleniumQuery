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

public class XPathComponent {

	public static final String MATCH_EVERYTHING_XPATH_CONDITIONAL = "true()";

	private String xPathExpression;
	private List<XPathComponent> otherExpressions;
	private ElementFilterList elementFilterList;
	
	/**
	 * The behavior this field drives should probably be replaced with polymorphism.
	 * Also, its presence shows a "rupture" with this class' (XPathExpression) concept.
	 * How can an "XPath Exression" have a "CSS Selector Type"?
	 * Maybe this should be a CSSSelector (or SQLocator, or whatever) that has a toXPathExpression() method.
	 * Or the conversion to XPath may be a visitor hierarchy. Something like that.
	 */
	private CssSelectorType cssSelectorType;

	XPathComponent(String xPathExpression, ElementFilterList elementFilterList, CssSelectorType cssSelectorType) {
		this(xPathExpression, new ArrayList<XPathComponent>(), elementFilterList, cssSelectorType);
	}

	private XPathComponent(String xPathExpression, List<XPathComponent> aggregatedComponents,
						   	ElementFilterList elementFilterList, CssSelectorType cssSelectorType) {
		this.xPathExpression = xPathExpression;
		this.otherExpressions = aggregatedComponents;
		this.elementFilterList = elementFilterList;
		this.cssSelectorType = cssSelectorType;
	}
	
	@Override
	public String toString() {
		return "[XPath: \""+xPathExpression+"\", kind: "+ cssSelectorType +", otherExps: "+otherExpressions+", filter: "+elementFilterList+"]";
	}
	
	public List<WebElement> findWebElements(SearchContext context) {
		String finalXPathExpression = this.toXPath();
		List<WebElement> elements = new By.ByXPath(finalXPathExpression).findElements(context);
		WebDriver driver = SelectorUtils.getWebDriver(context);
		return elementFilterList.filter(driver, elements);
	}

	public XPathComponent combine(XPathComponent other) {
		return combine(other, other.cssSelectorType);
	}

	public XPathComponent combine(XPathComponent other, CssSelectorType cssSelectorType) {
		XPathComponent otherCopyWithModifiedType = new XPathComponent(other.xPathExpression, other.otherExpressions,
																		other.elementFilterList, cssSelectorType);
		List<XPathComponent> aggregatedComponents = new ArrayList<XPathComponent>(this.otherExpressions);
		aggregatedComponents.add(otherCopyWithModifiedType);
		aggregatedComponents.addAll(other.otherExpressions);
		// should combine ElementFilterList here as well
		ElementFilterList combinedElementFilterList = this.elementFilterList;
		return new XPathComponent(this.xPathExpression, aggregatedComponents, combinedElementFilterList, this.cssSelectorType);
	}
	
	public String toXPath() {
		if (cssSelectorType != CssSelectorType.TAG) {
			throw new RuntimeException("This should not happen!");
		}
		if ("*".equals(this.xPathExpression)) {
			this.xPathExpression = ".//*";
		} else {
			this.xPathExpression = ".//*[self::" + this.xPathExpression+"]";
		}
		for (XPathComponent other : otherExpressions) {
			mergeExpression(other);
		}
		return "(" + this.xPathExpression + ")";
	}

	private void mergeExpression(XPathComponent other) {
		this.elementFilterList = ElementFilterListCombinator.combine(null, this.xPathExpression, this.elementFilterList,
				other.cssSelectorType, other.xPathExpression, other.elementFilterList);
		this.xPathExpression = other.cssSelectorType.merge(this.xPathExpression, null, other.xPathExpression);
	}

	public String toXPathCondition() {
		if (cssSelectorType != CssSelectorType.TAG) {
			throw new RuntimeException("This should not happen!");
		}
		if ("*".equals(this.xPathExpression)) {
			this.xPathExpression = MATCH_EVERYTHING_XPATH_CONDITIONAL;
		} else {
			this.xPathExpression = "local-name() = '"+this.xPathExpression+"'";
		}
		for (XPathComponent other : otherExpressions) {
			mergeAsCondition(other);
		}
		return this.xPathExpression;
	}

	private void mergeAsCondition(XPathComponent other) {
		this.elementFilterList = ElementFilterListCombinator.combine(null, this.xPathExpression, this.elementFilterList,
				other.cssSelectorType, other.xPathExpression, other.elementFilterList);
		this.xPathExpression = other.cssSelectorType.mergeAsCondition(this.xPathExpression, null, other.xPathExpression);
	}

	public String toSingleXPathExpression() {
		return this.xPathExpression;
	}

}