package io.github.seleniumquery.by.xpath.component;

import io.github.seleniumquery.by.SelectorUtils;
import io.github.seleniumquery.by.filter.ElementFilterList;
import io.github.seleniumquery.by.xpath.CssCombinationType;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class XPathComponent {

	public static final String MATCH_EVERYTHING_XPATH_CONDITIONAL = "true()";

	protected String xPathExpression;
	protected final List<XPathComponent> combinatedComponents;
	protected ElementFilterList elementFilterList;
	
	protected final CssCombinationType cssCombinationType;

	XPathComponent(String xPathExpression, ElementFilterList elementFilterList, CssCombinationType cssCombinationType) {
		this(xPathExpression, new ArrayList<XPathComponent>(), elementFilterList, cssCombinationType);
	}

	XPathComponent(String xPathExpression, List<XPathComponent> combinatedComponents,
						   	ElementFilterList elementFilterList, CssCombinationType cssCombinationType) {
		this.xPathExpression = xPathExpression;
		this.combinatedComponents = Collections.unmodifiableList(combinatedComponents);
		this.elementFilterList = elementFilterList;
		this.cssCombinationType = cssCombinationType;
	}
	
	@Override
	public String toString() {
		return "[XPath: \""+xPathExpression+"\", cssCombinationType: "+ cssCombinationType +
				", combinatedComponents: "+ combinatedComponents +", filter: "+elementFilterList+"]";
	}
	
	public List<WebElement> findWebElements(SearchContext context) {
		String finalXPathExpression = this.toXPath();
		List<WebElement> elements = new By.ByXPath(finalXPathExpression).findElements(context);
		WebDriver driver = SelectorUtils.getWebDriver(context);
		return elementFilterList.filter(driver, elements);
	}

	// i know, this method violates LSP, but bear with me while I manage to refactor it out...
	public String toXPath() {
		throw new RuntimeException("Only Tag components can be turned into XPath expressions. Got: "+ this.cssCombinationType + " "+this.getClass());
	}

	public ElementFilterList mergeFilter(ElementFilterList sourceElementFilterList) {
		return sourceElementFilterList;
	}

	public abstract String mergeExpression(String sourceXPathExpression);

	// i know, this method violates LSP, but bear with me while I manage to refactor it out...
	public String toXPathCondition() {
		throw new RuntimeException("Only Tag components can be turned into XPath expressions. Got: "+ this.cssCombinationType + " "+this.getClass());
	}

	public ElementFilterList mergeFilterAsCondition(ElementFilterList sourceElementFilterList) {
		return sourceElementFilterList;
	}

	public abstract String mergeExpressionAsCondition(String sourceXPathExpression);

	public String toSingleXPathExpression() {
		return this.xPathExpression;
	}

}