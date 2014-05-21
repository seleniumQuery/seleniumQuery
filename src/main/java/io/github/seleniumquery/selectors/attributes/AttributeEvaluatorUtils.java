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

}