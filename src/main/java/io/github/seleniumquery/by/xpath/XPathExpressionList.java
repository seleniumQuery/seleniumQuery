package io.github.seleniumquery.by.xpath;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

/**
 * Represents a list of {@link XPathComponent}. In other words, multiple XPath expressions
 * that should be "composed" or "merged" to become a single expression.
 * 
 * @since 0.9.0
 * 
 * @author acdcjunior
 * @author ricardo-sc
 */
public class XPathExpressionList {
	
	private static final String XPATH_EXPRESSION_OR = " | ";
	private static final String XPATH_CONDITIONAL_OR = ") or (";
	
	private List<XPathComponent> xPathComponents;
	
	XPathExpressionList(List<XPathComponent> xPathComponents) {
		this.xPathComponents = xPathComponents;
	}
	
	public List<WebElement> findWebElements(SearchContext context) {
		Set<WebElement> elements = new LinkedHashSet<WebElement>();
		for (XPathComponent xPathComponent : xPathComponents) {
			List<WebElement> elementsFound = xPathComponent.findWebElements(context);
			elements.addAll(elementsFound);
		}
		return new ArrayList<WebElement>(elements);
	}
	
	public String toXPath() {
		StringBuilder compoundXPathExpression = new StringBuilder();
		for (XPathComponent xPathComponent : xPathComponents) {
			compoundXPathExpression.append(xPathComponent.toXPath()).append(XPATH_EXPRESSION_OR);
		}
		removeTrailingExpressionOr(compoundXPathExpression);
		return compoundXPathExpression.toString(); 
	}

	/**
	 * Removes a trailing XPATH_EXPRESSION_OR, if necessary.
	 */
	private void removeTrailingExpressionOr(StringBuilder compoundXPathExpression) {
		if (!xPathComponents.isEmpty()) {
			int xPathExpressionLength = compoundXPathExpression.length();
			compoundXPathExpression.delete(xPathExpressionLength - XPATH_EXPRESSION_OR.length(), xPathExpressionLength);
		}
	}
	
	public String toXPathCondition() {
		StringBuilder compoundXPathCondition = new StringBuilder();
		compoundXPathCondition.append('(');
		for (XPathComponent xPathComponent : xPathComponents) {
			compoundXPathCondition.append(xPathComponent.toXPathCondition()).append(XPATH_CONDITIONAL_OR);
		}
		removeTrailingConditionalOr(compoundXPathCondition);
		compoundXPathCondition.append(')');
		return compoundXPathCondition.toString(); 
	}

	/**
	 * Removes a trailing XPATH_CONDITIONAL_OR, if necessary.
	 */
	private void removeTrailingConditionalOr(StringBuilder sb) {
		if (!xPathComponents.isEmpty()) {
			sb.delete(sb.length()-XPATH_CONDITIONAL_OR.length(), sb.length());
		}
	}

}