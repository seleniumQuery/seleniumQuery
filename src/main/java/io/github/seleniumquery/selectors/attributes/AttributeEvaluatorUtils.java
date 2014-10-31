package io.github.seleniumquery.selectors.attributes;

import io.github.seleniumquery.selector.SelectorUtils;

import org.w3c.css.sac.AttributeCondition;

/**
 * Utility methods for XPath attributes.
 * 
 * @author acdcjunior
 * @since 1.0.0
 */
public class AttributeEvaluatorUtils {

	public static String getXPathAttribute(AttributeCondition attributeCondition) {
		String attributeName = attributeCondition.getLocalName();
		if (!Character.isLetter(attributeName.charAt(0))) {
			return "@*[local-name() = "+ SelectorUtils.intoEscapedXPathString(attributeName) +"]";
		}
		return "@"+ attributeName;
	}

}