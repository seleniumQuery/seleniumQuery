package io.github.seleniumquery.by.xpath.component;

import io.github.seleniumquery.by.SelectorUtils;
import io.github.seleniumquery.by.filter.ElementFilterList;
import io.github.seleniumquery.by.filter.ElementFilterListCombinator;

import java.util.ArrayList;
import java.util.List;

import io.github.seleniumquery.by.xpath.CssCombinationType;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class XPathComponent {

	public static final String MATCH_EVERYTHING_XPATH_CONDITIONAL = "true()";

	protected String xPathExpression;
	protected List<XPathComponent> combinatedComponents;
	protected ElementFilterList elementFilterList;
	
	/**
	 * The behavior this field drives should probably be replaced with polymorphism.
	 * Also, its presence shows a "rupture" with this class' (XPathExpression) concept.
	 * How can an "XPath Exression" have a "CSS Selector Type"?
	 * Maybe this should be a CSSSelector (or SQLocator, or whatever) that has a toXPathExpression() method.
	 * Or the conversion to XPath may be a visitor hierarchy. Something like that.
	 */
	protected CssCombinationType cssCombinationType;

	XPathComponent(String xPathExpression, ElementFilterList elementFilterList, CssCombinationType cssCombinationType) {
		this(xPathExpression, new ArrayList<XPathComponent>(), elementFilterList, cssCombinationType);
	}

	public XPathComponent(String xPathExpression, List<XPathComponent> combinatedComponents,
						   	ElementFilterList elementFilterList, CssCombinationType cssCombinationType) {
		this.xPathExpression = xPathExpression;
		this.combinatedComponents = combinatedComponents;
		this.elementFilterList = elementFilterList;
		this.cssCombinationType = cssCombinationType;
	}
	
	@Override
	public String toString() {
		return "[XPath: \""+xPathExpression+"\", kind: "+ cssCombinationType +", otherExps: "+ combinatedComponents +", filter: "+elementFilterList+"]";
	}
	
	public List<WebElement> findWebElements(SearchContext context) {
		String finalXPathExpression = this.toXPath();
		List<WebElement> elements = new By.ByXPath(finalXPathExpression).findElements(context);
		WebDriver driver = SelectorUtils.getWebDriver(context);
		return elementFilterList.filter(driver, elements);
	}

	public XPathComponent combineKeepingType(XPathComponent other) {
		// im pretty sure other.cssCombinationType is always CONDITIONAL_SIMPLE here
		// maybe CONDITIONAL_SIPLE_APPLIED_TO_ALL...
		return combine(other, other.cssCombinationType);
	}

	public XPathComponent combine(XPathComponent other, CssCombinationType cssCombinationType) {
		XPathComponent otherCopyWithModifiedType = new XPathComponent(other.xPathExpression, other.combinatedComponents,
																		other.elementFilterList, cssCombinationType);
		List<XPathComponent> aggregatedComponents = new ArrayList<XPathComponent>(this.combinatedComponents);
		aggregatedComponents.add(otherCopyWithModifiedType);
		aggregatedComponents.addAll(other.combinatedComponents);
		// should combine ElementFilterList here as well
		ElementFilterList combinedElementFilterList = this.elementFilterList;
		return new XPathComponent(this.xPathExpression, aggregatedComponents, combinedElementFilterList, this.cssCombinationType);
	}
	
	public String toXPath() {
		if (cssCombinationType != CssCombinationType.TAG) {
			throw new RuntimeException("This should not happen!");
		}
		if ("*".equals(this.xPathExpression)) {
			this.xPathExpression = ".//*";
		} else {
			this.xPathExpression = ".//*[self::" + this.xPathExpression+"]";
		}
		for (XPathComponent other : combinatedComponents) {
			mergeExpression(other);
		}
		return "(" + this.xPathExpression + ")";
	}

	private void mergeExpression(XPathComponent other) {
		this.elementFilterList = ElementFilterListCombinator.combine(null, this.xPathExpression, this.elementFilterList,
				other.cssCombinationType, other.xPathExpression, other.elementFilterList);
		this.xPathExpression = other.cssCombinationType.merge(this.xPathExpression, null, other.xPathExpression);
	}

	public String toXPathCondition() {
		if (cssCombinationType != CssCombinationType.TAG) {
			throw new RuntimeException("This should not happen!");
		}
		if ("*".equals(this.xPathExpression)) {
			this.xPathExpression = MATCH_EVERYTHING_XPATH_CONDITIONAL;
		} else {
			this.xPathExpression = "local-name() = '"+this.xPathExpression+"'";
		}
		for (XPathComponent other : combinatedComponents) {
			mergeAsCondition(other);
		}
		return this.xPathExpression;
	}

	private void mergeAsCondition(XPathComponent other) {
		this.elementFilterList = ElementFilterListCombinator.combine(null, this.xPathExpression, this.elementFilterList,
				other.cssCombinationType, other.xPathExpression, other.elementFilterList);
		this.xPathExpression = other.cssCombinationType.mergeAsCondition(this.xPathExpression, null, other.xPathExpression);
	}

	public String toSingleXPathExpression() {
		return this.xPathExpression;
	}

	public String getXPathExpression() {
		return xPathExpression;
	}

	public List<XPathComponent> getCombinatedComponents() {
		return combinatedComponents;
	}

	public ElementFilterList getElementFilterList() {
		return elementFilterList;
	}

	public CssCombinationType getCssCombinationType() {
		return cssCombinationType;
	}
}