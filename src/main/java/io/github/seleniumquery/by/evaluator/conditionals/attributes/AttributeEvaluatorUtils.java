package io.github.seleniumquery.by.evaluator.conditionals.attributes;

import io.github.seleniumquery.by.evaluator.SelectorUtils;
import io.github.seleniumquery.by.selector.CompiledSelector;

import org.w3c.css.sac.AttributeCondition;

public class AttributeEvaluatorUtils {

	public static CompiledSelector createAttributeNoFilterCompiledSelector(AttributeCondition attributeCondition,
																				String attributeSelectorSymbol) {
		String attributeName = SelectorUtils.escapeSelector(attributeCondition.getLocalName()).replace("\\\\", "\\");
		String wantedValue = SelectorUtils.escapeAttributeValue(attributeCondition.getValue());
		return CompiledSelector.createNoFilterSelector("[" + attributeName + attributeSelectorSymbol + wantedValue + "]");
	}
	
	public static CompiledSelector createAttributeNoFilterCompiledSelector(AttributeCondition attributeCondition) {
		String attributeName = SelectorUtils.escapeSelector(attributeCondition.getLocalName()).replace("\\\\", "\\");
		return CompiledSelector.createNoFilterSelector("[" + attributeName + "]");
	}

}