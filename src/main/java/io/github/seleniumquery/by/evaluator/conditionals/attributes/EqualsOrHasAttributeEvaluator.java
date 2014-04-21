package io.github.seleniumquery.by.evaluator.conditionals.attributes;

import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;
import io.github.seleniumquery.by.evaluator.CSSCondition;
import io.github.seleniumquery.by.evaluator.SelectorUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.AttributeCondition;
import org.w3c.css.sac.Selector;

public class EqualsOrHasAttributeEvaluator implements CSSCondition<AttributeCondition> {

	private static final EqualsOrHasAttributeEvaluator instance = new EqualsOrHasAttributeEvaluator();

	public static EqualsOrHasAttributeEvaluator getInstance() {
		return instance;
	}
	
	private EqualsOrHasAttributeEvaluator() { }

	/**
	 * @see {@link org.w3c.css.sac.Condition#SAC_ATTRIBUTE_CONDITION}
	 * 
	 * This condition checks an attribute. example:
	 * 
	 * [simple]
	 * [restart="never"]
	 * 
	 * Case INsensitive!
	 */
	@Override
	public boolean is(WebDriver driver, WebElement element, Selector selectorUpToThisPoint, AttributeCondition attributeCondition) {
		String attributeName = attributeCondition.getLocalName();
		// [attribute=wantedValue]
		if (attributeCondition.getSpecified()) {
			String wantedValue = attributeCondition.getValue();
			String actualValue = element.getAttribute(attributeName);
			return equalsIgnoreCase(actualValue, wantedValue);
		}
		// [attribute]
		return SelectorUtils.hasAttribute(element, attributeName);
	}

}