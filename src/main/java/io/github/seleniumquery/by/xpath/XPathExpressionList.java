package io.github.seleniumquery.by.xpath;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

/**
 * Represents a list of {@link XPathExpression}. In other words, multiple XPath expressions
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
	
	private List<XPathExpression> xPathExpressions;
	
	XPathExpressionList(List<XPathExpression> xPathExpressions) {
		this.xPathExpressions = xPathExpressions;
	}
	
	public List<WebElement> findWebElements(SearchContext context) {
		Set<WebElement> elements = new LinkedHashSet<WebElement>();
		for (XPathExpression xPathExpression : xPathExpressions) {
			List<WebElement> elementsFound = xPathExpression.findWebElements(context);
			elements.addAll(elementsFound);
		}
		return new ArrayList<WebElement>(elements);
	}
	
	public String toXPath() {
		StringBuilder compoundXPathExpression = new StringBuilder();
		for (XPathExpression xPathExpression : xPathExpressions) {
			compoundXPathExpression.append(xPathExpression.toXPath()).append(XPATH_EXPRESSION_OR);
		}
		removeTrailingExpressionOr(compoundXPathExpression);
		return compoundXPathExpression.toString(); 
	}

	/**
	 * Removes a trailing XPATH_EXPRESSION_OR, if necessary.
	 */
	private void removeTrailingExpressionOr(StringBuilder compoundXPathExpression) {
		if (!xPathExpressions.isEmpty()) {
			int xPathExpressionLength = compoundXPathExpression.length();
			compoundXPathExpression.delete(xPathExpressionLength - XPATH_EXPRESSION_OR.length(), xPathExpressionLength);
		}
	}
	
	public String toXPathCondition() {
		StringBuilder compoundXPathCondition = new StringBuilder();
		compoundXPathCondition.append('(');
		for (XPathExpression xPathExpression : xPathExpressions) {
			compoundXPathCondition.append(xPathExpression.toXPathCondition()).append(XPATH_CONDITIONAL_OR);
		}
		removeTrailingConditionalOr(compoundXPathCondition);
		compoundXPathCondition.append(')');
		return compoundXPathCondition.toString(); 
	}

	/**
	 * Removes a trailing XPATH_CONDITIONAL_OR, if necessary.
	 */
	private void removeTrailingConditionalOr(StringBuilder sb) {
		if (!xPathExpressions.isEmpty()) {
			sb.delete(sb.length()-XPATH_CONDITIONAL_OR.length(), sb.length());
		}
	}

}