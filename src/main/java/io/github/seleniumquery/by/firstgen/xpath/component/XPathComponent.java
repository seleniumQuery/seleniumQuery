package io.github.seleniumquery.by.xpath.component;

import io.github.seleniumquery.by.filter.ElementFilterList;

import java.util.Collections;
import java.util.List;

public abstract class XPathComponent {

	public static final String MATCH_EVERYTHING_XPATH_CONDITIONAL = "true()";
	protected String xPathExpression;
	protected final List<XPathComponent> combinatedComponents;
	protected ElementFilterList elementFilterList;
	
	XPathComponent(String xPathExpression, ElementFilterList elementFilterList) {
		this(xPathExpression, Collections.<XPathComponent>emptyList(), elementFilterList);
	}

	XPathComponent(String xPathExpression, List<XPathComponent> combinatedComponents, ElementFilterList elementFilterList) {
		this.xPathExpression = xPathExpression;
		this.combinatedComponents = Collections.unmodifiableList(combinatedComponents);
		this.elementFilterList = elementFilterList;
	}
	
	@Override
	public String toString() {
		return "[XPath: \""+xPathExpression+"\", combinatedComponents: "+ combinatedComponents +", filter: "+elementFilterList+"]";
	}
	
	public ElementFilterList mergeIntoFilter(ElementFilterList sourceElementFilterList) {
		return sourceElementFilterList;
	}

	public abstract String mergeIntoExpression(String sourceXPathExpression);

	public ElementFilterList mergeFilterAsCondition(ElementFilterList sourceElementFilterList) {
		return sourceElementFilterList;
	}

	public abstract String mergeExpressionAsCondition(String sourceXPathExpression);

	public String toSingleXPathExpression() {
		return this.xPathExpression;
	}

}