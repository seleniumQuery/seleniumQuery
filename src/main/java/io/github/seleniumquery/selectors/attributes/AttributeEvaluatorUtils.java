package io.github.seleniumquery.selectors.attributes;

import io.github.seleniumquery.selector.CompiledCssSelector;
import io.github.seleniumquery.selector.SelectorUtils;

import org.w3c.css.sac.AttributeCondition;

public class AttributeEvaluatorUtils {

	public static CompiledCssSelector createAttributeNoFilterCompiledSelector(AttributeCondition attributeCondition,
																				String attributeSelectorSymbol) {
		String attributeName = SelectorUtils.escapeSelector(attributeCondition.getLocalName()).replace("\\\\", "\\");
		String wantedValue = SelectorUtils.escapeAttributeValue(attributeCondition.getValue());
		return CompiledCssSelector.createNoFilterSelector("[" + attributeName + attributeSelectorSymbol + wantedValue + "]");
	}
	
	public static CompiledCssSelector createAttributeNoFilterCompiledSelector(AttributeCondition attributeCondition) {
		String attributeName = SelectorUtils.escapeSelector(attributeCondition.getLocalName()).replace("\\\\", "\\");
		return CompiledCssSelector.createNoFilterSelector("[" + attributeName + "]");
	}
	
	public static String getXPathAttribute(AttributeCondition attributeCondition) {
		// em queals or has era
		String attributeName = attributeCondition.getLocalName();
		if (!Character.isLetter(attributeName.charAt(0))) {
			return "@*[name() = "+ SelectorUtils.intoEscapedXPathString(attributeName) +"]";
		}
		return "@"+ attributeName;
	}

}