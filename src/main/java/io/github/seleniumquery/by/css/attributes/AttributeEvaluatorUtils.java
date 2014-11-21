package io.github.seleniumquery.by.css.attributes;

import io.github.seleniumquery.by.SelectorUtils;

import org.w3c.css.sac.AttributeCondition;

/**
 * Utility methods for XPath attributes.
 * 
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class AttributeEvaluatorUtils {

	public static String getXPathAttribute(AttributeCondition attributeCondition) {
		String attributeName = attributeCondition.getLocalName();
		if (!Character.isLetter(attributeName.charAt(0))) {
			attributeName = SelectorUtils.intoEscapedXPathString(attributeName);
		}
		return "@*[translate(local-name(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '"+ attributeName.toLowerCase() +"']";
	}

}